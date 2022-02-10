package ir.sharif.messenger.common.prime;


import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static ir.sharif.messenger.common.prime.MillerRabin.isPrime;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MillerRabinTest {

    @Test
    void isPrime_whenPassedPrimeNumber_thenReturnsTrue() {
        List<BigInteger> primeList = Arrays.asList(
                BigInteger.valueOf(101), BigInteger.valueOf(17), BigInteger.valueOf(13), BigInteger.valueOf(97),
                BigInteger.valueOf(83), new BigInteger("359334085968622831041960188598043661065388726959079837"),
                new BigInteger("35742549198872617291353508656626642567")
        );
        primeList.forEach(
                prime -> assertTrue(isPrime(prime, 1))
        );
    }

    @Test
    void isPrime_whenPassedNonPrimeNumbers_testReturnsFalse() {
        List<BigInteger> nonPrimeList = Arrays.asList(
                BigInteger.valueOf(99),
                BigInteger.valueOf(15),
                BigInteger.valueOf(337137),
                BigInteger.valueOf(77)
        );
        nonPrimeList.forEach(
                nonPrime -> assertFalse(isPrime(nonPrime, 1))
        );
    }
}
