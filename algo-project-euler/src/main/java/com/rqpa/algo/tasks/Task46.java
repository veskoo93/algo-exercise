package com.rqpa.algo.tasks;

import java.util.Arrays;

import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;

/*
It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

9 = 7 + 2×1^2
15 = 7 + 2×2^2
21 = 3 + 2×3^2
25 = 7 + 2×3^2
27 = 19 + 2×2^2
33 = 31 + 2×1^2

It turns out that the conjecture was false.

What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */
public class Task46
{

    public static void main(String[] args)
    {
        System.out.println(findSmallestOddComposite());
    }

    public static long findSmallestOddComposite()
    {
        long[] primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(10_000);

        for (long i = 9; i <= Long.MAX_VALUE; i += 2)
        {
            if (Arrays.binarySearch(primes, i) >= 0)
            {
                continue;
            }
            
            long squareRootOfIDiv2 = Double.valueOf(Math.ceil(Math.sqrt((i + 1) / 2))).longValue();

            boolean isRepresentable = false;
            for (int j = 1; j < squareRootOfIDiv2; j++)
            {
                long primeCandidate = i - (2 * j * j);
                if (primeCandidate == 1 || Arrays.binarySearch(primes, primeCandidate) >= 0)
                {
                    isRepresentable = true;
                    break;
                }
            }

            if (!isRepresentable)
            {
                return i;
            }
        }

        throw new RuntimeException("Not found!");
    }
}
