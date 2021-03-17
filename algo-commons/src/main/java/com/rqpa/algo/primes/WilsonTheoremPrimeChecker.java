package com.rqpa.algo.primes;

/*
 Wilson theorem: n is a prime when: (n − 1)! + 1 is divisible by n
 */
public class WilsonTheoremPrimeChecker implements PrimeNumberChecker {

    public static final WilsonTheoremPrimeChecker instance = new WilsonTheoremPrimeChecker();

    private WilsonTheoremPrimeChecker()
    {
        // Singleton
    }

    @Override
    public boolean isPrime(int n) {
        /*
            result: ((n − 1)! + 1) % n == 0
            result: (((n-1)! % n) + 1 % n) % n == 0
         */

        // First find ((n-1)! ) % n
        // m! % n = ( 1%n + 2%n + ... + m%n) %n = (((1%n) +2%n)%n+3%n)....%n

        int factorialDivisionRemainder = 1;
        for (int i = 2; i < n; i++) {
            factorialDivisionRemainder = (factorialDivisionRemainder * i % n) % n;
        }

        return (factorialDivisionRemainder + 1) % n == 0;
    }
}
