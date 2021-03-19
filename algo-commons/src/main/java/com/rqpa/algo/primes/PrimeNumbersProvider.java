package com.rqpa.algo.primes;

public interface PrimeNumbersProvider
{
    long[] getPrimesLessThan(long n);
    boolean isPrime(long n);
}
