package ir.sharif.messenger.common.random;

public interface StreamCipher {

    String generateString(int size);

    byte[] generateBytes(int size);

    byte generateByte();

    int generateInt();

}
