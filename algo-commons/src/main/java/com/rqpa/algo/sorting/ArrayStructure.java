package com.rqpa.algo.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayStructure<T> implements RandomAccessStructure<T>
{
    private final T[] arr;
    private final Class<T> itemType;

    public ArrayStructure(T[] arr)
    {
        this.arr = arr;
        this.itemType = (Class<T>) arr.getClass().getComponentType();
    }

    @Override
    public int getSize()
    {
        return arr.length;
    }

    @Override
    public T getItem(int index) throws UnsupportedOperationException
    {
        return arr[index];
    }

    @Override
    public void setItem(int index, T item) throws UnsupportedOperationException
    {
        arr[index] = item;
    }

    @Override
    public int binarySearch(T item, Comparator<? super T> comparator, int fromIdxIncl, int toIdxExcl)
    {
        return Arrays.binarySearch(arr, fromIdxIncl, toIdxExcl, item, comparator);
    }

    @Override
    public Class<T> getItemType()
    {
        return itemType;
    }
}
