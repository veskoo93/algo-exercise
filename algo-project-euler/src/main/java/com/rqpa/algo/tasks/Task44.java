package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;

import org.slf4j.Logger;

import com.rqpa.algo.numbers.GeometricNumbersGenerator;

/*
Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten pentagonal numbers are:

1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...

It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70 − 22 = 48, is not pentagonal.

Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference are pentagonal and D = |Pk − Pj| is minimised; what is the value of D?
 */
public class Task44
{
    private static final Logger logger = getLogger(Task44.class);

    public static void main(String[] args)
    {
        System.out.println(findMinDifference());
    }

    public static long findMinDifference()
    {
        long minDifference = Long.MAX_VALUE;
        long[] pentagonalNumbers = new long[5_000];
        Arrays.setAll(pentagonalNumbers, i -> GeometricNumbersGenerator.getPentagonalNumber(i + 1));
        long lastPentagonal = pentagonalNumbers[pentagonalNumbers.length - 1];

        for (int i = 0; i < pentagonalNumbers.length - 2; i++)
        {
            if (pentagonalNumbers[i + 1] - pentagonalNumbers[i] > minDifference)
            {
                break;
            }
            if (pentagonalNumbers[i + 1] + pentagonalNumbers[i] > lastPentagonal)
            {
                break;
            }
            
            for (int j = i + 1; j < pentagonalNumbers.length - 1; j++)
            {
                long difference = pentagonalNumbers[j] - pentagonalNumbers[i];
                if (difference > minDifference)
                {
                    break;
                }
                long sum = pentagonalNumbers[j] + pentagonalNumbers[i];

                if (Arrays.binarySearch(pentagonalNumbers, difference) >= 0 && Arrays.binarySearch(pentagonalNumbers, sum) >= 0)
                {

                    minDifference = difference;
                    break;
                }
            }
        }

        if (minDifference == Long.MAX_VALUE)
        {
            throw new RuntimeException("Not found!");
        }
        return minDifference;
    }
}
