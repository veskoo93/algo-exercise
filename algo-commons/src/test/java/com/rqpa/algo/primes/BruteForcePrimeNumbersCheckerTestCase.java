package com.rqpa.algo.primes;

public class BruteForcePrimeNumbersCheckerTestCase extends AbstractPrimeNumberCheckerTestCase
{
    @Override
    protected PrimeNumberChecker createChecker()
    {
        return new BruteForcePrimeNumbersChecker(BruteForcePrimeNumbersProvider.instance);
    }
}
