package com.rqpa.algo.combinatorics;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.function.Predicate;

public class CombinationsGenerator
{
    public static final CombinationsGenerator instance = new CombinationsGenerator();

    private CombinationsGenerator()
    {
        // Singleton
    }

    public <T> void generateCombinationsWithoutRepetition(T[] items, int combinationClass, Predicate<T[]> combinationConsumer)
    {
        generateCombinations(items, combinationClass, combinationConsumer, 1);
    }

    public <T> void generateCombinationsWithRepetition(T[] items, int combinationClass, Predicate<T[]> combinationConsumer)
    {
        generateCombinations(items, combinationClass, combinationConsumer, 0);
    }

    private <T> void generateCombinations(T[] items, int combinationClass, Predicate<T[]> combinationConsumer, int indexOffset)
    {
        T[] combination = (T[]) Array.newInstance(items.getClass().getComponentType(), combinationClass);
        int[] indicesStack = new int[combinationClass];
        int stackPtr = 0;
        indicesStack[stackPtr] = -1;
        while (stackPtr >= 0)
        {
            int currentItemIdx = ++indicesStack[stackPtr];
            int currentCombItemIdx = stackPtr;

            if (currentItemIdx == items.length)
            {
                stackPtr--;
                continue;
            }

            combination[currentCombItemIdx] = items[currentItemIdx];
            if (currentCombItemIdx == combination.length - 1)
            {
                if(!combinationConsumer.test(combination))
                {
                    return;
                }
            }
            else
            {
                indicesStack[++stackPtr] = currentItemIdx - 1 + indexOffset;
            }
        }
    }
}
