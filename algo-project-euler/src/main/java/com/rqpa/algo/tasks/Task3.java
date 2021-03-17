package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;
import com.rqpa.algo.primes.PrimeNumbersProvider;

/*
The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
 */
public class Task3
{
    private static final Logger logger = getLogger(Task3.class);

    public static void main(String[] args)
    {
        System.out.println(maxPrimeFactor());
    }

    public static long maxPrimeFactor()
    {
        long[] primesLessThan5Mil = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(10_000);
        long maxPrimeFactor = 0;
        long toFactor = 600851475143l;
        for (int i = 0; i < primesLessThan5Mil.length; i++)
        {
            long prime = primesLessThan5Mil[i];
            while (toFactor % prime == 0)
            {
                maxPrimeFactor = prime;
                long divided = toFactor / prime;
                logger.trace("{} / {} = {}", toFactor, prime, divided);
                toFactor = divided;
            }
        }
        return maxPrimeFactor;
    }
}
