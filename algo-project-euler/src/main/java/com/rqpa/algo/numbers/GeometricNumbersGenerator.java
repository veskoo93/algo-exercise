package com.rqpa.algo.numbers;

public class GeometricNumbersGenerator
{
    public static long getTriangleNumber(long n)
    {
        return n * (n + 1) / 2;
    }

    public static long getPentagonalNumber(long n)
    {
        return n * (3 * n - 1) / 2;
    }

    public static long getHexagonalNumber(long n)
    {
        return n * (2 * n - 1);
    }
}
