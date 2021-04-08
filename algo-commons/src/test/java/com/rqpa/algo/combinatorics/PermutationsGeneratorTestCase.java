package com.rqpa.algo.combinatorics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PermutationsGeneratorTestCase
{

    @Test
    public void test2Items()
    {
        doTestPermutationGeneration(
                new Integer[] { 1, 2},
                new Permutation(1, 2),
                new Permutation(2, 1)
        );
    }

    @Test
    public void test3Items()
    {
        doTestPermutationGeneration(
                new Integer[] { 1, 2, 3},
                new Permutation(1, 2, 3),
                new Permutation(1, 3, 2),
                new Permutation(2, 1, 3),
                new Permutation(2, 3, 1),
                new Permutation(3, 1, 2),
                new Permutation(3, 2, 1)
        );
    }

    private void doTestPermutationGeneration(Integer[] items, Permutation... expectedPermutations)
    {
        Set<Permutation> expectedPermutationsSet = new HashSet<>();
        for (Permutation expectedPermutation : expectedPermutations)
        {
            Assertions.assertTrue(expectedPermutationsSet.add(expectedPermutation));
        }

        Predicate<Integer[]> permutationConsumer = perm -> {
            Permutation permutationObj = new Permutation(perm);
            Assertions.assertTrue(expectedPermutationsSet.remove(permutationObj));
            return true;
        };

        PermutationsGenerator.instance.generatePermutations(items, permutationConsumer);
        Assertions.assertTrue(expectedPermutationsSet.isEmpty());
    }

    private static class Permutation
    {
        private final Integer[] value;

        public Permutation(Integer... values)
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

            Permutation that = (Permutation) o;
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
