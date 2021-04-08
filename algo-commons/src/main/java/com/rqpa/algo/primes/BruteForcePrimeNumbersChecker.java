package com.rqpa.algo.primes;

public class BruteForcePrimeNumbersChecker implements PrimeNumberChecker
{
    private final PrimeNumbersProvider primeNumbersProvider;

    public BruteForcePrimeNumbersChecker(PrimeNumbersProvider primeNumbersProvider)
    {
        this.primeNumbersProvider = primeNumbersProvider;
    }

    @Override
    public boolean isPrime(long n)
    {
        if (n == 1)
        {
            return false;
        }
        long sqrtPlus1 = Math.round(Math.sqrt(n)) + 1;
        long[] primesLessThanSqrt = primeNumbersProvider.getPrimesLessThan(sqrtPlus1);
        for (int i = 0; i < primesLessThanSqrt.length; i++)
        {
            if (n % primesLessThanSqrt[i] == 0)
            {
                return false;
            }
        }

        return true;
    }
}
