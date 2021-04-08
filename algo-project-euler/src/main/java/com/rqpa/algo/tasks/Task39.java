package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.rqpa.algo.combinatorics.CombinationsGenerator;
import com.rqpa.algo.sorting.SelectionSortAlgo;

/*
If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?
 */
public class Task39
{
    private static final Logger logger = getLogger(Task39.class);

    private static int MAX_PERIMETER = 1000;

    public static void main(String[] args)
    {
        System.out.println(getMaxTrianglesCountForPerimeterUnder1000());
    }

    public static int getMaxTrianglesCountForPerimeterUnder1000()
    {
        int[] squares = new int[MAX_PERIMETER + 1];
        Arrays.setAll(squares, i -> i * i);

        Map<Integer, Set<Triangle>> trianglesByPerimeter = new HashMap<>();
        
        // A side must be less than the sum of the other two => A side can be maxed at perimeter / 2 - 1
        for (int a = 1; a < MAX_PERIMETER / 2 - 1; a++)
        {
            for (int b = 1; b <= a; b++)
            {
                int cSquared = squares[a] + squares[b];
                int c = Arrays.binarySearch(squares, cSquared);
                if (c > 0)
                {
                    // c^2 == a^2 + b^2
                    int perimeter = a + b + c;
                    if (perimeter <= MAX_PERIMETER)
                    {
                        trianglesByPerimeter.computeIfAbsent(perimeter, p -> new HashSet<>()).add(new Triangle(a, b, c));
                    }
                }
            }
        }

        Map.Entry<Integer, Set<Triangle>> maxTrianglesEntry = trianglesByPerimeter.entrySet()
                .stream()
                .max(Comparator.comparing(e -> e.getValue().size()))
                .get();
        logger.info("Perimeter {} yields {} triangles", maxTrianglesEntry.getKey(), maxTrianglesEntry.getValue().size());
        return maxTrianglesEntry.getKey();
    }

    private static class Triangle
    {
        private final int[] sides;
        public Triangle(int a, int b, int c)
        {
            sides = new int[] { a, b, c};
            Arrays.sort(sides);
        }

        public int[] getSides()
        {
            return sides;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Triangle triangle = (Triangle) o;

            return Arrays.equals(sides, triangle.sides);
        }

        @Override
        public int hashCode()
        {
            return Arrays.hashCode(sides);
        }
    }
}
