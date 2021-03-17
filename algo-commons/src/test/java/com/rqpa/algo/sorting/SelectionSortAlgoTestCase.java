package com.rqpa.algo.sorting;

public class SelectionSortAlgoTestCase extends AbstractRandomAccessStructureSortingAlgoTestCase
{
    public SelectionSortAlgoTestCase()
    {
        super(10_000);
    }

    @Override
    protected RandomAccessStructureSortingAlgo createAlgo()
    {
        return SelectionSortAlgo.instance;
    }
}
