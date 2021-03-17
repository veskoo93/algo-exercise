package com.rqpa.algo.sorting;

public class MergeSortAlgoTestCase extends AbstractRandomAccessStructureSortingAlgoTestCase
{
    public MergeSortAlgoTestCase()
    {
        super(5_000_000);
    }

    @Override
    protected RandomAccessStructureSortingAlgo createAlgo()
    {
        return MergeSortAlgo.instance;
    }
}
