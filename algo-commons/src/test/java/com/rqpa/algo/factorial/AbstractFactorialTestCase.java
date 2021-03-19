package com.rqpa.algo.factorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractFactorialTestCase
{
    protected abstract FactorialFinder createFinder();

    @Test
    public void test1Fact()
    {
        doTestFactorial(1, 1);
    }

    @Test
    public void test2Fact()
    {
        doTestFactorial(2, 2);
    }

    @Test
    public void test5Fact()
    {
        doTestFactorial(5, 120);
    }

    private void doTestFactorial(long n, long expectedFactorial)
    {
        long actualFactorial = createFinder().findFactorial(n);
        Assertions.assertEquals(expectedFactorial, actualFactorial);
    }
}
