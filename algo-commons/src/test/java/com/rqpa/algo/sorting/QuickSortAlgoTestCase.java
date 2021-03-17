package com.rqpa.algo.sorting;

public class QuickSortAlgoTestCase extends AbstractRandomAccessStructureSortingAlgoTestCase
{
    public QuickSortAlgoTestCase()
    {
        super(15_000_000);
    }

    @Override
    protected RandomAccessStructureSortingAlgo createAlgo()
    {
        return QuickSortAlgo.instance;
    }
}
