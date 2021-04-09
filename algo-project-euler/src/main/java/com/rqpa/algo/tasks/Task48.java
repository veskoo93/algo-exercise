package com.rqpa.algo.tasks;

import java.math.BigInteger;

/*
The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.

Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
public class Task48
{
    public static void main(String[] args)
    {
        System.out.println(solution());
    }

    public static String solution()
    {
        BigInteger sum = new BigInteger("0");
        for (int i = 1; i <= 1_000; i++)
        {
            sum = sum.add(nToNth(i));
        }

        String sumString = sum.toString();
        return sumString.substring(sumString.length() - 10);
    }

    private static BigInteger nToNth(int n)
    {
        BigInteger base = BigInteger.valueOf(n);
        BigInteger powered = new BigInteger("1");
        for (int i = 0; i < n; i++)
        {
            powered = powered.multiply(base);
        }
        return powered;
    }
}
