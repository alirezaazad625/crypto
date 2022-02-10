package ir.sharif.messenger.server;

import java.net.*;

public class Connection implements Runnable{

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private boolean isRunning;

    Connection(int serverPort) throws UnknownHostException, SocketException {
        serverAddress = InetAddress.getByName("localhost");
        socket = new DatagramSocket(serverPort);
        new Thread(this).start();
    }

    /**
     * Sends data to a specific user.
     * 
     * @param data data in byte array.
     * @param to The temporarily identifier of the receiver that should
     * be obtained from the last message received from the specific user.
     * @return {@code true} if and only if the message is sent without any
     * exception.
     */
    public boolean sendData(byte[] data, int to){
        try {
            socket.send(new DatagramPacket(data, data.length, serverAddress, to));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void run() {
        isRunning = true;
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (isRunning) {
            try{
                socket.receive(packet);
                Core.receiveData(buffer, packet.getPort());
            }catch (Exception e){
                System.out.println("Error occurred in socket server: " + e.getMessage());
            }
        }

        socket.close();
    }
}