package com.rqpa.algo.primes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractPrimeNumbersProviderTestCase extends AbstractPrimeNumberCheckerTestCase
{
    protected abstract PrimeNumbersProvider createProvider();

    @Override
    protected PrimeNumberChecker createChecker()
    {
        return createProvider();
    }

    @Test
    public void testGetPrimesLessThan200()
    {
        long[] expected = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
                59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131,
                137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199 };
        long[] actual = createProvider().getPrimesLessThan(200);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetPrimesLessThan199()
    {
        long[] expected = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
                59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131,
                137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197 };
        long[] actual = createProvider().getPrimesLessThan(199);

        Assertions.assertArrayEquals(expected, actual);
    }
}
