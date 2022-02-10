package ir.sharif.messenger.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class ChatFrame extends JFrame implements ActionListener{

	private static String SEND = "send";

	private Container container = getContentPane();
	private JPanel textPanel = new JPanel(), inputPanel;
	private JTextArea textField, textArea;
	private String contact, name;
	protected JButton sendButton = new JButton("Send");
	private final Font font = new Font("Arial", Font.PLAIN, 14);

	public ChatFrame(String name, String contact) {
		this.name = name;
		this.contact = contact;
		setLayoutManager();
		addComponentsToContainer();
		addActionEvent();
		initializeForm();
	}

	private void addActionEvent() {
		sendButton.addActionListener(this);
		sendButton.setActionCommand(SEND);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ContactsFrame.openChats.removeChat(contact);
			}
		});
	}

	private void setLayoutManager() {
		container.setLayout(new BorderLayout());
	}

	private void addComponentsToContainer() {
		container.add(getInputPanel(), BorderLayout.CENTER);
		container.add(getTextPanel(), BorderLayout.NORTH);
	}

	private void initializeForm(){
		container.setBackground(new Color(220, 220, 220));

		Message[] messages = Core.getMessages(contact);
		for (Message message : messages)
			handleMessage(message);
		pack();
		setTitle("Chat ("+contact+")");
		setResizable(false);
		setAlwaysOnTop(true);
		setLocation(150, 150);
		getRootPane().setDefaultButton(sendButton);
		setVisible(true);
		textField.requestFocus();
    }

	private JPanel getTextPanel(){
		textArea = new JTextArea("", 16, 55);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setFont(font);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textPanel.add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		return textPanel;
	}

	private JPanel getInputPanel(){
		inputPanel = new JPanel(new BorderLayout());
		textField = new JTextArea(2,52);
		textField.setFont(font);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);

        JScrollPane scrollPaneField = new JScrollPane(textField);
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(scrollPaneField);

		inputPanel.add(textFieldPanel, BorderLayout.CENTER);
		inputPanel.add(sendButton, BorderLayout.EAST);
		return inputPanel;
	}

	public void handleMessage(Message message) {
		textArea.append(message.from + ": " + message.text + "\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();

		if(cmd.equals(SEND)){
			String message = textField.getText();
			if (message.equals(""))
				return;
			textField.setText("");
			if (Core.sendMessage(contact, message)) {
				textArea.append(name + ": " + message + "\n");
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		}
	}
}
