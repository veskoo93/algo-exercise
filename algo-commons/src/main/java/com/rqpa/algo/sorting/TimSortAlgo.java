package com.rqpa.algo.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.IntFunction;

public class TimSortAlgo implements RandomAccessStructureSortingAlgo
{
    private final int minRunLength;
    private final RandomAccessStructureSortingAlgo subRunSortingAlgo = InsertionSortAlgo.instance;

    public TimSortAlgo(int minRunLength)
    {
        this.minRunLength = minRunLength;
    }

    @Override
    public <T> void sort(RandomAccessStructure<T> structure, Comparator<? super T> comparator)
    {
        IntFunction<T[]> arrayFactory = size -> (T[]) Array.newInstance(structure.getItemType(), size);
        LinkedList<T[]> runsStack = new LinkedList<>();
        int nextRunStart = 0;
        while (nextRunStart < structure.getSize())
        {
            T[] run = findNextRun(structure, comparator, arrayFactory, nextRunStart);
            addToStack(run, runsStack, arrayFactory, comparator);
            nextRunStart += run.length;
        }

        mergeRuns(runsStack, structure, comparator, arrayFactory);
    }

    private <T> T[] findNextRun(
            RandomAccessStructure<T> structure,
            Comparator<? super T> comparator,
            IntFunction<T[]> arrayFactory,
            int startIdxIncl)
    {
        int minRunLength = Math.min(structure.getSize() - startIdxIncl, this.minRunLength);
        T lastItem = structure.getItem(startIdxIncl);
        int runEndIdxEcl = startIdxIncl + 1;
        for (; runEndIdxEcl < structure.getSize(); runEndIdxEcl++)
        {
            T currentItem = structure.getItem(runEndIdxEcl);
            if (comparator.compare(lastItem, currentItem) > 0)
            {
                break;
            }

            lastItem = currentItem;
        }

        boolean isSortedRun = true;
        if (runEndIdxEcl - startIdxIncl < minRunLength)
        {
            isSortedRun = false;
            runEndIdxEcl = startIdxIncl + minRunLength;
        }

        T[] run = arrayFactory.apply(runEndIdxEcl - startIdxIncl);
        for (int i = 0; i < run.length; i++)
        {
            run[i] = structure.getItem(startIdxIncl + i);
        }

        if (!isSortedRun)
        {
            subRunSortingAlgo.sort(new ArrayStructure<>(run), comparator);
        }

        return run;
    }

    private <T> void addToStack(T[] run, LinkedList<T[]> runsStack, IntFunction<T[]> arrayFactory, Comparator<? super T> comparator)
    {
        int lastNRunsLength = run.length;
        LinkedList<T[]> runsToMerge = new LinkedList<>();
        runsToMerge.add(run);

        T[] topRun = runsStack.isEmpty()  ? null : runsStack.removeLast();
        T[] secondToTopRun = runsStack.isEmpty()  ? null : runsStack.removeLast();

        while (topRun != null)
        {
            if (topRun.length <= lastNRunsLength)
            {
                runsToMerge.add(topRun);
                lastNRunsLength += topRun.length;
                topRun = secondToTopRun;
                secondToTopRun = runsStack.isEmpty()  ? null : runsStack.removeLast();
                continue;
            }

            if (secondToTopRun != null && (topRun.length + lastNRunsLength) > secondToTopRun.length)
            {
                runsToMerge.add(topRun);
                runsToMerge.add(secondToTopRun);

                lastNRunsLength = lastNRunsLength + topRun.length + secondToTopRun.length;

                topRun = runsStack.isEmpty()  ? null : runsStack.removeLast();
                secondToTopRun = runsStack.isEmpty()  ? null : runsStack.removeLast();
                continue;
            }

            // After the merge the invariant will be satisfied.
            // Return the top items in the stack
            if (secondToTopRun != null)
            {
                runsStack.add(secondToTopRun);
            }
            runsStack.add(topRun);
            break;
        }

        T[] runToAdd;
        if (runsToMerge.size() > 1)
        {
            runToAdd = mergeRuns(runsToMerge, arrayFactory, comparator);
        }
        else
        {
            runToAdd = runsToMerge.getFirst();
        }

        runsStack.add(runToAdd);
    }

    private <T> T[] mergeRuns(Iterable<T[]> runs, IntFunction<T[]> arrayFactory, Comparator<? super T> comparator)
    {
        int totalLength = 0;
        for (T[] run : runs)
        {
            totalLength += run.length;
        }

        T[] dest = arrayFactory.apply(totalLength);
        mergeRuns(runs, new ArrayStructure<>(dest), comparator, arrayFactory);
        return dest;
    }

    private <T> void mergeRuns(Iterable<T[]> runs, RandomAccessStructure<T> dest, Comparator<? super T> comparator, IntFunction<T[]> arrayFactory)
    {
        int lastRunEnd = 0;
        for (T[] run : runs)
        {
            if (lastRunEnd == 0)
            {
                for (int i = 0; i < run.length; i++)
                {
                    dest.setItem(i, run[i]);
                }
                lastRunEnd = run.length;
                continue;
            }

            /*
            res = -ip -1
            ip = -res -1
             */

            int hiDest = dest.binarySearch(run[0], comparator, 0, lastRunEnd);
            if (hiDest < 0)
            {
                hiDest = -hiDest -2;
            }
            int loRun = Arrays.binarySearch(run, dest.getItem(lastRunEnd - 1), comparator);
            if (loRun < 0)
            {
                loRun = -loRun - 1;
            }

            T[] merged = arrayFactory.apply((lastRunEnd - hiDest - 1 + loRun));
            MergeSupport.merge(
                    merged,
                    dest,
                    hiDest + 1,
                    lastRunEnd,
                    new ArrayStructure<>(run),
                    0,
                    loRun,
                    comparator
            );

            int destIdx = hiDest + 1;
            for (int i = 0; i < merged.length; i++)
            {
                dest.setItem(destIdx + i, merged[i]);
            }
            destIdx = destIdx + merged.length;
            for (int i = 0; i < run.length - loRun; i++)
            {
                dest.setItem(destIdx + i, run[loRun + i]);
            }

            lastRunEnd += run.length;
        }
    }
}
