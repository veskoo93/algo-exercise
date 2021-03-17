package com.rqpa.algo.tasks;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;

/*
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.
 */
public class Task10
{

    public static void main(String[] args)
    {
        System.out.println(findSumOfAllPrimesBelowTwoMillions());
    }

    public static long findSumOfAllPrimesBelowTwoMillions()
    {
        long[] primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(2_000_000);
        long primesSum = Arrays.stream(primes).sum();
        return primesSum;
    }
}
