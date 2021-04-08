package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.function.LongConsumer;

import org.slf4j.Logger;

import com.rqpa.algo.combinatorics.CombinationsGenerator;
import com.rqpa.algo.math.MathUtil;
import com.rqpa.algo.math.NumbersUtil;

/*
Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

1634 = 1^4 + 6^4 + 3^4 + 4^4
8208 = 8^4 + 2^4 + 0^4 + 8^4
9474 = 9^4 + 4^4 + 7^4 + 4^4
As 1 = 1^4 is not a sum it is not included.

The sum of these numbers is 1634 + 8208 + 9474 = 19316.

Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
public class Task30
{
    private static final Logger logger = getLogger(Task30.class);

    public static void main(String[] args)
    {
        System.out.println(sumOfAllNumbersEqualToSumOfFifthPowersOfTheirDigits());
    }

    public static long sumOfAllNumbersEqualToSumOfFifthPowersOfTheirDigits()
    {
        return sumOfAllNumbersEqualToSumOfNthPowersOfTheirDigits(5);
    }

    private static long sumOfAllNumbersEqualToSumOfNthPowersOfTheirDigits(int power)
    {
        long[] digitsAtPower = new long[10];
        Arrays.setAll(digitsAtPower, digit -> MathUtil.pow(digit, power));

        int digitsCount = 1;
        SumHolder sum = new SumHolder();
        Integer[] digits = new Integer[10];
        Arrays.setAll(digits, i -> i);

        // The number value grows exponentially with respect to the digits count. The sum of powered digits grows linearly
        // with respect to the digits count. So if the minNumber with digitsCount digits is bigger than the maxSum of
        // powered digits it is known that no match can be achieved with this count of digits or higher.
        while (MathUtil.pow(10, digitsCount - 1) <= digitsCount * digitsAtPower[9])
        {
            digitsCount++;

            CombinationsGenerator.instance.generateCombinationsWithRepetition(digits, digitsCount, comb -> {
                appendCombinationIfApplicable(digitsAtPower, comb, sum);
                return true;
            });
        }

        return sum.getSum();
    }

    private static void appendCombinationIfApplicable(long[] digitsAtPower, Integer[] currentCombination, SumHolder sumHolder)
    {
        long sumOfDigitsAtPower = 0;
        for (int i : currentCombination)
        {
            sumOfDigitsAtPower += digitsAtPower[i];
        }
        byte[] digitsOfSum = NumbersUtil.getDigits(sumOfDigitsAtPower);
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
        logger.trace("Item found: {}", sumOfDigitsAtPower);
        sumHolder.accept(sumOfDigitsAtPower);
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
