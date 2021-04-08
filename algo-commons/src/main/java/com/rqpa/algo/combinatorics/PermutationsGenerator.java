package com.rqpa.algo.combinatorics;

import java.lang.reflect.Array;
import java.util.function.Predicate;

public class PermutationsGenerator
{
    public static final PermutationsGenerator instance = new PermutationsGenerator();

    private PermutationsGenerator()
    {
        // Singleton
    }

    public <T> void generatePermutations(T[] items, Predicate<T[]> permutationConsumer)
    {
        T[] combination = (T[]) Array.newInstance(items.getClass().getComponentType(), items.length);
        int[] indicesStack = new int[items.length];
        int stackPtr = 0;
        indicesStack[stackPtr] = -1;
        while (stackPtr >= 0)
        {
            int currentItemIdx;
            int currentCombItemIdx = stackPtr;

            for (currentItemIdx = indicesStack[stackPtr] + 1; currentItemIdx < items.length; currentItemIdx++)
            {
                boolean isUsed = false;
                for (int i = 0; i < stackPtr; i++)
                {
                    if (indicesStack[i] == currentItemIdx)
                    {
                        isUsed = true;
                        break;
                    }
                }
                if (!isUsed)
                {
                    break;
                }
            }

            if (currentItemIdx == items.length)
            {
                stackPtr--;
                continue;
            }

            indicesStack[stackPtr] = currentItemIdx;
            combination[currentCombItemIdx] = items[currentItemIdx];
            if (currentCombItemIdx == combination.length - 1)
            {
                if(!permutationConsumer.test(combination))
                {
                    return;
                }
            }
            else
            {
                indicesStack[++stackPtr] = -1;
            }
        }
    }
}
