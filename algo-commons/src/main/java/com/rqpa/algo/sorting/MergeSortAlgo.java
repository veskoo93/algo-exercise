package com.rqpa.algo.sorting;

import java.lang.reflect.Array;
import java.util.Comparator;

public class MergeSortAlgo implements RandomAccessStructureSortingAlgo
{
    public static final MergeSortAlgo instance = new MergeSortAlgo();

    private MergeSortAlgo()
    {
        // Singleton
    }

    @Override
    public <T> void sort(RandomAccessStructure<T> structure, Comparator<? super T> comparator)
    {
        doSort(structure, comparator, 0, structure.getSize());
    }

    private <T> void doSort(RandomAccessStructure<T> structure, Comparator<? super T> comparator, int fromIdxIncl, int toIdxExcl) {
        int size = toIdxExcl - fromIdxIncl;
        if (size <= 1) {
            return;
        }

        int midIndex = fromIdxIncl + size / 2;
        doSort(structure, comparator, fromIdxIncl, midIndex);
        doSort(structure, comparator, midIndex, toIdxExcl);

        T[] sortedStructure = (T[]) Array.newInstance(structure.getItemType(), size);
        MergeSupport.merge(
                sortedStructure,
                structure,
                fromIdxIncl,
                midIndex,
                structure,
                midIndex,
                toIdxExcl,
                comparator
        );

        for (int i = 0; i < sortedStructure.length; i++)
        {
            structure.setItem(fromIdxIncl + i, sortedStructure[i]);
        }
    }

}
