package com.rqpa.algo.fibonacci;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class IterativeFibonacciSupplier implements FibonacciNumbersSupplier
{
    public static final IterativeFibonacciSupplier instance = new IterativeFibonacciSupplier();

    private IterativeFibonacciSupplier()
    {
        // Singleton
    }

    @Override
    public int getNthNumber(int n)
    {
        NumbersSupplier supplier = new NumbersSupplier();
        for (int i = 0; i < n - 1; i++)
        {
            supplier.getAsInt();
        }
        return supplier.getAsInt();
    }

    @Override
    public IntStream streamFromNth(int n)
    {
        NumbersSupplier supplier = new NumbersSupplier();
        for (int i = 0; i < n - 1; i++)
        {
            supplier.getAsInt();
        }
        return IntStream.generate(supplier);
    }

    private static class NumbersSupplier implements IntSupplier {

        private int fibMinus2 = 0;
        private int fibMinus1 = 1;

        private int nextNumberOrder = 1;

        @Override
        public int getAsInt()
        {
            int result;
            if (nextNumberOrder == 1)
            {
                result = 0;
            }
            else if (nextNumberOrder == 2)
            {
                result = 1;
            }
            else
            {
                fibMinus1 = fibMinus1 + fibMinus2;
                fibMinus2 = fibMinus1 - fibMinus2;
                result = fibMinus1;
            }
            nextNumberOrder++;
            return result;
        }

        public int getNextNumberOrder()
        {
            return nextNumberOrder;
        }
    }
}
