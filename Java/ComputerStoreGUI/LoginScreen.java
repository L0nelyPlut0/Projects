/* Class to create and run a login screen for the computer product management system, has all the action listeners and setup to run the store menu if log in is successful  */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class LoginScreen extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ComputerStore computerStore;
    private StoreMenu sm;
    private StaffMember member;

    // constructor setting up setters for computer store and setting up frame size and position
    public LoginScreen(ComputerStore computerStore) {
        this.computerStore = computerStore;
        setSize(900, 750);
        setLocationRelativeTo(null);
        setTitle("Login Screen");

        // Create the Swing components for the frame, setting their size, fonts and colour 
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // making sure the frame closes when the 'x' button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setting up and setting the computer store logo in the frame, scaling the image so it actually fits 
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("graphics/logo.jpg"));
        JLabel logoLabel = new JLabel(logoIcon);

        // Scale up the components, so they look a little bigger on screen 
        float scaleFactor = 1.2f;
        AffineTransform transform = new AffineTransform();
        transform.scale(scaleFactor, scaleFactor);
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(transform));
        usernameField.setFont(usernameField.getFont().deriveFont(transform));
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(transform));
        passwordField.setFont(passwordField.getFont().deriveFont(transform));
        loginButton.setFont(loginButton.getFont().deriveFont(transform));
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage()
                .getScaledInstance((int) (logoIcon.getIconWidth() * scaleFactor), -1, Image.SCALE_SMOOTH)));

        // Add the components to a panel in a layout that I thought looked good, setting up the padding for the components 
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(191, 219, 255));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 20, 0);
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        panel.add(logoLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20, 20, 20, 20);
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(usernameField, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(passwordLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER; 

        // adding the log in button to the panel 
        panel.add(loginButton, c);

        // adding the panel to the actual frame 
        add(panel);
        // making sure the frame is visible
        setVisible(true);

        // adding an action listener to the login button that when clicked goes through all the computer store's employees and tries to match the user and pass word fields with any of the employees usernames and passwords 
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // if the log in is valid then run the storeMenu screen and pass the logged in member and the computer store in the constructor to set it there as well 
                if (validLogIn()) {
                    computerStore.setLoggedInMember(member);
                    StoreMenu sm = new StoreMenu(computerStore, member);
                    sm.setStoreMenu(sm);
                    // get rid of this current frame 
                    dispose();
                    // if the log in isn't valid display an error message and clear the user and password fields 
                } else {
                    String errorMessage = "Invalid login. Please try again.";
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }

    // setter method for the logged in member 
    public void setLoggedInMember(StaffMember employee) {
        this.member = employee;
    }

    // setter method for the storeMenu 
    public void setStoreMenu(StoreMenu sm) {
        this.sm = sm;
    }

    // method to check if the user trying to log in is valid 
    public boolean validLogIn() {

        // makes a string out of the username and password field 
        String enteredUsername = usernameField.getText();
        String enteredPassword = new String(passwordField.getPassword());
        // goes through all of computerStore's employees and and checks to see if the attempted log in details match any of the stored employees details 
        for (StaffMember employee : computerStore.getAllEmployees()) {
            if (enteredUsername.equals(employee.getUserName()) && enteredPassword.equals(employee.getPassword())) {
                // if a match is found set the current employee as the logged in employee, then return true 
                setLoggedInMember(employee);
                return true;
            }
        }// no match has been found so return false
        return false;
    }

}
