package com.rqpa.algo.tasks;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.rqpa.algo.combinatorics.CombinationsGenerator;
import com.rqpa.algo.combinatorics.PermutationsGenerator;
import com.rqpa.algo.math.NumbersUtil;
import com.rqpa.algo.primes.BruteForcePrimeNumbersChecker;
import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;
import com.rqpa.algo.primes.PrimeNumberChecker;
import com.rqpa.algo.primes.WilsonTheoremPrimeChecker;

/*
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.

What is the largest n-digit pandigital prime that exists?
 */
public class Task41
{

    public static void main(String[] args)
    {
        System.out.println(findLargestNDigitPandigitalPrime());
    }

    public static long findLargestNDigitPandigitalPrime()
    {
        PrimeNumberChecker primeChecker = new BruteForcePrimeNumbersChecker(BruteForcePrimeNumbersProvider.instance);
        AtomicLong largestN = new AtomicLong(0);
        for (int n = 9; n >= 2; n--)
        {
            Byte[] digits = new Byte[n];
            for (int i = 0; i < digits.length; i++)
            {
                digits[i] = (byte) (i + 1);
            }
            PermutationsGenerator.instance.generatePermutations(digits, perm -> {
                byte[] digitsPrimitive = new byte[perm.length];
                for (int i = 0; i < perm.length; i++)
                {
                    digitsPrimitive[i] = perm[i];
                }
                long number = NumbersUtil.intFromDigits(digitsPrimitive);
                if (largestN.get() < number && primeChecker.isPrime(number))
                {
                    largestN.set(number);
                }
                return true;
            });
            if (largestN.get() > 0)
            {
                return largestN.get();
            }
        }

        throw new RuntimeException("Pandigital prime not found!");
    }
}
