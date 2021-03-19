package com.rqpa.algo.tasks;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.IntStream;

import com.rqpa.algo.tuple.Tuple;

/*
A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

1/2	= 	0.5
1/3	= 	0.(3)
1/4	= 	0.25
1/5	= 	0.2
1/6	= 	0.1(6)
1/7	= 	0.(142857)
1/8	= 	0.125
1/9	= 	0.(1)
1/10	= 	0.1
Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.

Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class Task26
{
    public static void main(String[] args)
    {
        System.out.println(getDenominatorSmallerThan1000WithLongestCycle());
    }

    public static int getDenominatorSmallerThan1000WithLongestCycle()
    {
        return IntStream.range(2, 1000)
                .mapToObj(d -> Tuple.of(d, getCycleLength(d)))
                .max(Comparator.comparing(Tuple::getSecond))
                .orElseThrow()
                .getFirst();
    }

    private static int getCycleLength(int denominator)
    {
        LinkedHashSet<Integer> evaluatedNominators = new LinkedHashSet<>();
        int nominator = 10;

        while (nominator != 0)
        {
            if (evaluatedNominators.contains(nominator))
            {
                int idx = 0;
                for (Integer evaluatedNominator : evaluatedNominators)
                {
                    if (evaluatedNominator.equals(nominator))
                    {
                        break;
                    }
                    idx++;
                }

                return evaluatedNominators.size() - idx;
            }

            evaluatedNominators.add(nominator);
            nominator = (nominator % denominator) * 10;
        }

        return 0;
    }
}
