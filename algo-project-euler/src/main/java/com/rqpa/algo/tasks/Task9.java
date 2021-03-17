package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.stream.IntStream;

import org.slf4j.Logger;

/*
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a^2 + b^2 = c^2
For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
 */
public class Task9
{
    private static final Logger logger = getLogger(Task9.class);

    public static void main(String[] args)
    {
        System.out.println(findProductOfPythagoreanTripletWith1000Sum());
    }

    public static int findProductOfPythagoreanTripletWith1000Sum()
    {
        for (int a = 1; a <= 998; a++)
        {
            for (int b = 1; b < 1000 - a; b++)
            {
                int c = 1000 - a - b;
                if (a*a + b*b == c*c)
                {
                    logger.info("a = {} b = {} c = {} a*b*c = {}", a, b, c, a*b*c);
                    return a*b*c;
                }
            }
        }

        throw new RuntimeException("Failed to find pythagorean triplet");
    }
}
