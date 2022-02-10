package ir.sharif.messenger.server;


public class ChatServer {

    public static Connection connection;
    private static int serverPort = 8008;

    public static void main(String[] args) throws Exception {
        connection = new Connection(serverPort);
    }
}