package com.rqpa.algo.factorial;

public class IterativeFactorialFinderTestCase extends AbstractFactorialTestCase
{
    @Override
    protected FactorialFinder createFinder()
    {
        return IterativeFactorialFinder.instance;
    }
}
