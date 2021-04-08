package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;

import com.rqpa.algo.sorting.SelectionSortAlgo;

/*
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
public class Task32
{
    private static final Logger logger = getLogger(Task32.class);

    public static void main(String[] args)
    {
        System.out.println(findSumOfUniquePandigitalProducts());
    }

    public static long findSumOfUniquePandigitalProducts()
    {
        Set<Integer> products = new HashSet<>();
        int maxProduct = 999_999_999;
        // Multiplier will always be greater or equal to multiplicand so we can limit multiplicand to sqrt(maxProduct) + 1
        int maxMultiplicand = Double.valueOf(Math.ceil(Math.sqrt(maxProduct))).intValue();

        for (int multiplicand = 1; multiplicand <= maxMultiplicand; multiplicand++)
        {
            int maxMultiplier = maxProduct / multiplicand + 1;
            int[] multiplicandDigits = getDigits(multiplicand);
            for (int multiplier = multiplicand; multiplier <= maxMultiplier; multiplier++)
            {
                int product = multiplicand * multiplier;
                int[] multiplierDigits = getDigits(multiplier);
                int[] productDigits = getDigits(product);

                // Check if pandigital
                int totalDigits = multiplicandDigits.length + multiplierDigits.length + productDigits.length;
                if (totalDigits > 9)
                {
                    // We will get larger and larger products => totalDigits will continue to be > 9
                    break;
                }
                if(totalDigits < 9)
                {
                    // Can not be pandigital 1-9 without having exactly 9 digits
                    continue;
                }

                int[] allDigits = new int[9];
                System.arraycopy(multiplicandDigits, 0, allDigits, 0, multiplicandDigits.length);
                System.arraycopy(multiplierDigits, 0, allDigits, multiplicandDigits.length, multiplierDigits.length);
                System.arraycopy(productDigits, 0, allDigits, multiplicandDigits.length + multiplierDigits.length, productDigits.length);
                Arrays.sort(allDigits);
                boolean isPandigital = true;
                for (int i = 0; i < allDigits.length; i++)
                {
                    if (allDigits[i] != i + 1)
                    {
                        isPandigital = false;
                        break;
                    }
                }

                if (isPandigital)
                {
                    logger.trace("{} * {} = {}", multiplicand, multiplier, product);
                    products.add(product);
                }
            }
        }

        return products.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }

    private static int[] getDigits(int n)
    {
        int[] digits = new int[10]; // Integer.MAX_VALUE has 10 digits
        int idx = digits.length - 1;
        while (n > 0)
        {
            digits[idx--] = n % 10;
            n /= 10;
        }
        return Arrays.copyOfRange(digits, idx + 1, digits.length);
    }
}
