package com.rqpa.algo.tasks;

import java.util.stream.LongStream;

import com.rqpa.algo.math.MathUtil;
import com.rqpa.algo.math.NumbersUtil;

/*
The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class Task36
{
    public static void main(String[] args)
    {
        System.out.println(findSumOfPalindromicDecAndBinaryLessThanMillion());
    }

    public static long findSumOfPalindromicDecAndBinaryLessThanMillion()
    {
        return findSumOfPalindromicDecAndBinaryWithMaxDigits(6);
    }

    private static long findSumOfPalindromicDecAndBinaryWithMaxDigits(int maxDigitsIncl)
    {
        // Single digit numbers are palindromes
        long palindromesSum = LongStream.rangeClosed(1, 9)
                .filter(Task36::isBinaryPalindrome)
                .sum();

        for (int digitsCount = 2; digitsCount <= maxDigitsIncl; digitsCount += 2)
        {
            boolean generateWithOddDigits = digitsCount + 1 < maxDigitsIncl;

            long minSide = MathUtil.pow(10, digitsCount / 2 - 1);
            long maxSide = MathUtil.pow(10, digitsCount / 2);

            for (long leftSide = minSide; leftSide < maxSide; leftSide++)
            {
                long leftSideCopy = leftSide;

                long leftSideDigitMultiplier = minSide;
                long rightSide = 0;
                while (leftSideCopy != 0)
                {
                    rightSide += ((leftSideCopy % 10) * leftSideDigitMultiplier);
                    leftSideDigitMultiplier /= 10;
                    leftSideCopy /= 10;
                }

                long palindrome = leftSide * maxSide + rightSide;
                if (isBinaryPalindrome(palindrome))
                {
                    palindromesSum += palindrome;
                }

                if (!generateWithOddDigits)
                {
                    continue;
                }

                for (int i = 0; i <= 9; i++)
                {
                    palindrome = (leftSide * 10 + i) * maxSide + rightSide;
                    if (isBinaryPalindrome(palindrome))
                    {
                        palindromesSum += palindrome;
                    }
                }
            }
        }

        return palindromesSum;
    }

    private static boolean isBinaryPalindrome(long n)
    {
        if (n % 2 == 0)
        {
            // Even numbers can not be considered binary palindromes as they always end with a zero and leading zeroes are trimmed.
            return false;
        }

        byte[] nInBinary = NumbersUtil.getDigits(n, 2);
        return isPalindrome(nInBinary);
    }

    private static boolean isPalindrome(byte[] arr)
    {
        for (int i = 0; i < arr.length / 2; i++)
        {
            if (arr[i] != arr[arr.length - i - 1])
            {
                return false;
            }
        }
        return true;
    }
}
