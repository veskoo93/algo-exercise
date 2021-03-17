package com.rqpa.algo.fibonacci;

import java.util.stream.IntStream;

public interface FibonacciNumbersSupplier
{
    int getNthNumber(int n);
    IntStream streamFromNth(int n);
}
