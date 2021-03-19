package com.rqpa.algo.primes;

import java.util.Arrays;
import java.util.BitSet;

public class EratosthenesSievePrimeNumbersProvider implements PrimeNumbersProvider
{
    public static final EratosthenesSievePrimeNumbersProvider instance = new EratosthenesSievePrimeNumbersProvider();

    private static final int MAX_NUMBER = 100_000;

    private final Object lock = new Object();
    private long[] primes;

    private EratosthenesSievePrimeNumbersProvider()
    {
        // Singleton
    }

    @Override
    public boolean isPrime(long n)
    {
        ensureNumberSupported(n);
        return Arrays.binarySearch(primes, n) >= 0;
    }

    @Override
    public long[] getPrimesLessThan(long n)
    {
        ensureNumberSupported(n);

        if (primes == null) {
            synchronized (lock) {
                if (primes == null) {
                    doSieve();
                }
            }
        }
        return PrimeNumbersProviderSupport.resolvePrimesArrayWithPrimesLessThan(primes, n);
    }

    private void ensureNumberSupported(long n)
    {
        if (n > MAX_NUMBER) {
            throw new IllegalArgumentException("This implementation of \"erathosten sieve\" can not find primes larger than " + MAX_NUMBER);
        }
    }

    private void doSieve() {
        // To prevent setting all bits to true, just invert the logic.
        // If the n-th bit is NOT set, that means that (n + 2) is a prime
        int bitSetSize = MAX_NUMBER - 2;
        BitSet bits = new BitSet(bitSetSize);
        bits.set(0, bitSetSize, false);
        int currentNumberIndex = 0;
        while (currentNumberIndex < bitSetSize) {
            int currentNumber = currentNumberIndex + 2;
            for (int i = currentNumberIndex + currentNumber; i < bitSetSize; i += currentNumber) {
                bits.set(i);
            }

            currentNumberIndex = bits.nextClearBit(currentNumberIndex + 1);
        }

        int clearBitsCount = bits.size() - bits.cardinality();
        primes = new long[clearBitsCount];
        // By definition 2 is a prime and therefore the first bit is clear
        int lastPrimeBitIndex = -1;
        for (int i = 0; i < primes.length; i++) {
            lastPrimeBitIndex = bits.nextClearBit(lastPrimeBitIndex + 1);
            primes[i] = lastPrimeBitIndex + 2;
        }
    }
}
