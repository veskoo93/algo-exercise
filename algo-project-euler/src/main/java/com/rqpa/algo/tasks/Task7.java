package com.rqpa.algo.tasks;

import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;
import com.rqpa.algo.primes.PrimeNumbersProvider;

/*
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number?
 */
public class Task7
{
    public static void main(String[] args)
    {
        System.out.println(find10001stPrime());
    }

    public static long find10001stPrime()
    {
        PrimeNumbersProvider primesProvider = BruteForcePrimeNumbersProvider.instance;

        long[] primes = new long[0];
        long maxPrime = 50_000;
        while (primes.length < 10_001)
        {
            maxPrime *= 2;
            primes = primesProvider.getPrimesLessThan(maxPrime);
        }
        return primes[10_000];
    }
}
