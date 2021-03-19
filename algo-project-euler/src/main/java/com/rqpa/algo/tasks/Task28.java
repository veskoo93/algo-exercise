package com.rqpa.algo.tasks;

/*
Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

21 22 23 24 25
20  7  8  9 10
19  6  1  2 11
18  5  4  3 12
17 16 15 14 13

It can be verified that the sum of the numbers on the diagonals is 101.

What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */
public class Task28
{
    public static void main(String[] args)
    {
        System.out.println(findDiagonalsSumIn1001WidthAndHeightSpiral());
    }


    public static long findDiagonalsSumIn1001WidthAndHeightSpiral()
    {
        return findDiagonalsSumInSquareSpiral(1_001);
    }

    private static long findDiagonalsSumInSquareSpiral(int spiralWidthAndHeight)
    {
        // Points to the current value. Each iteration starts with the pointer at the Northern Eastern square of the previous iteration.
        long pointer = 1;
        long sum = 1;
        for (int width = 3; width <= spiralWidthAndHeight; width += 2)
        {
            for (int i = 0; i < 4; i++)
            {
                pointer += (width - 1);
                sum += pointer;
            }
//            // Move to SouthEast
//            pointer += (width - 1);
//            sumToSouthEast += pointer;
//
//            // Move to SouthWest
//            pointer += (width - 1);
//            sumToSouthWest += pointer;
//
//            // Move to NorthWest
//            pointer += (width - 1);
//            sumToNorthWest += pointer;
//
//            // Move to NorthEast
//            pointer += (width - 1);
//            sumToNorthEast += pointer;
        }

        return sum;
    }
}
