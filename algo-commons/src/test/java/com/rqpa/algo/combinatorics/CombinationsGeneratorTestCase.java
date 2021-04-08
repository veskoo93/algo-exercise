package com.rqpa.algo.combinatorics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CombinationsGeneratorTestCase
{

    @Test
    public void test4Items2ClassNoRepetition()
    {
        doTestCombinationGeneration(
                new Integer[] { 1, 2, 3, 4},
                2,
                false,
                new Combination(1, 2),
                new Combination(1, 3),
                new Combination(1, 4),
                new Combination(2, 3),
                new Combination(2, 4),
                new Combination(3, 4)
        );
    }

    @Test
    public void test4Items2ClassWithRepetition()
    {
        doTestCombinationGeneration(
                new Integer[] { 1, 2, 3, 4},
                2,
                true,
                new Combination(1, 1),
                new Combination(1, 2),
                new Combination(1, 3),
                new Combination(1, 4),
                new Combination(2, 2),
                new Combination(2, 3),
                new Combination(2, 4),
                new Combination(3, 3),
                new Combination(3, 4),
                new Combination(4, 4)
        );
    }

    private void doTestCombinationGeneration(Integer[] items, int combinationClass, boolean withRepetition, Combination... expectedCombinations)
    {
        Set<Combination> expectedCombinationsSet = new HashSet<>();
        for (Combination expectedCombination : expectedCombinations)
        {
            Assertions.assertTrue(expectedCombinationsSet.add(expectedCombination));
        }

        Predicate<Integer[]> combinationConsumer = comb -> {
            Combination combinationObj = new Combination(comb);
            Assertions.assertTrue(expectedCombinationsSet.remove(combinationObj));
            return true;
        };

        if (withRepetition)
        {
            CombinationsGenerator.instance.generateCombinationsWithRepetition(items, combinationClass, combinationConsumer);
        }
        else
        {
            CombinationsGenerator.instance.generateCombinationsWithoutRepetition(items, combinationClass, combinationConsumer);
        }
        Assertions.assertTrue(expectedCombinationsSet.isEmpty());
    }

    private static class Combination
    {
        private final Integer[] value;

        public Combination(Integer... values)
        {
            this.value = values;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Combination that = (Combination) o;
            return Arrays.equals(value, that.value);
        }

        @Override
        public int hashCode()
        {
            return Arrays.hashCode(value);
        }

        @Override
        public String toString()
        {
            return Arrays.stream(value)
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        }
    }
}
