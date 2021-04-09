package com.rqpa.algo.tasks;

import java.util.Arrays;

import com.rqpa.algo.math.NumbersUtil;

/*
It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
public class Task52
{
    public static void main(String[] args)
    {
        System.out.println(solution());
    }

    public static long solution()
    {
        for (long i = 1; i < Long.MAX_VALUE; i++)
        {
            byte[] digitsSorted = NumbersUtil.getDigits(i);
            byte[] digitsSorted6 = NumbersUtil.getDigits(i * 6);

            if (digitsSorted.length != digitsSorted6.length)
            {
                continue;
            }

            Arrays.sort(digitsSorted);
            Arrays.sort(digitsSorted6);
            if (!Arrays.equals(digitsSorted, digitsSorted6))
            {
                continue;
            }

            boolean mismatchFound = false;
            for (int j = 2; j <= 5; j++)
            {
                byte[] digitsSortedJ = NumbersUtil.getDigits(i * j);
                Arrays.sort(digitsSortedJ);

                if (!Arrays.equals(digitsSorted, digitsSortedJ))
                {
                    mismatchFound = true;
                    break;
                }
            }

            if (!mismatchFound)
            {
                return i;
            }
        }

        throw new RuntimeException("Not found!");
    }
}
