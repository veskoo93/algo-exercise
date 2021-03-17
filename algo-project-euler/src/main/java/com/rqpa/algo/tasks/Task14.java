package com.rqpa.algo.tasks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/*
The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.
 */
public class Task14
{

    public static void main(String[] args)
    {
        System.out.println(findSeedLessThanMillionWithLongestChain());
    }
    public static long findSeedLessThanMillionWithLongestChain()
    {
        long maxSeed = 999_999;
        Map<Long, Integer> chainSeedToLength = new HashMap<>();
        chainSeedToLength.put(1l, 1);

        int maxChainLength = 1;
        long maxChainLengthSeed = -1l;
        LinkedList<Long> stack = new LinkedList<>();
        for (long inputSeed = maxSeed; inputSeed > 1; inputSeed--)
        {
            long currentSeed = inputSeed;
            Integer currentSeedLength = chainSeedToLength.get(currentSeed);
            while (currentSeedLength == null)
            {
                stack.add(currentSeed);
                currentSeed = getNextNumberInChain(currentSeed);
                currentSeedLength = chainSeedToLength.get(currentSeed);
            }

            while (!stack.isEmpty())
            {
                currentSeed = stack.removeLast();
                currentSeedLength += 1;
                chainSeedToLength.put(currentSeed, currentSeedLength);
            }

            if (currentSeedLength > maxChainLength)
            {
                maxChainLength = currentSeedLength;
                maxChainLengthSeed = currentSeed;
            }
        }

        return maxChainLengthSeed;
    }

    private static long getNextNumberInChain(long prev)
    {
        if (prev % 2 == 0)
        {
            return prev / 2;
        }
        return 3 * prev + 1;
    }
}
