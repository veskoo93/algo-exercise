package com.rqpa.algo.tasks;

import com.rqpa.algo.primes.EratosthenesSievePrimeNumbersProvider;

/*
2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Task5
{
    public static void main(String[] args)
    {
        System.out.println(findSmallestPositiveDisibleByNumbersFrom1To20());
    }

    public static long findSmallestPositiveDisibleByNumbersFrom1To20()
    {
        long[] primesLte20 = EratosthenesSievePrimeNumbersProvider.instance.getPrimesLessThan(21);
        long primesProduct = 1;
        for (long prime : primesLte20)
        {
            primesProduct *= prime;
        }

        long currentNumber = primesProduct;
        while (true)
        {
            if (isDivisibleByRange(currentNumber, 2, 21))
            {
                return currentNumber;
            }
            currentNumber += primesProduct;
        }
    }

    private static boolean isDivisibleByRange(long n, long fromIncl, long toExcl)
    {
        for (long i = fromIncl; i < toExcl; i++)
        {
            if (n % i != 0)
            {
                return false;
            }
        }
        return true;
    }
}
