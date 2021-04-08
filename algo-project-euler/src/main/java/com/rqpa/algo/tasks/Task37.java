package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.stream.LongStream;

import org.slf4j.Logger;

import com.rqpa.algo.math.MathUtil;
import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;
import com.rqpa.algo.primes.PrimeNumberChecker;
import com.rqpa.algo.primes.PrimeNumbersProvider;

/*
The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class Task37
{
    private static final Logger logger = getLogger(Task37.class);

    private static final int TRUNCATABLE_PRIMES_COUNT = 11;

    public static void main(String[] args)
    {
        System.out.println(findSumOfTruncatablePrimes());
    }

    public static long findSumOfTruncatablePrimes()
    {
        PrimeNumbersProvider primeNumbersProvider = BruteForcePrimeNumbersProvider.instance;
        long maxPrimeExcl = 10;
        long[] knownPrimes = primeNumbersProvider.getPrimesLessThan(maxPrimeExcl);
        int nextPrimeIdx = knownPrimes.length; // Skip single digit primes
        int nextTruncatablePrimeIdx = 0;
        long[] truncatablePrimes = new long[TRUNCATABLE_PRIMES_COUNT];

        while (nextTruncatablePrimeIdx < truncatablePrimes.length)
        {
            while (nextPrimeIdx >= knownPrimes.length)
            {
                maxPrimeExcl *= 5;
                knownPrimes = primeNumbersProvider.getPrimesLessThan(maxPrimeExcl);
            }

            long prime = knownPrimes[nextPrimeIdx++];
            if (isTruncatablePrime(prime, primeNumbersProvider))
            {
                truncatablePrimes[nextTruncatablePrimeIdx++] = prime;
                logger.trace("{}", prime);
            }
        }

        return LongStream.of(truncatablePrimes).sum();
    }

    private static boolean isTruncatablePrime(long prime, PrimeNumberChecker primesChecker)
    {
        long digits = 0;
        long primeCopy = prime;
        while (primeCopy > 0)
        {
            if (!primesChecker.isPrime(primeCopy))
            {
                return false;
            }
            primeCopy /= 10;
            digits++;
        }

        long divisor = MathUtil.pow(10, digits - 1);
        primeCopy = prime;
        while (divisor > 0)
        {
            if (!primesChecker.isPrime(primeCopy))
            {
                return false;
            }
            primeCopy = primeCopy % divisor;
            divisor /= 10;
        }

        return true;
    }
}
