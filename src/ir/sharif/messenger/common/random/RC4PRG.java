package ir.sharif.messenger.common.random;

import java.nio.charset.StandardCharsets;

public class RC4PRG implements StreamCipher {
    public static final int SEED_SIZE = 256;
    private byte[] s;
    private int i;
    private int j;

    public RC4PRG(final String seed) {
        this.init(seed);
    }

    @Override
    public String generateString(int size) {
        byte[] byteResult = this.generateBytes(size);
        return new String(byteResult, StandardCharsets.UTF_8);
    }

    @Override
    public byte[] generateBytes(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("size must be positive");
        byte[] result = new byte[size];
        for (int idx = 0; idx < size; idx++)
            result[idx] = this.generateByte();
        return result;
    }

    @Override
    public int generateInt() {
        byte[] byteResult = this.generateBytes(4);
        return byteResult[0] & 0xff | byteResult[1] << 8 | byteResult[2] << 16 | byteResult[3] << 24;
    }

    @Override
    public byte generateByte() {
        this.i = (this.i + 1) % SEED_SIZE;
        this.j = (this.j + this.getUnsignedS(this.i)) % SEED_SIZE;
        swaps(i, j);
        return this.s[(this.getUnsignedS(this.i) + this.getUnsignedS(this.j)) % SEED_SIZE];
    }

    private void init(final String seed) {
        this.s = new byte[SEED_SIZE];
        byte[] seedBytes = this.getSeedBytes(seed);
        this.j = 0;

        for (this.i = 0; this.i < SEED_SIZE; this.i++) {
            this.s[i] = (byte) (i - 128);
        }

        for (this.i = 0; this.i < SEED_SIZE; this.i++) {
            this.j = (this.j + this.getUnsignedS(i) + seedBytes[i]) % SEED_SIZE;
            this.swaps(this.i, this.j);
        }

        this.i = 0;
        this.j = 0;
    }

    private void swaps(final int a, final int b) {
        assert a < SEED_SIZE && a >= 0 : String.format("a must be within range [0, %d) but was %d", SEED_SIZE, a);
        assert b < SEED_SIZE && b >= 0 : String.format("b must be within range [0, %d) but was %d", SEED_SIZE, b);

        byte tmp = this.s[a];
        this.s[a] = this.s[b];
        this.s[b] = tmp;
    }

    private byte[] getSeedBytes(final String seed) {
        byte[] initialBytes = seed.getBytes(StandardCharsets.UTF_8);
        if (initialBytes.length > SEED_SIZE)
            throw new IllegalArgumentException("byte size of the seed cannot be more than " + SEED_SIZE);

        byte[] result = new byte[SEED_SIZE];
        for (int idx = 0; idx < SEED_SIZE; idx++)
            result[idx] = initialBytes[idx % initialBytes.length];

        return result;
    }

    private int getUnsignedS(final int idx) {
        assert idx < SEED_SIZE && idx >= 0 : String.format("idx must be within range [0, %d) but was %d", SEED_SIZE, idx);
        return this.s[idx] + 128;
    }
}
