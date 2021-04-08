package com.rqpa.algo.tasks;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import com.rqpa.algo.math.Fraction;
import com.rqpa.algo.math.LongFraction;

/*
The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
public class Task33
{
    private static final Logger logger = getLogger(Task33.class);

    public static void main(String[] args)
    {
        System.out.println(findProductDenominator());
    }

    public static long findProductDenominator()
    {
        Fraction<Long> product = new LongFraction(1l, 1l);
        for (long numerator = 10; numerator <= 99; numerator++)
        {
            for (long denominator = numerator + 1; denominator <= 99; denominator++)
            {
                if (numerator % 10 == 0 && denominator % 10 == 0)
                {
                    // "trivial" example
                    continue;
                }
                Fraction<Long> fraction = new LongFraction(numerator, denominator);
                Fraction<Long> fractionSimplified = fraction.simplified();

                long numeratorFirstDigit = numerator / 10;
                long numeratorSecondDigit = numerator % 10;
                long denominatorFirstDigit = denominator / 10;
                long denominatorSecondDigit = denominator % 10;

                if (numeratorFirstDigit == denominatorFirstDigit)
                {
                    product = appendToProductIfCurious(product, fraction, fractionSimplified, numeratorSecondDigit, denominatorSecondDigit);
                }
                if (numeratorSecondDigit == denominatorSecondDigit)
                {
                    product = appendToProductIfCurious(product, fraction, fractionSimplified, numeratorFirstDigit, denominatorFirstDigit);
                }
                if (numeratorSecondDigit == denominatorFirstDigit)
                {
                    product = appendToProductIfCurious(product, fraction, fractionSimplified, numeratorFirstDigit, denominatorSecondDigit);
                }
                if (numeratorFirstDigit == denominatorSecondDigit)
                {
                    product = appendToProductIfCurious(product, fraction, fractionSimplified, numeratorSecondDigit, denominatorFirstDigit);
                }
            }
        }

        return product.simplified().getDenominator();
    }

    private static Fraction<Long> appendToProductIfCurious(
            Fraction<Long> product,
            Fraction<Long> originalFraction,
            Fraction<Long> originalSimplifiedFraction,
            long incorrectlySimplifiedNumerator,
            long incorrectlySimplifiedDenominator)
    {
        if (incorrectlySimplifiedNumerator == 0 || incorrectlySimplifiedDenominator == 0)
        {
            return product;
        }

        Fraction<Long> incorrectlySimplified = new LongFraction(incorrectlySimplifiedNumerator, incorrectlySimplifiedDenominator);
        if (originalSimplifiedFraction.equals(incorrectlySimplified.simplified()))
        {
            product = product.multiply(incorrectlySimplified);
            logFractionsEqual(originalFraction, incorrectlySimplified);
        }
        return product;
    }

    private static void logFractionsEqual(Fraction<Long> a, Fraction<Long> b)
    {
        logger.trace("{} / {} = {} / {}", a.getNumerator(), a.getDenominator(), b.getNumerator(), b.getDenominator());
    }
}
