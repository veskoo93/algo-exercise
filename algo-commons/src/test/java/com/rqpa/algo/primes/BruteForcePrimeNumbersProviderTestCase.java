package com.rqpa.algo.primes;

public class BruteForcePrimeNumbersProviderTestCase extends AbstractPrimeNumbersProviderTestCase
{
    @Override
    protected PrimeNumbersProvider createProvider()
    {
        return BruteForcePrimeNumbersProvider.instance;
    }
}
