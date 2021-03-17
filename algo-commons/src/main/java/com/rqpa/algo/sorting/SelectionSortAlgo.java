package com.rqpa.algo.sorting;

import java.util.Comparator;

public class SelectionSortAlgo implements RandomAccessStructureSortingAlgo
{
    public static final SelectionSortAlgo instance = new SelectionSortAlgo();

    private SelectionSortAlgo()
    {
        // Singleton
    }

    @Override
    public <T> void sort(RandomAccessStructure<T> structure, Comparator<? super T> comparator)
    {
        for (int i = 0; i < structure.getSize(); i++)
        {
            T iThItem = structure.getItem(i);
            T minItem = iThItem;
            int minItemIdx = i;

            for (int j = i + 1; j < structure.getSize(); j++)
            {
                T thisItem = structure.getItem(j);
                if (comparator.compare(thisItem, minItem) < 0)
                {
                    minItem = thisItem;
                    minItemIdx = j;
                }
            }

            if (minItemIdx != i)
            {
                structure.setItem(i, minItem);
                structure.setItem(minItemIdx, iThItem);
            }
        }
    }
}
