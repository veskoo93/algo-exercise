package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;

import com.rqpa.algo.math.MathUtil;
import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;

/*
The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

How many circular primes are there below one million?
 */
public class Task35
{
    private static final Logger logger = getLogger(Task35.class);

    public static void main(String[] args)
    {
        System.out.println(countCircularPrimesBelow1Million());
//        System.out.println(countCircularPrimesLessThan(100));
    }

    public static long countCircularPrimesBelow1Million()
    {
        return countCircularPrimesLessThan(1_000_000);
    }


    private static long countCircularPrimesLessThan(int maxPrimeExcl)
    {
        Integer[] digits = new Integer[10];
        Arrays.setAll(digits, Integer::valueOf);

        Set<Long> circularPrimes = new TreeSet<>();
        long[] primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(maxPrimeExcl);
        for (long prime : primes)
        {
            Set<Long> currentIterationPrimes = new HashSet<>();
            currentIterationPrimes.add(prime);

            long primeCopy = prime;
            int digitsCount = 0;
            while (primeCopy > 0)
            {
                digitsCount += 1;
                primeCopy /= 10;
            }

            long multiplier = MathUtil.pow(10, digitsCount - 1);
            long offsetPrime = prime;
            boolean allPrimes = true;
            for (int i = 0; i < digitsCount - 1; i++)
            {
                long remainder = offsetPrime % 10;
                offsetPrime /= 10;
                offsetPrime = offsetPrime + remainder * multiplier;
                if (!BruteForcePrimeNumbersProvider.instance.isPrime(offsetPrime))
                {
                    allPrimes = false;
                    break;
                }
                currentIterationPrimes.add(offsetPrime);
            }

            if (allPrimes)
            {
                circularPrimes.addAll(currentIterationPrimes);
            }
        }

        circularPrimes.forEach(n -> logger.trace("{}", n));
        return circularPrimes.size();
    }

}
