package com.rqpa.algo.math;

import java.util.Map;

import com.rqpa.algo.factorization.Factorization;

public class LongFraction extends Fraction<Long>
{
    public LongFraction(Long numerator, Long denominator)
    {
        this(numerator, denominator, false);
    }

    private LongFraction(Long numerator, Long denominator, boolean isSimplified)
    {
        super(numerator, denominator, isSimplified);
    }

    @Override
    protected Long multiply(Long multiplicand, Long multiplier)
    {
        return multiplicand * multiplier;
    }

    @Override
    protected Fraction<Long> create(Long numerator, Long denominator, boolean simplified)
    {
        return new LongFraction(numerator, denominator, simplified);
    }

    @Override
    protected Long calculateFromFactors(Map<Long, Long> factors)
    {
        long value = 1;
        for (Map.Entry<Long, Long> primePowerEntry : factors.entrySet())
        {
            for (long i = 0; i < primePowerEntry.getValue(); i++)
            {
                value *= primePowerEntry.getKey();
            }
        }
        return value;
    }

    @Override
    protected Map<Long, Long> factorize(Long aLong)
    {
        return Factorization.factorizeToPrimes(aLong);
    }

    @Override
    public double doubleValue()
    {
        return 1.0 * getNumerator() / getDenominator();
    }
}
