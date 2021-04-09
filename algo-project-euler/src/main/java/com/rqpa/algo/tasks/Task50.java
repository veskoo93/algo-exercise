package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;

import org.slf4j.Logger;

import com.rqpa.algo.primes.BruteForcePrimeNumbersProvider;

/*
The prime 41, can be written as the sum of six consecutive primes:

41 = 2 + 3 + 5 + 7 + 11 + 13
This is the longest sum of consecutive primes that adds to a prime below one-hundred.

The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.

Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */
public class Task50
{
    private static final Logger logger = getLogger(Task50.class);

    public static void main(String[] args)
    {
        System.out.println(solution());
    }

    public static long solution()
    {
        long maxSum = 1_000_000;
        long[] primes = BruteForcePrimeNumbersProvider.instance.getPrimesLessThan(maxSum);

        int maxWindowSize = findLargestSequenceWIthSumUnderN(primes, maxSum);

        for (int windowSize = maxWindowSize; windowSize > 1; windowSize--)
        {
            long windowSum = 0;
            for (int i = 0; i < windowSize; i++)
            {
                windowSum += primes[i];
            }
            if (windowSum > maxSum)
            {
                break;
            }

            for (int i = 0; i < primes.length - 1 - windowSize; i++)
            {
                if (windowSum > maxSum)
                {
                    break;
                }
                if (Arrays.binarySearch(primes, windowSum) >= 0)
                {
                    logger.info("{} primes sequence found starting from {}", windowSize, primes[i]);
                    return windowSum;
                }

                windowSum = windowSum - primes[i] + primes[i + windowSize];
            }
        }

        return 1;
    }

    private static int findLargestSequenceWIthSumUnderN(long[] numbers, long n)
    {
        long sum = 0;
        for (int i = 0; i < numbers.length; i++)
        {
            sum += numbers[i];
            if (sum > n)
            {
                return i;
            }
        }

        return numbers.length;
    }
}
