package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import com.rqpa.algo.math.NumbersUtil;

/*
An irrational decimal fraction is created by concatenating the positive integers:

0.12345678910_1_112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
 */
public class Task40
{
    private static final Logger logger = getLogger(Task40.class);

    public static void main(String[] args)
    {
        System.out.println(findMagicValue());
    }

    public static int findMagicValue()
    {
        int product = 1;
        int n = 1;
        for (int i = 0; i <= 6; i++)
        {
            int digit = findNthDigit(n);
            product *= digit;
            n *= 10;
        }
        return product;
    }

    /*
    Digits 1-9 make 9 digits
    DIgits 10-99 make 90 numbers x 2 digits = 9 * 10 * 2
    Digits 100-999 make 900 numbers x 3 digits = 9 * 100 * 3
    Numbers 10^n - (10^(n+1) - 1) make 9 * 10^n * n+1 digits
     */
    private static int findNthDigit(int n)
    {
        if (n < 10)
        {
            return n;
        }
        
        int lastPowerOfTen = 0;
        int tenPowered = 1;
        int digitsAfterLastPower = 9;
        while(true)
        {
            tenPowered *= 10;
            lastPowerOfTen += 1;
            int digitsAfterNextPower = digitsAfterLastPower + (9 * tenPowered ) * (lastPowerOfTen + 1);
            if (digitsAfterNextPower > n)
            {
                break;
            }
            digitsAfterLastPower = digitsAfterNextPower;
        }
        if (digitsAfterLastPower == n)
        {
            return 9; // Last digit is always nine
        }

        int digitsPerNumber = lastPowerOfTen + 1;
        int digitInPowerIndex = n - digitsAfterLastPower - 1;
        // Each number makes n + 1 digits. Since the while is already finished, *lastPowerOfTen* and *tenPowered* are at power n + 1
        int number =  tenPowered + digitInPowerIndex / digitsPerNumber;
        byte[] numberDigits = NumbersUtil.getDigits(number);
        int digit = digitInPowerIndex % digitsPerNumber;
        return numberDigits[digit];
    }

}
