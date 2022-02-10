package ir.sharif.messenger.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    private static String[] availableServers = { "Local" };
    private static String SHOW = "show";
    private static String RESET = "reset";
    private static String LOGIN = "login";

    private Container container = getContentPane();
    private JLabel serverLabel = new JLabel("SERVER");
    private JLabel userLabel = new JLabel("USERNAME");
    private JLabel passwordLabel = new JLabel("PASSWORD");
    private JComboBox<String> serverComboBox = new JComboBox<>(availableServers);
    private JTextField userTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton resetButton = new JButton("RESET");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    private JLabel imgLabel = new JLabel(new ImageIcon("resources//logo.png"));


    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        initializeForm();
    }

    private void initializeForm() {
        setTitle("Login");
        setVisible(true);
        setBounds(10, 10, 370, 550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocation(150, 150);
        container.setBackground(new Color(220, 220, 220));
        userTextField.requestFocus();
    }

    private void setLayoutManager() {
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        imgLabel.setBounds(60, 35, 250, 200);
        serverLabel.setBounds(50, 265, 100, 30);
        userLabel.setBounds(50, 305, 100, 30);
        passwordLabel.setBounds(50, 345, 100, 30);
        serverComboBox.setBounds(150, 265, 150, 30);
        userTextField.setBounds(150, 305, 150, 30);
        passwordField.setBounds(150, 345, 150, 30);
        showPassword.setBounds(150, 375, 150, 30);
        loginButton.setBounds(50, 430, 100, 30);
        resetButton.setBounds(200, 430, 100, 30);
    }

    private void addComponentsToContainer() {
        container.add(imgLabel);
        container.add(serverLabel);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(serverComboBox);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    private void addActionEvent() {
        loginButton.addActionListener(this);
        loginButton.setActionCommand(LOGIN);
        resetButton.addActionListener(this);
        resetButton.setActionCommand(RESET);
        showPassword.addActionListener(this);
        showPassword.setActionCommand(SHOW);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals(LOGIN)) {
            String userText;
            char[] pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getPassword();
            if(userText.equals("")){
                JOptionPane.showMessageDialog(this, "Enter your username", "Error", JOptionPane.INFORMATION_MESSAGE);
                userTextField.requestFocus();
            }
            else if(pwdText.length == 0){
                JOptionPane.showMessageDialog(this, "Enter your password", "Error", JOptionPane.INFORMATION_MESSAGE);
                passwordField.requestFocus();
            }
            else if (Core.loginOrRegister(userText,pwdText)) {
                new ContactsFrame(userText);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                userTextField.requestFocus();
            }
        }
        else if (cmd.equals(RESET)) {
            userTextField.setText("");
            passwordField.setText("");
            userTextField.requestFocus();
        }
        else if (cmd.equals(SHOW)) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}