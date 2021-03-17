package com.rqpa.algo.tasks;

/*
Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class Task6
{
    public static void main(String[] args)
    {
        System.out.println(findDifferenceBetweenSquareSumAndSumOfSquares1To100());
    }

    public static int findDifferenceBetweenSquareSumAndSumOfSquares1To100()
    {
        int sum = 0;
        int sumOfSquares = 0;
        for (int i = 1; i <= 100; i++)
        {
            sum += i;
            sumOfSquares += (i * i);
        }

        return sum * sum - sumOfSquares;
    }
}
