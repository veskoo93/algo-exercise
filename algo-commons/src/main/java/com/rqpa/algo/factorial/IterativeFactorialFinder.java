package com.rqpa.algo.factorial;

public class IterativeFactorialFinder implements FactorialFinder
{
    public static final IterativeFactorialFinder instance = new IterativeFactorialFinder();

    private IterativeFactorialFinder()
    {
        // Singleton
    }

    @Override
    public long findFactorial(long n)
    {
        long factorial = 1;
        for (int i = 2; i <= n; i++)
        {
            factorial *= i;
        }
        return factorial;
    }
}
