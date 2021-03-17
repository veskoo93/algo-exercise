package com.rqpa.algo.sorting;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public abstract class AbstractRandomAccessStructureSortingAlgoTestCase
{
    private static final Logger logger = getLogger(AbstractRandomAccessStructureSortingAlgoTestCase.class);

    protected abstract RandomAccessStructureSortingAlgo createAlgo();

    private final int maxItems;

    public AbstractRandomAccessStructureSortingAlgoTestCase(int maxItems)
    {
        this.maxItems = maxItems;
    }

    @Test
    public void test1() {
        doTest(
                new Integer[] { 2, 5, 1, -3, 2, 1, 2, 7},
                new Integer[] { -3, 1, 1, 2, 2, 2, 5, 7}
        );
    }

    @Test
    public void testReversed() {
        doTest(
                new Integer[] { 10, 8, 7, 5, 3, 0, -1, -3},
                new Integer[] { -3, -1, 0, 3, 5, 7, 8, 10}
        );
    }

    @Test
    public void testSorted() {
        Integer[] sortedArr = new Integer[] {
                -5, -3 ,0, 0, 1, 1, 2, 4, 5, 5, 5, 5, 6, 8, 10, 10
        };
        Integer[] toSort = Arrays.copyOf(sortedArr, sortedArr.length);

        doTest(toSort, sortedArr);
    }

    @Test
    public void testWithMillionItems() {
        Random rng = new Random(0);
        int items = 1_000_000;
        doTestWithRandomItems(items, Integer[]::new, rng::nextInt);
    }

    @Test
    public void testWith10kItems() {
        Random rng = new Random(0);
        int items = 10_000;
        doTestWithRandomItems(items, Integer[]::new, rng::nextInt);
    }

    private <T extends Comparable<T>> void doTestWithRandomItems(
            int itemsCount,
            IntFunction<T[]> arrayFactory,
            Supplier<T> itemGenerator) {
        if (itemsCount > maxItems)
        {
            return;
        }

        T[] toSort = arrayFactory.apply(itemsCount);
        Arrays.setAll(toSort, index -> itemGenerator.get());
        T[] sorted = arrayFactory.apply(toSort.length);
        System.arraycopy(toSort, 0, sorted, 0, toSort.length);
        Arrays.sort(sorted);

        doTest(toSort, sorted);
    }

    private <T extends Comparable<T>> void doTest(T[] toSort, T[] expected) {
        doTest(toSort, expected, Comparator.naturalOrder());
    }

    private <T> void doTest(T[] toSort, T[] expected, Comparator<? super T> comparator) {
        RandomAccessStructureSortingAlgo algo = createAlgo();
        RandomAccessStructure<T> structure = new ArrayStructure<>(toSort);
        algo.sort(structure, comparator);
        Assertions.assertArrayEquals(expected, toSort);
    }
}
