package com.rqpa.algo.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Fraction<T>
{
    private final T numerator;
    private final T denominator;
    private final boolean isSimplified;

    protected Fraction(T numerator, T denominator, boolean isSimplified)
    {
        this.numerator = numerator;
        this.denominator = denominator;
        this.isSimplified = isSimplified;
    }

    public T getNumerator()
    {
        return numerator;
    }

    public T getDenominator()
    {
        return denominator;
    }

    public Fraction<T> simplified()
    {
        if (isSimplified)
        {
            return this;
        }

        Map<Long, Long> numeratorFactors = factorize(numerator);
        Map<Long, Long> denominatorFactors = factorize(denominator);

        List<Long> numeratorPrimes = new ArrayList<>(numeratorFactors.keySet());
        for (Long prime : numeratorPrimes)
        {
            Long numeratorPower = numeratorFactors.get(prime);
            Long denominatorPower = denominatorFactors.get(prime);
            if (denominatorPower == null)
            {
                continue;
            }

            if (numeratorPower.equals(denominatorPower))
            {
                numeratorFactors.remove(prime);
                denominatorFactors.remove(prime);
            }
            else if (numeratorPower < denominatorPower)
            {
                numeratorFactors.remove(prime);
                denominatorFactors.put(prime, denominatorPower - numeratorPower);
            }
            else
            {
                numeratorFactors.put(prime, denominatorPower - numeratorPower);
                denominatorFactors.remove(prime);
            }
        }

        return create(calculateFromFactors(numeratorFactors), calculateFromFactors(denominatorFactors), true);
    }

    public Fraction<T> multiply(Fraction<T> multiplier)
    {
        return create(
                multiply(numerator, multiplier.numerator),
                multiply(denominator, multiplier.denominator),
                false
        );
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Fraction<?> fraction = (Fraction<?>) o;

        if (!numerator.equals(fraction.numerator))
            return false;
        return denominator.equals(fraction.denominator);
    }

    @Override
    public int hashCode()
    {
        int result = numerator.hashCode();
        result = 31 * result + denominator.hashCode();
        return result;
    }

    protected abstract T multiply(T multiplicand, T multiplier);
    protected abstract Fraction<T> create(T numerator, T denominator, boolean simplified);
    protected abstract T calculateFromFactors(Map<Long, Long> factors);
    protected abstract Map<Long, Long> factorize(T t);
    public abstract double doubleValue();
}
