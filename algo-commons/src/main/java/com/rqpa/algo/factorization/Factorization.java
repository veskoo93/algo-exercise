package com.rqpa.algo.factorization;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;

public abstract class Factorization
{
    private Factorization()
    {
        // Util class
    }

    public static Map<Long, Long> factorizeToPrimes(long n)
    {
        Map<Long, Long> primeToPower = new TreeMap<>();
        long factoring = n;
        long maxPrime = 1;
        while (factoring != 1)
        {
            maxPrime = Math.min(factoring + 1, 10 * maxPrime);
            long[] primesLtN = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(maxPrime);
            for (long prime : primesLtN)
            {
                long primePower = 0;
                while (factoring % prime == 0)
                {
                    factoring /= prime;
                    primePower += 1;
                }
                if (primePower > 0)
                {
                    primeToPower.put(prime, primePower);
                }
                if (factoring == 1)
                {
                    break;
                }
            }
        }

        return primeToPower;
    }

    public static Set<Long> getDivisors(long n)
    {
        Map<Long, Long> primeFactors = factorizeToPrimes(n);
        Set<Long> divisors = new TreeSet<>();
        divisors.add(1l);
        for (Map.Entry<Long, Long> primeToPowerEntry : primeFactors.entrySet())
        {
            Long prime = primeToPowerEntry.getKey();
            Long power = primeToPowerEntry.getValue();

            for (long i = 0; i < power; i++)
            {
                Long[] multipliedDivisors = new Long[divisors.size()];
                int multDivisorIdx = 0;
                for (Long divisor : divisors)
                {
                    multipliedDivisors[multDivisorIdx++] = divisor * prime;
                }
                Collections.addAll(divisors, multipliedDivisors);
            }
        }
        divisors.add(n);



        return divisors;
    }
}
