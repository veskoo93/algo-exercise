package com.rqpa.algo.tasks;

import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.rqpa.algo.tuple.Tuple;

/*
The Fibonacci sequence is defined by the recurrence relation:

Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
Hence the first 12 terms will be:

F1 = 1
F2 = 1
F3 = 2
F4 = 3
F5 = 5
F6 = 8
F7 = 13
F8 = 21
F9 = 34
F10 = 55
F11 = 89
F12 = 144
The 12th term, F12, is the first term to contain three digits.

What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class Task25
{

    public static void main(String[] args)
    {
        Tuple<Long, BigInteger> fib = getResult();
        System.out.println("FIB " + fib.getFirst() + " > " + fib.getSecond());
    }

    public static Tuple<Long, BigInteger> getResult()
    {
        return getFirstFibonacciWithNDigits(1000);
    }

    private static Tuple<Long, BigInteger> getFirstFibonacciWithNDigits(int n)
    {
        return Stream.generate(new FibonacciWithIndexSupplier())
                .filter(t -> t.getSecond().toString().length() >= n)
                .findFirst()
                .orElse(null);
    }


    private static class FibonacciWithIndexSupplier implements Supplier<Tuple<Long, BigInteger>>
    {
        private Tuple<Long, BigInteger> fibMinus2 = Tuple.of(-1l, BigInteger.ZERO);
        private Tuple<Long, BigInteger> fibMinus1 = Tuple.of(0l, BigInteger.ZERO);
        private Tuple<Long, BigInteger> fib = Tuple.of(1l, BigInteger.ONE);

        @Override
        public Tuple<Long, BigInteger> get()
        {
            fibMinus2 = fibMinus1;
            fibMinus1 = fib;
            fib = Tuple.of(
                    fibMinus1.getFirst() + 1l,
                    fibMinus1.getSecond().add(fibMinus2.getSecond())
            );
            return fibMinus1;
        }
    }

}
