package com.rqpa.algo.primes;

public class WilsonTheoremPrimeNumberCheckerTestCase extends AbstractPrimeNumberCheckerTestCase
{
    @Override
    protected PrimeNumberChecker createChecker()
    {
        return WilsonTheoremPrimeChecker.instance;
    }
}
