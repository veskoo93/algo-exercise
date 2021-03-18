package com.rqpa.algo.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.LongUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.rqpa.algo.factorization.Factorization;

/*
Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.
 */
public class Task21
{
    public static void main(String[] args)
    {
//        System.out.println(isAmicable(new DFunction(), 220));
        System.out.println(findSumOfAllAmicableNumbersUnder10000());
    }

    public static long findSumOfAllAmicableNumbersUnder10000()
    {
        DFunction d = new DFunction();
        return LongStream.range(1, 10_000)
                .filter(n -> isAmicable(d, n))
                .sum();
    }

    private static boolean isAmicable(DFunction d, long n)
    {
        long da = d.applyAsLong(n);
        long db = d.applyAsLong(da);
        return n == db &&  da != n;
    }

    private static class DFunction implements LongUnaryOperator
    {
        private final Map<Long, Long> cache = new HashMap<>();

        @Override
        public long applyAsLong(long n)
        {
            return cache.computeIfAbsent(n, this::computeD);
        }

        private Long computeD(Long n)
        {
            Set<Long> divisors = Factorization.getDivisors(n);
            divisors.remove(n);
            long divisorsSum = divisors.stream()
                    .mapToLong(Long::longValue)
                    .sum();
            return divisorsSum;
        }
    }
}
