package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.LinkedHashSet;

import org.slf4j.Logger;

import com.rqpa.algo.math.NumbersUtil;

/*
Take the number 192 and multiply it by each of 1, 2, and 3:

192 × 1 = 192
192 × 2 = 384
192 × 3 = 576
By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
 */
public class Task38
{
    private static final Logger logger = getLogger(Task38.class);

    /*
    n > 1 therefore we must have at least 2 products which will have AT MOST 5 digits. Therefore we set the max number to 5 nines => 99_999
     */
    private static final int MAX_NUMBER = 99_999;

    public static void main(String[] args)
    {
        System.out.println(largestPandigitalConcatenatedProduct());
    }

    public static int largestPandigitalConcatenatedProduct()
    {
        int largestNumber = 0;
        LinkedHashSet<Byte> iterationDigits = new LinkedHashSet<>();
        for (int i = 1; i <= MAX_NUMBER; i++)
        {
            boolean repeatedDigitOrZeroFound = false;
            int multiplier = 1;
            while (iterationDigits.size() < 9)
            {
                int product = i * multiplier++;
                byte[] productDigits = NumbersUtil.getDigits(product);

                for (byte productDigit : productDigits)
                {
                    if (!iterationDigits.add(productDigit) || productDigit == 0)
                    {
                        // Digit is repeated!
                        repeatedDigitOrZeroFound = true;
                        break;
                    }
                }

                if (repeatedDigitOrZeroFound)
                {
                    break;
                }
            }

            if (iterationDigits.size() == 9 && !repeatedDigitOrZeroFound)
            {
                byte[] digitsArr = new byte[iterationDigits.size()];
                int idx = 0;
                for (byte digit : iterationDigits)
                {
                    digitsArr[idx++] = digit;
                }
                int currentIterationNumber = NumbersUtil.intFromDigits(digitsArr);
                logger.info("{} produces {}", i, currentIterationNumber);
                largestNumber = Math.max(currentIterationNumber, largestNumber);
            }

            iterationDigits.clear();
        }

        return largestNumber;
    }
}
