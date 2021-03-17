package com.rqpa.algo.sorting;

import java.util.Collections;
import java.util.Comparator;

public class QuickSortAlgo implements RandomAccessStructureSortingAlgo
{
    public static final QuickSortAlgo instance = new QuickSortAlgo();

    private QuickSortAlgo()
    {
        // Singleton
    }

    @Override
    public <T> void sort(RandomAccessStructure<T> structure, Comparator<? super T> comparator)
    {
        doSort(structure, comparator, 0, structure.getSize());
    }

    private <T> void doSort(RandomAccessStructure<T> structure, Comparator<? super T> comparator, int fromIdxIncl, int toIdxExcl)
    {
        int size = toIdxExcl - fromIdxIncl;
        if (size <= 1) {
            return;
        }

        T pivotItem = getPivotItemIndex(structure, fromIdxIncl, toIdxExcl, comparator);

        int endOfLesserItemsExcl = fromIdxIncl;
        int endOfPivotItemsExcl = fromIdxIncl;
        int startOfBiggerItemsIncl = toIdxExcl - 1;

        while (endOfPivotItemsExcl <= startOfBiggerItemsIncl)
        {
            T itemAfterEndOfPivot = structure.getItem(endOfPivotItemsExcl);
            int cmpWithPivotResult = comparator.compare(itemAfterEndOfPivot, pivotItem);
            if (cmpWithPivotResult < 0)
            {
                structure.setItem(endOfPivotItemsExcl++, structure.getItem(endOfLesserItemsExcl));
                structure.setItem(endOfLesserItemsExcl++, itemAfterEndOfPivot);
            }
            else if (cmpWithPivotResult > 0)
            {
                structure.setItem(endOfPivotItemsExcl, structure.getItem(startOfBiggerItemsIncl));
                structure.setItem(startOfBiggerItemsIncl--, itemAfterEndOfPivot);
            }
            else
            {
                endOfPivotItemsExcl++;
            }
        }

        doSort(structure, comparator, fromIdxIncl, endOfLesserItemsExcl);
        doSort(structure, comparator, endOfPivotItemsExcl, toIdxExcl);
    }

    private <T> T getPivotItemIndex(RandomAccessStructure<T> structure, int fromIdxIncl, int toIdxExcl, Comparator<? super T> comparator)
    {
        int size = toIdxExcl - fromIdxIncl;
        if (size < 3)
        {
            return  structure.getItem(fromIdxIncl + size / 2);
        }

        int sizeDivided = size >>> 2;
        T a = structure.getItem(fromIdxIncl + sizeDivided);
        T b = structure.getItem(fromIdxIncl + size >>> 2);
        T c = structure.getItem(fromIdxIncl + sizeDivided * 3);

        if (comparator.compare(a, b) > 0)
        {
            if (comparator.compare(b, c) > 0)
            {
                return b;
            }

            return comparator.compare(a, c) > 0 ? c : a;
        }

        if (comparator.compare(a, c) > 0)
        {
            return a;
        }

        return c;
    }
}
