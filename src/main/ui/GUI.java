package ui;

import model.StudySpots;
import model.User;
import model.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GUI {
    private JFrame frame;
    private JPanel login;
    private JPanel MainMenu;
    final static int GAP = 10;
    private User currentUser;
    private static UserData ud;
    private static StudySpots sss;
    private String currentLocation;

    public static void main(String[] args) throws FileNotFoundException {
        ud = new UserData();
        ud.load();
        sss = new StudySpots();
        ud.redistributeUserData(sss);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI window = new GUI();
                window.frame.setVisible(true);
            }
        });
    }

    public GUI() {
        initialize();
    }

    public void initialize() {

        frame = new JFrame("App");
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        login = new JPanel();
        frame.getContentPane().add(login);

        login.setLayout(new BoxLayout(login, BoxLayout.LINE_AXIS));
        JTextField userName = new JTextField();
        JPasswordField password = new JPasswordField();
        JTextField address = new JTextField();

        login.setLayout(new BoxLayout(login,
                BoxLayout.PAGE_AXIS));
        login.add(createEntryFields(userName, password, address));
        login.add(createButtons(userName, password, address));


        MainMenu = new JPanel();
        SplitPaneDemo s = new SplitPaneDemo(sss);
        MainMenu.add(s.getSplitPane());
        MainMenu.setVisible(false);
        frame.getContentPane().add(MainMenu);

    }


    protected JComponent createButtons(final JTextField userName, final JPasswordField password, final JTextField address) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = userName.getText();
                char[] pass = password.getPassword();
                String password = new String(pass);
                currentUser = ud.getExistingUser(name, password);
                if (currentUser != null) {
                    MainMenu.setVisible(true);
                    login.setVisible(false);
                    currentLocation = address.getText();
                }

            }
        });
        panel.add(button);

        button = new JButton("Sign up");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = userName.getText();
                char[] pass = password.getPassword();
                String password = new String(pass);
                currentUser = ud.newUser(name, password);
                MainMenu.setVisible(true);
                login.setVisible(false);
                currentLocation = address.getText();
            }
        });
        panel.add(button);

        //Match the SpringLayout's gap, subtracting 5 to make
        //up for the default gap FlowLayout provides.
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                GAP - 5, GAP - 5));
        return panel;
    }


    protected JComponent createEntryFields(JTextField userName, JPasswordField password, JTextField address) {
        JPanel panel = new JPanel(new SpringLayout());

        String[] labelStrings = {
                "Username: ",
                "Password: ",
                "Current Address: "
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;
        userName.setColumns(20);
        fields[fieldNum++] = userName;

        password.setColumns(20);
        fields[fieldNum++] = password;

        address.setColumns(20);
        fields[fieldNum++] = address;

        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                    JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            JTextField tf = null;
            tf = (JTextField) fields[i];
        }


            SpringUtilities.makeCompactGrid(panel,
                    labelStrings.length, 2,
                    GAP, GAP, //init x,y
                    GAP, GAP / 2);//xpad, ypad

        return panel;
    }
}





