package com.rqpa.algo.tasks;

import java.util.LinkedList;
import java.util.stream.Stream;

/*
2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000?
 */
public class Task16
{
    public static void main(String[] args)
    {
        System.out.println(sumDigitsOf2Pow1000());
    }

    public static int sumDigitsOf2Pow1000()
    {
        String number = "1";

        for (int i = 0; i < 1000; i++)
        {
            int overflow = 0;
            LinkedList<Character> resultDigits = new LinkedList<>();
            for (int j = 0; j < number.length(); j++)
            {
                int digit = Character.digit(number.charAt(number.length() - j - 1), 10);
                overflow = overflow + digit + digit;
                resultDigits.addFirst(Character.forDigit(overflow % 10, 10));
                overflow /= 10;
            }

            StringBuilder resultSb = new StringBuilder();
            if (overflow > 0)
            {
                resultSb.append(overflow);
            }
            resultDigits.forEach(resultSb::append);
            number = resultSb.toString();
        }

        int sumOfDigits = number.chars()
                .map(c -> c - '0')
                .sum();
        return sumOfDigits;
    }
}
