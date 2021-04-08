package com.rqpa.algo.math;

import java.util.Arrays;

public class MathUtil
{
    private MathUtil()
    {
        // Util
    }

    public static long pow(long base, long power)
    {
        if (power < 0)
        {
            throw new IllegalArgumentException();
        }

        long result = 1l;
        for (int i = 0; i < power; i++)
        {
            result *= base;
        }

        return result;
    }

}
