package ir.sharif.messenger.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ContactsFrame extends JFrame implements ActionListener {

    private static String ADD = "add";
    private static String EXIT = "exit";

    private Container container = getContentPane();
    private ImageIcon profileIcon = new ImageIcon("resources//profile.png");
    private Font arialFont = new Font("Arial", Font.BOLD, 14);
    private JButton addContactButton = new JButton("Add new contact");
    private JButton exitButton = new JButton("Exit");
    private String userName;
    private JPanel buttonsPanel = new JPanel();

    static ChatMap<String, ChatFrame> openChats = new ChatMap<>();

    public ContactsFrame(String userName) {
        this.userName = userName;
        setLayoutManager();
        addComponentsToContainer();
        addActionEvent();
        initializeForm();
    }

    private void initializeForm() {
        setTitle("Contacts");
        setResizable(false);
        setAlwaysOnTop(true);
        setLocation(150, 150);
        container.setBackground(new Color(220, 220, 220));
        setBounds(10, 10, 370, 550);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }

    private void setLayoutManager() {
        container.setLayout(new BorderLayout());
    }

    private void addComponentsToContainer() {
        buttonsPanel.removeAll();
        buttonsPanel.setLayout(new GridLayout(1,2));
        buttonsPanel.add(addContactButton);
        buttonsPanel.add(exitButton);

        container.removeAll();
        JScrollPane contactPanel = getContactsPanel();

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contactPanel, buttonsPanel){
            private final int location = 450;
            {
                setDividerLocation( location );
            }
            @Override
            public int getDividerLocation() {
                return location ;
            }
            @Override
            public int getLastDividerLocation() {
                return location ;
            }
        };

        container.add(split);
    }

    public JScrollPane getContactsPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        String[] contactsList = Core.getContacts();
        JPanel bag = new JPanel();
        bag.setLayout(new BoxLayout(bag, BoxLayout.Y_AXIS));
        for (String aContactsList : contactsList)
            bag.add(getContactItem(aContactsList));
        jPanel.add(bag, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(jPanel);
        return new JScrollPane(new ScrollableWrapper(scrollPane));
    }

    public JPanel getContactItem(String name){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JButton panel = new JButton();
        BorderLayout mainLayout = new BorderLayout(0,0);
        mainLayout.setHgap(25);
        panel.setLayout(mainLayout);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(arialFont);
        panel.add(nameLabel);
        panel.setMinimumSize(new Dimension(220,300));
        JLabel imgLabel = new JLabel(profileIcon);
        panel.add(imgLabel, BorderLayout.WEST);
        jPanel.add(panel, BorderLayout.NORTH);
        panel.addActionListener(this);
        panel.setActionCommand(name);
        return jPanel;
    }

    private void addActionEvent() {
        addContactButton.addActionListener(this);
        addContactButton.setActionCommand(ADD);
        exitButton.addActionListener(this);
        exitButton.setActionCommand(EXIT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals(ADD)) {
            String contactUserName = JOptionPane.showInputDialog(this,"Enter contacts username:","Add contact", JOptionPane.QUESTION_MESSAGE);
            Core.addContact(contactUserName);
            addComponentsToContainer();
            revalidate();
            repaint();
        }
        else if (cmd.equals(EXIT)) {
            Core.logOut();
            System.exit(0);
        }
        else {
            if(!openChats.containsKey(cmd))
                openChats.put(cmd, new ChatFrame(userName, cmd));
        }
    }

}