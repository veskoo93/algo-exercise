package com.rqpa.algo.tasks;

/*
In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?
 */
public class Task31
{

    public static void main(String[] args)
    {
        System.out.println(calculateWaysToAchieve2pounds());
    }

    public static long calculateWaysToAchieve2pounds()
    {
        return calculateWaysToAchieveSum(200);
    }

    private static long calculateWaysToAchieveSum(long sum)
    {
        long[] coinValues = new long[] {
                1, 2, 5, 10, 20, 50, 100, 200
        };

        return calculateWaysToAchieveSum(sum, coinValues, 0);
    }

    private static long calculateWaysToAchieveSum(long sum, long[] coinValues, int minCoinValueIdx)
    {
        if (minCoinValueIdx == coinValues.length)
        {
            return sum == 0 ? 1 : 0;
        }
        long currentCoinValue = coinValues[minCoinValueIdx];
        long maxCoins = sum / currentCoinValue;
        long remainder = sum % currentCoinValue;

        long waysToAchieveSum = 0;
        for (int i = 0; i <= maxCoins; i++)
        {
            waysToAchieveSum += calculateWaysToAchieveSum(
                    currentCoinValue * i + remainder,
                    coinValues,
                    minCoinValueIdx + 1);
        }
        return waysToAchieveSum;
    }
}
