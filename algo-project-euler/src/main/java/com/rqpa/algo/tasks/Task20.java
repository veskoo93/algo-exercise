package com.rqpa.algo.tasks;

import java.math.BigInteger;

/*
n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!
 */
public class Task20
{
    public static void main(String[] args)
    {
        System.out.println(findSumOfDigitsIn100Factorial());
    }

    public static long findSumOfDigitsIn100Factorial()
    {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= 100; i++)
        {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        int charsSum = factorial.toString().chars()
                .map(c -> c - '0')
                .sum();
        return charsSum;
    }
}
