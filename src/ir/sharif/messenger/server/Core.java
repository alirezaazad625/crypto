package ir.sharif.messenger.server;

/**
 * A class used to provide communication between initial code and
 * students protocol implementation.
 *
 * @since   1.0
 */

public class Core {

    /**
     * This function is called when a message is received from client 
     * and should handle lifecycle of request and take proper actions
     * detailed in project document.
     *
     * @param data plain received message in byte array.
     * @param from The temporarily identifier of the sender that could
     * be used to respond to received message.
     */
    public static void receiveData(byte[] data, int from){
        // Todo: Implement
    }

    /**
     * Generates RSA key pair with pre-defined security parameters 
     * which can be altered easily. The shape of keys is your
     * decision but should be efficiently usable.
     * 
     * @return Array of generated keys. First element is private key 
     * and second one is public key.
     */
    public static String[] generateRSAKeyPair(){
        // Todo: Implement
        return new String[2];
    }

}