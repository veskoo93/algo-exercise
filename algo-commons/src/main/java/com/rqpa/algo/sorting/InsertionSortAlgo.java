package com.rqpa.algo.sorting;

import java.lang.reflect.Array;
import java.util.Comparator;

public class InsertionSortAlgo implements RandomAccessStructureSortingAlgo
{
    public static final InsertionSortAlgo instance = new InsertionSortAlgo();

    private InsertionSortAlgo()
    {
        // Singleton
    }

    @Override
    public <T> void sort(RandomAccessStructure<T> structure, Comparator<? super T> comparator)
    {
        for (int i = 1; i < structure.getSize(); i++) {
            T displacedItem = structure.getItem(i);

            int insertionPoint = structure.binarySearch(displacedItem, comparator, 0, i);
            if (insertionPoint < 0)
            {
                insertionPoint = -insertionPoint -1;
            }
            else
            {
                // We got an exact match!
                // Find the latest match and insert after it.
                while (comparator.compare(displacedItem, structure.getItem(++insertionPoint)) == 0 && insertionPoint < i);
            }

            if (insertionPoint == i)
            {
                continue;
            }

            for (int k = i; k > insertionPoint; k--)
            {
                structure.setItem(k, structure.getItem(k - 1));
            }
            structure.setItem(insertionPoint, displacedItem);
        }
    }
}
