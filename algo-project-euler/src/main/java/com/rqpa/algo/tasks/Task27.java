package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;
import com.rqpa.algo.primes.PrimeNumbersProvider;

/*
Euler discovered the remarkable quadratic formula: n^2 + n + 41

It turns out that the formula will produce 40 primes for the consecutive integer values 0 <= n <= 39.
However, when n = 40, 40^2 + 40 + 41 = 40(40+1) + 41 is divisible by 41, and certainly when n = 41, 41^2 + 41 + 41 is clearly divisible by 41.

The incredible formula n^2 - 79n + 1601 was discovered, which produces 80 primes for the consecutive values 0 <= n <= 79.
 The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:
    n^2 + an + b, where |a| < 1000 and |b| < 1000
    where |n| is the modulus/absolute value of n

Find the product of the coefficients, a and b,
for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with 0.
 */
public class Task27
{
    private static final Logger logger = getLogger(Task27.class);

    public static void main(String[] args)
    {
        System.out.println(findTheProverbialProduct());
    }
    public static long findTheProverbialProduct()
    {
        return findTheProverbialProduct(BruteForcePrimeNumbersProvider.instance);
    }

    private static long findTheProverbialProduct(PrimeNumbersProvider primeNumbersProvider)
    {
        int maxN = -1;
        int aAtMaxN = -1000;
        int bAtMaxN = -1000;

        for (int a = -999; a <= 999; a++)
        {
            for(int b = -999; b <= 999; b++)
            {
                // Start checks at maxN + 1. If these coefficients can not produce a prime for maxN + 1 then
                // they can not produce a longer streak at all
                boolean streakContinues = true;
                for (int n = maxN + 1; n >= 0; n--)
                {
                    long valueAtN = evaluateFormula(a, b, n);
                    if (!primeNumbersProvider.isPrime(valueAtN))
                    {
                        streakContinues = false;
                        break;
                    }
                }

                if (!streakContinues)
                {
                    continue;
                }

                // Coefficients produce a prime streak for at least n in [0, maxN + 1]. Check how further can we go.
                aAtMaxN = a;
                bAtMaxN = b;
                maxN++;
                while(primeNumbersProvider.isPrime(evaluateFormula(a, b, maxN + 1)))
                {
                    maxN++;
                }
            }
        }

        logger.trace("Max A: {} Max B: {} Max N: {}", aAtMaxN, bAtMaxN, maxN);
        return aAtMaxN * bAtMaxN;
    }

    private static long evaluateFormula(int a, int b, int n)
    {
        return n * (a + n) + b;
    }
}
