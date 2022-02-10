package ir.sharif.messenger.common.prime;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    private static boolean isPrime(BigInteger n, Random r) {
        BigInteger temp = BigInteger.ZERO;
        do {
            temp = new BigInteger(n.bitLength() - 1, r);
        } while (temp.compareTo(BigInteger.ONE) <= 0);
        if (!n.gcd(temp).equals(BigInteger.ONE)) return false;
        BigInteger base = n.subtract(BigInteger.ONE);
        BigInteger TWO = BigInteger.valueOf(2);
        int k = 0;
        while ((base.mod(TWO)).equals(BigInteger.ZERO)) {
            base = base.divide(TWO);
            k++;
        }
        BigInteger curValue = temp.modPow(base, n);
        if (curValue.equals(BigInteger.ONE)) return true;
        for (int i = 0; i < k; i++) {
            if (curValue.equals(n.subtract(BigInteger.ONE))) return true;
            else curValue = curValue.modPow(TWO, n);
        }
        return false;
    }

    public static boolean isPrime(BigInteger n, long numTimes) {
        Random r = new Random(); //TODO switch to RC4
        for (int i = 0; i < numTimes; i++)
            if (!isPrime(n, r)) return false;
        return true;
    }

}
