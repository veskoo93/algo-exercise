package com.rqpa.algo.primes;

public interface PrimeNumbersProvider extends PrimeNumberChecker
{
    long[] getPrimesLessThan(long n);
}
