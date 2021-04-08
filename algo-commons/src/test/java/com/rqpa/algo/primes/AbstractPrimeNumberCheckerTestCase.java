package com.rqpa.algo.primes;

import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;

public abstract class AbstractPrimeNumberCheckerTestCase
{
    private static final Logger logger = getLogger(AbstractPrimeNumberCheckerTestCase.class);

    protected abstract PrimeNumberChecker createChecker();

    @ParameterizedTest
    @CsvSource({
            "1,false",
            "2,true",
            "3,true",
            "4,false",
            "5,true",
            "10,false",
            "11,true",
            "14,false",
            "19,true",
            "36,false",
            "37,true",
            "38,false"
    })
    public void testChecker(long number, boolean isPrime) {
        boolean actualIsPrime = createChecker().isPrime(number);
        logger.trace("Checked if number {} was prime. Expected: {} Actual: {} Match: {}", number, isPrime, actualIsPrime, isPrime == actualIsPrime);
        Assertions.assertEquals(isPrime, actualIsPrime);
    }
}
