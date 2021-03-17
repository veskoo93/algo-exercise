package com.rqpa.algo.fibonacci;

public class IterativeFibonacciNumbersSupplierTestCase extends AbstractFibonacciNumbersSupplierTestCase
{
    @Override
    protected FibonacciNumbersSupplier createSupplier()
    {
        return IterativeFibonacciSupplier.instance;
    }
}
