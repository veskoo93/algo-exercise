package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

/*
The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.

73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450

Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
 */
public class Task8
{
    private static final Logger logger = getLogger(Task8.class);

    public static void main(String[] args)
    {
        System.out.println(findGreatest13AdjacentDigitsProduct());
    }

    public static long findGreatest13AdjacentDigitsProduct()
    {
        String numberAsString = ""
                + "73167176531330624919225119674426574742355349194934"
                + "96983520312774506326239578318016984801869478851843"
                + "85861560789112949495459501737958331952853208805511"
                + "12540698747158523863050715693290963295227443043557"
                + "66896648950445244523161731856403098711121722383113"
                + "62229893423380308135336276614282806444486645238749"
                + "30358907296290491560440772390713810515859307960866"
                + "70172427121883998797908792274921901699720888093776"
                + "65727333001053367881220235421809751254540594752243"
                + "52584907711670556013604839586446706324415722155397"
                + "53697817977846174064955149290862569321978468622482"
                + "83972241375657056057490261407972968652414535100474"
                + "82166370484403199890008895243450658541227588666881"
                + "16427171479924442928230863465674813919123162824586"
                + "17866458359124566529476545682848912883142607690042"
                + "24219022671055626321111109370544217506941658960408"
                + "07198403850962455444362981230987879927244284909188"
                + "84580156166097919133875499200524063689912560717606"
                + "05886116467109405077541002256983155200055935729725"
                + "71636269561882670428252483600823257530420752963450";
        int windowSize = 13;

        long windowProduct = -1;
        int windowEndIndexExcl = windowSize - 1;
        while (windowEndIndexExcl <= numberAsString.length() && windowProduct == -1)
        {
            windowEndIndexExcl += 1;
            windowProduct = calculateWindowProduct(numberAsString, windowEndIndexExcl - windowSize, windowSize);
        }
        logger.trace("{}", windowProduct);

        long maxWindowProduct = windowProduct;

        while (windowEndIndexExcl < numberAsString.length())
        {
            int nextDigit = Character.digit(numberAsString.charAt(windowEndIndexExcl), 10);
            if (nextDigit != 0)
            {
                windowProduct /= Character.digit(numberAsString.charAt(windowEndIndexExcl - windowSize), 10);
                windowProduct *= nextDigit;
                windowEndIndexExcl += 1;
            }
            else
            {
                windowProduct = -1;
                windowEndIndexExcl += windowSize;
                while (windowEndIndexExcl <= numberAsString.length() && windowProduct == -1)
                {
                    windowEndIndexExcl += 1;
                    windowProduct = calculateWindowProduct(numberAsString, windowEndIndexExcl - windowSize, windowSize);
                }
            }

            if (windowProduct > 0)
            {
                logger.trace("{}", windowProduct);
            }
            maxWindowProduct = Math.max(windowProduct, maxWindowProduct);
        }
        return maxWindowProduct;
    }

    private static long calculateWindowProduct(String number, int windowStartIdx, int windowSize)
    {
        long product = 1;
        for (int i = 0; i < windowSize; i++)
        {
            int digit = Character.digit(number.charAt(windowStartIdx + i), 10);
            if (digit == 0)
            {
                return -1;
            }
            product *= digit;
        }

        return product;
    }
}
