package com.rqpa.algo.sorting;

public class InsertionSortAlgoTestCase extends AbstractRandomAccessStructureSortingAlgoTestCase
{
    public InsertionSortAlgoTestCase()
    {
        super(10_000);
    }

    @Override
    protected RandomAccessStructureSortingAlgo createAlgo()
    {
        return InsertionSortAlgo.instance;
    }
}
