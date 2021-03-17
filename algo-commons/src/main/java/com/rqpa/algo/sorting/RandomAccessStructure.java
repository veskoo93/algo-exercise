package com.rqpa.algo.sorting;

import java.util.Comparator;

public interface RandomAccessStructure<T>
{
    int getSize();
    T getItem(int index) throws UnsupportedOperationException;
    void setItem(int index, T item) throws UnsupportedOperationException;
    int binarySearch(T item, Comparator<? super T> comparator, int fromIdxIncl, int toIdxExcl);
    Class<T> getItemType();
}
