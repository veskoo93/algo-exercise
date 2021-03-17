package com.rqpa.algo.sorting;

import java.util.Comparator;

class MergeSupport
{
    public static <T> void merge(
            T[] dest,
            RandomAccessStructure<T> src1,
            int src1FromIncl,
            int src1ToExcl,
            RandomAccessStructure<T> src2,
            int src2FromIncl,
            int src2ToExcl,
            Comparator<? super T> comparator)
    {
        RandomStructureCursor<T> left = new RandomStructureCursor<>(src1, src1FromIncl, src1ToExcl);
        RandomStructureCursor<T> right = new RandomStructureCursor<>(src2, src2FromIncl, src2ToExcl);

        for (int i = 0; i < dest.length; i++)
        {
            if (!left.hasCurrentItem()) {
                dest[i] = right.get();
            } else if (!right.hasCurrentItem()) {
                dest[i] = left.get();
            } else {
                int cmpResult = comparator.compare(left.peek(), right.peek());
                if (cmpResult < 0) {
                    dest[i] = left.get();
                } else {
                    dest[i] = right.get();
                }
            }
        }

    }

    private static class RandomStructureCursor<T>
    {
        private final RandomAccessStructure<T> structure;
        private int nextIndex;
        private T currentItem;
        private boolean hasCurrentItem;
        private final int maxIndexExcl;

        public RandomStructureCursor(RandomAccessStructure<T> structure, int fromIndexIncl, int maxIndexExcl)
        {
            this.structure = structure;
            this.nextIndex = fromIndexIncl;
            this.maxIndexExcl = maxIndexExcl;
            advance();
        }

        private void advance() {
            if (nextIndex == maxIndexExcl) {
                hasCurrentItem = false;
                return;
            }

            currentItem = structure.getItem(nextIndex++);
            hasCurrentItem = true;
        }

        public T get() {
            T item = currentItem;
            advance();
            return item;
        }

        public T peek() {
            return currentItem;
        }

        public boolean hasCurrentItem() {
            return hasCurrentItem;
        }

    }
}
