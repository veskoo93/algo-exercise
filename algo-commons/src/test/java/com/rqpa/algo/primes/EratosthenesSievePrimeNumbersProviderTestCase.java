package com.rqpa.algo.primes;

public class EratosthenesSievePrimeNumbersProviderTestCase extends AbstractPrimeNumbersProviderTestCase
{
    @Override
    protected PrimeNumbersProvider createProvider()
    {
        return EratosthenesSievePrimeNumbersProvider.instance;
    }
}
