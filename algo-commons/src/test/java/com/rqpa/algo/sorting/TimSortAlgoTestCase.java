package com.rqpa.algo.sorting;

public class TimSortAlgoTestCase extends AbstractRandomAccessStructureSortingAlgoTestCase
{
    public TimSortAlgoTestCase()
    {
        super(10_000_000);
    }

    @Override
    protected RandomAccessStructureSortingAlgo createAlgo()
    {
        return new TimSortAlgo(64);
    }
}
