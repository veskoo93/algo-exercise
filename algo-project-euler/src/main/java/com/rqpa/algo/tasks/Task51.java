package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;

import com.rqpa.algo.combinatorics.CombinationsGenerator;
import com.rqpa.algo.math.NumbersUtil;
import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;

/*
By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.

Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
 */
public class Task51
{
    private static final Logger logger = getLogger(Task51.class);

    public static void main(String[] args)
    {
        System.out.println(solution());
    }

    public static long solution()
    {
        long[] primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(1_000_000);

        AtomicLong result = new AtomicLong();
        int startingPrimeIdx = 0;
        while (result.get() == 0)
        {
            if (startingPrimeIdx >= primes.length)
            {
                primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(primes[primes.length - 1] * 3 / 2);
            }

            long[] primesLocal = primes;

            long prime = primes[startingPrimeIdx++];
            byte[] digits = NumbersUtil.getDigits(prime);
            byte[] digitsCopy = Arrays.copyOf(digits, digits.length);

            Integer[] digitIndices = new Integer[digits.length];
            Arrays.setAll(digitIndices, i -> i);
            for (int i = 1; i < digits.length; i++)
            {
                CombinationsGenerator.instance.generateCombinationsWithoutRepetition(digitIndices, i, comb -> {
                    List<Long> primesFamily = new ArrayList<>();
                    System.arraycopy(digits, 0, digitsCopy, 0, digits.length);

                    byte firstDigitToTry = 0;
                    for (Integer digitIndex : comb)
                    {
                        if (digitIndex == 0)
                        {
                            firstDigitToTry = 1;
                            break;
                        }
                    }
                    for (byte j = firstDigitToTry; j < 10; j++)
                    {
                        for (Integer digitIndex : comb)
                        {
                            digitsCopy[digitIndex] = j;
                        }

                        long number = NumbersUtil.longFromDigits(digitsCopy);
                        if (Arrays.binarySearch(primesLocal, number) >= 0)
                        {
                            primesFamily.add(number);
                        }
                    }

                    if (primesFamily.size() == 8)
                    {
                        logger.info("Primes family:");
                        logger.info("Prime: {}", prime);
                        for (Long p : primesFamily)
                        {
                            logger.info("{}", p);
                        }

                        result.set(primesFamily.get(0));
                        return false;
                    }
                    return true;
                });
            }
        }

        return result.get();
    }
}
