package com.rqpa.algo.primes;

public class BruteForcePrimeNumbersProvider implements PrimeNumbersProvider
{
    public static final BruteForcePrimeNumbersProvider instance = new BruteForcePrimeNumbersProvider();

    private static final int MAX_NUMBERS = 10_000_000;

    private final long[] knownPrimes = new long[MAX_NUMBERS];
    private int knownPrimesCount = 2;
    private long lastCheckedNumber = 3;

    private BruteForcePrimeNumbersProvider()
    {
        knownPrimes[0] = 2;
        knownPrimes[1] = 3;
    }

    @Override
    public synchronized long[] getPrimesLessThan(long n)
    {
        ensurePrimesLessThanNAvailable(n);
        return PrimeNumbersProviderSupport.resolvePrimesArrayWithPrimesLessThan(knownPrimes, 0, knownPrimesCount, n);
    }

    private void ensurePrimesLessThanNAvailable(long n)
    {
        long largestKnownPrime = getLargestKnownPrime();
        if (knownPrimesCount == MAX_NUMBERS && n > largestKnownPrime) {
            throw new IllegalArgumentException("This algorithm can not handle primes larger than " + largestKnownPrime);
        }

        while (n > lastCheckedNumber + 1 && knownPrimesCount < MAX_NUMBERS) {
            calculateNextPrimeIfLessThanN(n);
        }

        if (n > lastCheckedNumber + 1) {
            // We've reached the max numbers we can calculate and n is still bigger than the last one we checked.
            // This stuff can not be calculated so throw an exception
            throw new IllegalArgumentException("This algorithm can not handle primes larger than " + getLargestKnownPrime());
        }
    }

    private long getLargestKnownPrime() {
        return knownPrimes[knownPrimesCount - 1];
    }

    private void calculateNextPrimeIfLessThanN(long n) {
        long primeCandidate = lastCheckedNumber + 1;
        for (; primeCandidate < n; primeCandidate++) {
            if (isPrime(primeCandidate)) {
                knownPrimes[knownPrimesCount++] = primeCandidate;
                lastCheckedNumber = primeCandidate;
                return;
            }
        }
        lastCheckedNumber = primeCandidate - 1;
    }

    private boolean isPrime(long candidate) {
        for (int i = 0; i < knownPrimesCount; i++) {
            long knownPrime = knownPrimes[i];
            if (knownPrime * knownPrime > candidate) {
                // No point in checking for divisibility when prime^2 > candidate.
                // If the candidate is not divisible to any prime that is smaller than sqrt(candidate) then candidate is considered a prime
                return true;
            }

            if (candidate % knownPrime == 0) {
                return false;
            }
        }

        return true;
    }
}
