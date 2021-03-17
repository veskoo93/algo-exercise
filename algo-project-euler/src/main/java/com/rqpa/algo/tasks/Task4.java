package com.rqpa.algo.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Task4
{
    public static void main(String[] args)
    {
        System.out.println(findLargestPalindromeOfTwoNumbersWith3Digits());
    }

    public static int findLargestPalindromeOfTwoNumbersWith3Digits()
    {
        List<Integer> palindromes = new ArrayList<>();
        for (int i = 999; i >= 100; i--)
        {
            for (int j = i; j >= 100; j--)
            {
                int multiplied = i * j;
                if (isPalindrome(multiplied))
                {
                    palindromes.add(multiplied);
                }
            }
        }
        palindromes.sort(Comparator.reverseOrder());
        return palindromes.get(0);
    }

    private static boolean isPalindrome(int multiplied)
    {
        int[] digits = getDigits(multiplied);
        int firstNonZeroDigitIdx = -1;
        for (int i = 0; i < digits.length; i++)
        {
            if (digits[i] != 0)
            {
                firstNonZeroDigitIdx = i;
                break;
            }
        }
        if (firstNonZeroDigitIdx < 0)
        {
            return true;
        }

        for (int i = firstNonZeroDigitIdx; i < digits.length; i++)
        {
            int correspondingDigitIndex = digits.length -1 - (i - firstNonZeroDigitIdx);
            if (digits[i] != digits[correspondingDigitIndex])
            {
                return false;
            }
        }
        return true;
    }

    private static int[] getDigits(int n)
    {
        int[] digits = new int[10]; // ints can be +- 2 000 000 000 so 10 digits max
        int currentDigitIndex = digits.length - 1;
        while (n > 0)
        {
            int lastDigit = n % 10;
            digits[currentDigitIndex--] = lastDigit;
            n /= 10;
        }

        return digits;
    }
}
