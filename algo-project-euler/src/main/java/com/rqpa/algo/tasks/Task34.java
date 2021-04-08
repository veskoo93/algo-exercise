package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.function.LongConsumer;

import org.slf4j.Logger;

import com.rqpa.algo.combinatorics.CombinationsGenerator;
import com.rqpa.algo.math.MathUtil;
import com.rqpa.algo.math.NumbersUtil;

/*
145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: As 1! = 1 and 2! = 2 are not sums they are not included.
 */
public class Task34
{
    private static final Logger logger = getLogger(Task30.class);

    public static void main(String[] args)
    {
        System.out.println(sumOfAllNumbersEqualToSumOfFifthPowersOfTheirDigits());
    }


    public static long sumOfAllNumbersEqualToSumOfFifthPowersOfTheirDigits()
    {
        long[] digitsAtFactorial = new long[10];
        digitsAtFactorial[0] = 1;
        for (int i = 1; i < digitsAtFactorial.length; i++)
        {
            digitsAtFactorial[i] = i * digitsAtFactorial[i - 1];
        }

        int digitsCount = 1;
        SumHolder sum = new SumHolder();
        Integer[] digits = new Integer[10];
        Arrays.setAll(digits, i -> i);
        // The number value grows exponentially with respect to the digits count. The sum of factorials grows linearly
        // with respect to the digits count. So if the minNumber with digitsCount digits is bigger than the maxSum of
        // factorials it is known that no match can be achieved with this count of digits or higher.
        while (MathUtil.pow(10, digitsCount - 1) <= digitsCount * digitsAtFactorial[9])
        {
            digitsCount++;

            CombinationsGenerator.instance.generateCombinationsWithRepetition(digits, digitsCount, comb -> {
                appendCombinationIfApplicable(digitsAtFactorial, comb, sum);
                return true;
            });
        }

        return sum.getSum();
    }

    private static void appendCombinationIfApplicable(long[] digitsAtFactorial, Integer[] currentCombination, SumHolder sumHolder)
    {

        long sumOfDigitsAtFactorial = 0;
        for (int i : currentCombination)
        {
            sumOfDigitsAtFactorial += digitsAtFactorial[i];
        }
        byte[] digitsOfSum = NumbersUtil.getDigits(sumOfDigitsAtFactorial);
        if (digitsOfSum.length != currentCombination.length)
        {
            return;
        }
        for (int combinationItem : currentCombination)
        {
            boolean itemFound = false;
            for (int i = 0; i < digitsOfSum.length; i++)
            {
                if (digitsOfSum[i] == combinationItem)
                {
                    digitsOfSum[i] = -1;
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound)
            {
                return;
            }
        }
        // All combination items have a corresponding digit. Add the number to the sum total
        logger.trace("Item found: {}", sumOfDigitsAtFactorial);
        sumHolder.accept(sumOfDigitsAtFactorial);
    }

    private static class SumHolder implements LongConsumer
    {
        private long sum = 0;

        @Override
        public void accept(long value)
        {
            sum += value;
        }

        public long getSum()
        {
            return sum;
        }
    }

}
