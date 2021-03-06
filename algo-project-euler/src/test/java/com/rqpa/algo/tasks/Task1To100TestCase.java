package com.rqpa.algo.tasks;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.rqpa.algo.tuple.Tuple;

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
        Assertions.assertEquals(31626, Task21.findSumOfAllAmicableNumbersUnder10000());
    }

    @Test
    public void testTask22() throws IOException
    {
        Assertions.assertEquals(871198282l, Task22.calculateScoresSum());
    }

    @Test
    public void testTask23()
    {
        Assertions.assertEquals(4179871l, Task23.sumOfAllPositiveNumbersNotASumOfTwoAbundantNumbers());
    }

    @Test
    public void testTask24()
    {
        Assertions.assertEquals("2783915460", Task24.getMillionthLexicographicPermutationOfDigits0To9());
    }

    @Test
    public void testTask25()
    {
        Tuple<Long, BigInteger> expectedTuple = Tuple.of(
                4782l,
                new BigInteger("107006626638275893676498058445739688508368389663215166501323520337531452"
                        + "060469404062188914758248979265780469488817759195748433646667256"
                        + "995951299603046126274809248218614406943305123477444275027378175"
                        + "308757939166619214925918675955396642283714894311307469950343954"
                        + "700198543260972306729019287052644724372611771582182554849112052"
                        + "501320147861296593138179223555965745203950613755146783754322911"
                        + "960212993404826070617539770684706820289548690266618543512452190"
                        + "036948064135744747091170761976694569107009802439343961747410373"
                        + "691250323136553216477369702316775505159517351846057995491941096"
                        + "777837322966579658164651390348815425631018422419025984608800011"
                        + "018625555024549393711365165703944762958471454852342595042858242"
                        + "530608354443542821261100899286379504800689433030977321783486454"
                        + "311320576565986845628861680871869383529735064398629764066000072"
                        + "356291790520705116407761481249188583094594056668833910935094445"
                        + "657635766615161931775379289166158132715961687748798382182049252"
                        + "0348473874384736771934512787029218636250627816"));
        // Now that's some big-ass number
        Assertions.assertEquals(expectedTuple, Task25.getResult());
    }

    @Test
    public void testTask26()
    {
        Assertions.assertEquals(983, Task26.getDenominatorSmallerThan1000WithLongestCycle());
    }

    @Test
    public void testTask27()
    {
        Assertions.assertEquals(-59231, Task27.findTheProverbialProduct());
    }

    @Test
    public void testTask28()
    {
        Assertions.assertEquals(669171001, Task28.findDiagonalsSumIn1001WidthAndHeightSpiral());
    }

    @Test
    public void testTask29()
    {
        Assertions.assertEquals(9183, Task29.findDistinctResultsCountForPowersAndBasesFrom2To100());
    }

    @Test
    public void testTask30()
    {
        Assertions.assertEquals(443839, Task30.sumOfAllNumbersEqualToSumOfFifthPowersOfTheirDigits());
    }

    @Test
    public void testTask31()
    {
        Assertions.assertEquals(73682, Task31.calculateWaysToAchieve2pounds());
    }

    @Test
    public void testTask32()
    {
        Assertions.assertEquals(45228, Task32.findSumOfUniquePandigitalProducts());
    }

    @Test
    public void testTask33()
    {
        Assertions.assertEquals(100, Task33.findProductDenominator());
    }

    @Test
    public void testTask34()
    {
        Assertions.assertEquals(40730, Task34.sumOfAllNumbersEqualToSumOfFifthPowersOfTheirDigits());
    }

    @Test
    public void testTask35()
    {
        Assertions.assertEquals(55, Task35.countCircularPrimesBelow1Million());
    }

    @Test
    public void testTask36()
    {
        Assertions.assertEquals(872187, Task36.findSumOfPalindromicDecAndBinaryLessThanMillion());
    }

    @Test
    public void testTask37()
    {
        Assertions.assertEquals(748317, Task37.findSumOfTruncatablePrimes());
    }

    @Test
    public void testTask38()
    {
        Assertions.assertEquals(932718654, Task38.largestPandigitalConcatenatedProduct());
    }

    @Test
    public void testTask39()
    {
        Assertions.assertEquals(840, Task39.getMaxTrianglesCountForPerimeterUnder1000());
    }

    @Test
    public void testTask40()
    {
        Assertions.assertEquals(210, Task40.findMagicValue());
    }

    @Test
    public void testTask41()
    {
        Assertions.assertEquals(7652413, Task41.findLargestNDigitPandigitalPrime());
    }

    @Test
    public void testTask42() throws IOException
    {
        Assertions.assertEquals(162, Task42.findTriangleWordsCount());
    }

    @Test
    public void testTask43()
    {
        Assertions.assertEquals(16695334890l, Task43.findSpecialPandigitalsSum());
    }

    @Test
    public void testTask44()
    {
        Assertions.assertEquals(5482660, Task44.findMinDifference());
    }

    @Test
    public void testTask45()
    {
        Assertions.assertEquals(1533776805l, Task45.findNextTriangleNumber());
    }

    @Test
    public void testTask46()
    {
        Assertions.assertEquals(5777, Task46.findSmallestOddComposite());
    }

    @Test
    public void testTask47()
    {
        Assertions.assertEquals(134043, Task47.solution());
    }

    @Test
    public void testTask48()
    {
        Assertions.assertEquals("9110846700", Task48.solution());
    }

    @Test
    public void testTask49()
    {
        Assertions.assertEquals(296962999629l, Task49.solution());
    }

    @Test
    public void testTask50()
    {
        Assertions.assertEquals(997651, Task50.solution());
    }

    @Test
    public void testTask51()
    {
        Assertions.assertEquals(121313, Task51.solution());
    }

    @Test
    public void testTask52()
    {
        Assertions.assertEquals(142857, Task52.solution());
    }
}
