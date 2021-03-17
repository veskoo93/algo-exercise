package com.rqpa.algo.fibonacci;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractFibonacciNumbersSupplierTestCase
{
    protected abstract FibonacciNumbersSupplier createSupplier();

    private static final int[] firstNNumbers = new int[] {
            0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
    };

    @Test
    public void testFindNth()
    {
        FibonacciNumbersSupplier supplier = createSupplier();
        for (int i = 0; i < firstNNumbers.length; i++)
        {
            Assertions.assertEquals(firstNNumbers[i], supplier.getNthNumber(i + 1));
        }
    }

    @Test
    public void testStreamAfterNth()
    {
        FibonacciNumbersSupplier supplier = createSupplier();
        int n = firstNNumbers.length / 2;
        int[] expected = Arrays.copyOfRange(firstNNumbers, n - 1, firstNNumbers.length);
        Assertions.assertTrue(expected.length > 3);
        int[] actual = supplier.streamFromNth(n).limit(expected.length).toArray();
        Assertions.assertArrayEquals(expected, actual);
    }
}
