package ir.sharif.messenger.client;

/**
 * A class used to provide communication between initial code and
 * students protocol implementation.
 *
 * @since   1.0
 */

public class Core {

    /**
     * This function is called when a data is received from server 
     * and should handle lifecycle of request and take proper actions
     * detailed in project document.
     *
     * @param data plain received data in byte array.
     */
    public synchronized static void receiveData(byte[] data){
        // Todo: Implement
    }

    /**
     * This function is called when a user wants to login or register. 
     *
     * @param userName plain username.
     * @param password plain password in a char array. 
     * @return {@code true} if and only if the login or register process is
     * done successfully.
     */
    public static boolean loginOrRegister(String userName, char[] password){
        // Todo: Implement
        return true;
    }

    /**
     * This function is called when a user wants to logout.
     *
//     * @param userName plain username.
//     * @param password plain password in a char array.
     * @return {@code true} if and only if the logout process is done
     * successfully.
     */
    public static boolean logOut(){
        // Todo: Implement
        return true;
    }

    /**
     * Retrieves the last 100 messages of a specific chat.
     * If messages count is less than 100, retrieves all.
     *
     * @param userName specific contact username to get messages of 
     * their chat.
     * @return Array of messages of length 100 or less.
     */
    public static Message[] getMessages(String userName){
        // Todo: Implement
        return new Message[0];
    }

    /**
     * Retrieves the contacts list of the current user.
     *
     * @return Array of contacts usernames. 
     */
    public static String[] getContacts(){
        // Todo: Implement
        return new String[0];
    }

    /**
     * Adds the contact to the contacts list of the current user.
     * 
     * @param userName a contact username to add.
     * @return {@code true} if and only if the process is done successfully.
     */
    public static boolean addContact(String userName){
        // Todo: Implement
        return true;
    }

    /**
     * Sends message.
     * 
     * @param to a contact username to send message to.
     * @param text plain content of the message. 
     * @return {@code true} if and only if the message is sent successfully.
     */
    public static boolean sendMessage(String to, String text){
        // Todo: Implement
        return true;
    }
}
