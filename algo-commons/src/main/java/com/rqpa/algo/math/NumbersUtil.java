package com.rqpa.algo.math;

import java.util.Arrays;

public class NumbersUtil
{
    private NumbersUtil()
    {
        // Util
    }


    public static byte[] getDigits(long n)
    {
        return getDigits(n, 10);
    }

    public static byte[] getDigits(long n, int radix)
    {
        byte[] digits = new byte[63]; // Long.MAX_VALUE has 63 binary digits. Higher radixes will have no more digits than that.
        int currentDigitIdx = digits.length - 1;
        while (Math.abs(n) > 0)
        {
            digits[currentDigitIdx--] = (byte) (n % radix);
            n /= radix;
        }
        return Arrays.copyOfRange(digits, currentDigitIdx + 1, digits.length);
    }


    public static int intFromDigits(byte[] digits)
    {
        return intFromDigits(digits, 10);
    }

    public static int intFromDigits(byte[] digits, int radix)
    {
        int result = 0;
        for (byte digit : digits)
        {
            result *= radix;
            result += digit;
        }
        return result;
    }


    public static long longFromDigits(byte[] digits)
    {
        return longFromDigits(digits, 10);
    }

    public static long longFromDigits(byte[] digits, int radix)
    {
        long result = 0;
        for (byte digit : digits)
        {
            result *= radix;
            result += digit;
        }
        return result;
    }


    public static long reverse(long n)
    {
        long reversed = 0;
        while (n > 0)
        {
            reversed *= 10;
            reversed += n % 10;
            n /= 10;
        }
        return reversed;
    }
}
