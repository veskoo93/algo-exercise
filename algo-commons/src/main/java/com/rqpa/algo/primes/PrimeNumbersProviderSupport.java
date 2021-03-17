package com.rqpa.algo.primes;

import java.util.Arrays;

abstract class PrimeNumbersProviderSupport
{

    /**
     * Returns a new array with all primes less than n. This method assumes that the last known prime (knownPrimes[knownPrimes.length - 1]) is greater than or equal to n
     *
     * @param knownPrimes the array of known primes
     * @param n           the reference number
     * @return a new array with all primes that are less than n
     */
    public static long[] resolvePrimesArrayWithPrimesLessThan(long[] knownPrimes, long n){
        return resolvePrimesArrayWithPrimesLessThan(knownPrimes, 0, knownPrimes.length, n);
    }

    /**
     * Returns a new array with all primes less than n. This method assumes that the last known prime (knownPrimes[knownPrimes.length - 1]) is greater than or equal to n
     * @param knownPrimes the array of known primes
     * @param knownPrimesFrom the index where the known primes start (inclusive)
     * @param knownPrimesTo the index where the known primes end (exclusive)
     * @param n the reference number
     * @return a new array with all primes that are less than n
     */
    public static long[] resolvePrimesArrayWithPrimesLessThan(long[] knownPrimes, int knownPrimesFrom, int knownPrimesTo, long n)
    {
        int knownPrimeIndex = Arrays.binarySearch(knownPrimes, knownPrimesFrom, knownPrimesTo, n);
        if (knownPrimeIndex == 0) {
            // No primes less than this one.
            return new long[0];
        }
        if (knownPrimeIndex > 0) {
            return Arrays.copyOfRange(knownPrimes, 0, knownPrimeIndex);
        }

        // idx = -ip -1
        // -ip = idx +1
        // ip = -(idx+1)
        int primeInsertionPoint = - (knownPrimeIndex + 1);
        return Arrays.copyOfRange(knownPrimes, 0, primeInsertionPoint);
    }
}
