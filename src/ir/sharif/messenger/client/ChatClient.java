package ir.sharif.messenger.client;

import javax.swing.*;

public class ChatClient{

	public static Connection connection;
	private static int serverPort = 8008;

	public static void main(String[] args) throws Exception {
		try{
			for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if("Nimbus".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch(Exception ignored){
		}
		connection = new Connection(serverPort);
		new LoginFrame();
	}

	public void receiveMessage(Message message){
		if (ContactsFrame.openChats.containsKey(message.from))
			ContactsFrame.openChats.get(message.from).handleMessage(message);
	}
}