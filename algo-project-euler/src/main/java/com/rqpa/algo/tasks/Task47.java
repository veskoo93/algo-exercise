package com.rqpa.algo.tasks;

import java.util.Map;

import com.rqpa.algo.factorization.Factorization;

/*
The first two consecutive numbers to have two distinct prime factors are:

14 = 2 × 7
15 = 3 × 5

The first three consecutive numbers to have three distinct prime factors are:

644 = 2² × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19.

Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
 */
public class Task47
{
    public static void main(String[] args)
    {
        System.out.println(solution());
    }

    public static long solution()
    {
        for (long i = 14; i < Integer.MAX_VALUE; i++)
        {
            Map<Long, Long> factors = Factorization.factorizeToPrimes(i);
            if (factors.size() != 4)
            {
                continue;
            }

            boolean seqHasNumberWithFactorCountNe4 = false;
            for (int j = 0; j < 3; j++)
            {
                if (Factorization.factorizeToPrimes(++i).size() != 4)
                {
                    seqHasNumberWithFactorCountNe4 = true;
                    break;
                }
            }

            if (!seqHasNumberWithFactorCountNe4)
            {
                return i - 3;
            }
        }

        throw new RuntimeException("Not found!");
    }
}
