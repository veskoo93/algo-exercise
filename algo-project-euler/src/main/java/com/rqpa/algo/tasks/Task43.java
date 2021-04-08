package com.rqpa.algo.tasks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.rqpa.algo.combinatorics.CombinationsGenerator;
import com.rqpa.algo.combinatorics.PermutationsGenerator;
import com.rqpa.algo.math.NumbersUtil;
import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;
import com.rqpa.algo.primes.PrimeNumbersProvider;

/*
The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

d2d3d4=406 is divisible by 2
d3d4d5=063 is divisible by 3
d4d5d6=635 is divisible by 5
d5d6d7=357 is divisible by 7
d6d7d8=572 is divisible by 11
d7d8d9=728 is divisible by 13
d8d9d10=289 is divisible by 17
Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class Task43
{
    public static void main(String[] args)
    {
        System.out.println(findSpecialPandigitalsSum());
    }

    public static long findSpecialPandigitalsSum()
    {
        Byte[] digits = new Byte[10];
        for (int i = 0; i < digits.length; i++)
        {
            digits[i] = (byte) i;
        }
        long[] primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(100);
        AtomicLong sum = new AtomicLong(0l);
        PermutationsGenerator.instance.generatePermutations(digits, perm -> {
            if (perm[0].equals(Byte.valueOf((byte) 0)))
            {
                return true;
            }

            for (int i = 0; i < 7; i++)
            {
                long number = 100 * perm[i + 1] + 10 * perm[i + 2] + perm[i + 3];
                if (number % primes[i] != 0)
                {
                    return true;
                }
            }

            byte[] numberDigits = new byte[10];
            for (int i = 0; i < perm.length; i++)
            {
                numberDigits[i] = perm[i];
            }
            sum.addAndGet(NumbersUtil.longFromDigits(numberDigits));
            return true;
        });

        return sum.get();
    }
}
