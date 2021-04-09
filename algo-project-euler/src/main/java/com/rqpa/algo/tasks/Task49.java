package com.rqpa.algo.tasks;

import java.util.Arrays;

import com.rqpa.algo.math.NumbersUtil;
import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;

/*
The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways:
(i) each of the three terms are prime, and,
(ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
 */
public class Task49
{
    public static void main(String[] args)
    {
        System.out.println(solution());
    }

    public static long solution()
    {
        long[] primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(10_000);
        byte[] solutionDigits = new byte[12];
        for (int i = 0; i < primes.length; i++)
        {
            long n1 = primes[i];
            if (n1 < 1_000 || n1 == 1487)
            {
                continue;
            }
            long n2 = n1 + 3330;
            long n3 = n2 + 3330;

            if (n3 >= 10_000)
            {
                break;
            }

            if (Arrays.binarySearch(primes, n2) < 0 || Arrays.binarySearch(primes, n3) < 0)
            {
                continue;
            }

            byte[] digits1 = NumbersUtil.getDigits(n1);
            Arrays.sort(digits1);
            byte[] digits2 = NumbersUtil.getDigits(n2);
            Arrays.sort(digits2);
            if (!Arrays.equals(digits1, digits2))
            {
                continue;
            }

            byte[] digits3 = NumbersUtil.getDigits(n3);
            Arrays.sort(digits3);
            if (!Arrays.equals(digits1, digits3))
            {
                continue;
            }

            System.arraycopy(NumbersUtil.getDigits(n1), 0, solutionDigits, 0, 4);
            System.arraycopy(NumbersUtil.getDigits(n2), 0, solutionDigits, 4, 4);
            System.arraycopy(NumbersUtil.getDigits(n3), 0, solutionDigits, 8, 4);
        }

        return NumbersUtil.longFromDigits(solutionDigits);
    }
}
