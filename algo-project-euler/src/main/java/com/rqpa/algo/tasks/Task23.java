package com.rqpa.algo.tasks;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.rqpa.algo.factorization.Factorization;

/*
A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class Task23
{
    public static void main(String[] args)
    {
        System.out.println(sumOfAllPositiveNumbersNotASumOfTwoAbundantNumbers());
    }

    public static long sumOfAllPositiveNumbersNotASumOfTwoAbundantNumbers()
    {
        long lastNonAbundantNumber = 28123;
        Long[] abundantNumbers = findAllAbundantBelow(lastNonAbundantNumber);
        long[] abundantNumbersPrimitive = new long[abundantNumbers.length];
        Arrays.setAll(abundantNumbersPrimitive, i -> abundantNumbers[i].longValue());
        return LongStream.rangeClosed(1, lastNonAbundantNumber)
                .filter(n -> !canBeRepresentedAsTwoNumbersSum(n, abundantNumbersPrimitive))
                .sum();
    }

    private static boolean canBeRepresentedAsTwoNumbersSum(long n, long[] allowedNumbers)
    {
        for (int i = 0; i < allowedNumbers.length; i++)
        {
            long toSearch = n - allowedNumbers[i];
            int foundIdx = Arrays.binarySearch(allowedNumbers, i, allowedNumbers.length, toSearch);
            if (foundIdx >= 0)
            {
                return true;
            }
        }
        return false;
    }


    private static Long[] findAllAbundantBelow(long maxAbundantNumberIncl)
    {
        List<Long> abundantNumbers = LongStream.rangeClosed(2, maxAbundantNumberIncl)
                .filter(Task23::isAbundant)
                .boxed()
                .collect(Collectors.toList());
        return abundantNumbers.toArray(Long[]::new);
    }

    private static boolean isAbundant(long n)
    {
        Set<Long> divisors = Factorization.getDivisors(n);
        divisors.remove(n);
        long divisorsSum = divisors.stream()
                .mapToLong(Long::longValue)
                .sum();
        return divisorsSum > n;
    }
}
