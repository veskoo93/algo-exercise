package com.rqpa.algo.sorting;

import java.util.Comparator;

public interface RandomAccessStructureSortingAlgo {
    <T> void sort(RandomAccessStructure<T> structure, Comparator<? super T> comparator);

    default <T extends Comparable<T>> void sort(RandomAccessStructure<T> structure) {
        sort(structure, Comparator.naturalOrder());
    }
}
