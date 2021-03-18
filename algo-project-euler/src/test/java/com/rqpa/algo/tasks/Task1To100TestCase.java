package com.rqpa.algo.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1To100TestCase
{

    @Test
    public void testTask1()
    {
        Assertions.assertEquals(233168, Task1.sumOfAllMultiplesOf3And5Below1000());
    }

    @Test
    public void testTask2()
    {
        Assertions.assertEquals(4613732l, Task2.sumFibNumbersLessThanOrEqual4million());
    }

    @Test
    public void testTask3()
    {
        Assertions.assertEquals(6857, Task3.maxPrimeFactor());
    }

    @Test
    public void testTask4()
    {
        Assertions.assertEquals(906609, Task4.findLargestPalindromeOfTwoNumbersWith3Digits());
    }

    @Test
    public void testTask5()
    {
        Assertions.assertEquals(232792560l, Task5.findSmallestPositiveDisibleByNumbersFrom1To20());
    }

    @Test
    public void testTask6()
    {
        Assertions.assertEquals(25164150, Task6.findDifferenceBetweenSquareSumAndSumOfSquares1To100());
    }

    @Test
    public void testTask7()
    {
        Assertions.assertEquals(104743, Task7.find10001stPrime());
    }

    @Test
    public void testTask8()
    {
        Assertions.assertEquals(23514624000l, Task8.findGreatest13AdjacentDigitsProduct());
    }

    @Test
    public void testTask9()
    {
        Assertions.assertEquals(31875000, Task9.findProductOfPythagoreanTripletWith1000Sum());
    }

    @Test
    public void testTask10()
    {
        Assertions.assertEquals(142913828922l, Task10.findSumOfAllPrimesBelowTwoMillions());
    }

    @Test
    public void testTask11()
    {
        Assertions.assertEquals(70600674, Task11.findMaxProductOfFourAdjacentNumbers());
    }

    @Test
    public void testTask12()
    {
        Assertions.assertEquals(76576500l, Task12.findFirstTriangleNumberWithOver500Divisors());
    }

    @Test
    public void testTask13()
    {
        Assertions.assertEquals("5537376230390876637302048746832985971773659831892672", Task13.findSumOfAllNumbers());
        Assertions.assertEquals("5537376230", Task13.findFirstTenDigitsOfSumOfAllNumbers());
    }

    @Test
    public void testTask14()
    {
        Assertions.assertEquals(837799, Task14.findSeedLessThanMillionWithLongestChain());
    }

    @Test
    public void testTask15()
    {
        Assertions.assertEquals(137846528820l, Task15.findNumberOfRoutesFrom0_0to20_20());
    }

    @Test
    public void testTask16()
    {
        Assertions.assertEquals(1366, Task16.sumDigitsOf2Pow1000());
    }

    @Test
    public void testTask17()
    {
        Assertions.assertEquals(21124, Task17.findSumOfLettersInNumbersFrom1To1000Incl());
    }

    @Test
    public void testTask18()
    {
        Assertions.assertEquals(1074, Task18.findMaxTopToBottomTotal());
    }

    @Test
    public void testTask19()
    {
        Assertions.assertEquals(171, Task19.sundaysOnFirstOfMonthFrom1Jan1901To31Dec2000Incl());
    }

    @Test
    public void testTask20()
    {
        Assertions.assertEquals(648, Task20.findSumOfDigitsIn100Factorial());
    }

    @Test
    public void testTask21()
    {

    }
}
