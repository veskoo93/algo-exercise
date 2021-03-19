package com.rqpa.algo.tuple;

import java.util.Objects;

public class Tuple<First, Second>
{
    public static <F, S> Tuple<F, S> of(F first, S second)
    {
        return new Tuple<>(first, second);
    }

    private final First first;
    private final Second second;

    private Tuple(First first, Second second)
    {
        this.first = first;
        this.second = second;
    }

    public First getFirst()
    {
        return first;
    }

    public Second getSecond()
    {
        return second;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (!Objects.equals(first, tuple.first))
            return false;
        return Objects.equals(second, tuple.second);
    }

    @Override
    public int hashCode()
    {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
