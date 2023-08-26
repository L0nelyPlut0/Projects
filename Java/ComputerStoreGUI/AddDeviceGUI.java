import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddDeviceGUI extends JFrame {
    // data fields needed for AddDeviceGUI
    private StoreMenu sm;
    private Laptop newDevice;
    private ComputerStore computerStore;

    // all variables for the frame components here
    private JLabel modelIdLabel, deviceTypeLabel, deviceCategoryLabel, brandLabel, cpuFamilyLabel, memorySizeLabel,
            ssdSizeLabel, screenSizeLabel, priceLabel;

    private JTextField modelIdTextField, brandTextField, cpuFamilyTextField, memorySizeTextField, ssdSizeTextField,
            screenSizeTextField, priceTextField;

    private JComboBox<String> deviceTypeComboBox, deviceCategoryComboBox;

    // constructor for the AddDeviceGUI where a lot of set up is done. store menu
    // and computer store are passed to the constructor and set in constructor
    public AddDeviceGUI(StoreMenu sm, ComputerStore store) {
        // a new laptop device is passed in as the 'new' device with some blank
        // arguments (something needed to be passed in and blank strings work just fine)
        Laptop newDevice = new Laptop("Laptop", "", "", "", "", 0, 0, 0.0, 0.0);
        // setting store and computerStore
        setStore(sm);
        setComputerStore(store);
        // setting the newDevice to the newly created 'new' laptop device
        setNewDevice(newDevice);
        // setting window size, position, close action and title
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Add new device");

        // creating all labels for the frame
        modelIdLabel = new JLabel("Model ID:");
        deviceTypeLabel = new JLabel("Device Type:");
        deviceCategoryLabel = new JLabel("Device Category:");
        brandLabel = new JLabel("Brand:");
        cpuFamilyLabel = new JLabel("CPU Family:");
        memorySizeLabel = new JLabel("Memory Size:");
        ssdSizeLabel = new JLabel("SSD Size:");
        screenSizeLabel = new JLabel("Screen Size:");
        priceLabel = new JLabel("Price:");

        // setting label alignment to the right so it is close to the text fields
        modelIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        deviceTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        deviceCategoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        brandLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cpuFamilyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        memorySizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ssdSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        screenSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // setting all text fields and setting the device specific ones as blank strings
        modelIdTextField = new JTextField("");
        brandTextField = new JTextField("");
        cpuFamilyTextField = new JTextField("");
        priceTextField = new JTextField("");
        memorySizeTextField = new JTextField();
        ssdSizeTextField = new JTextField();
        screenSizeTextField = new JTextField();

        // create the drop downs for the device type and device category- setting it to
        // laptop initially as a laptop was made as the new device (but it had to be set
        // to something)
        deviceTypeComboBox = new JComboBox<String>(new String[] { "Desktop PC", "Laptop", "Tablet" });
        deviceTypeComboBox.setSelectedItem(newDevice.getType());
        deviceCategoryComboBox = new JComboBox<String>();

        // setting up the main panel and then the 'field panel' which hold the device
        // text fields and keeps them contained in their own little space so the edit,
        // exit and update button can stay at the bottom of the screen no matter what
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel fieldPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        // adding all the different labels and text fields to the panel
        fieldPanel.add(modelIdLabel);
        fieldPanel.add(modelIdTextField);
        fieldPanel.add(deviceTypeLabel);
        fieldPanel.add(deviceTypeComboBox);
        fieldPanel.add(deviceCategoryLabel);
        fieldPanel.add(deviceCategoryComboBox);
        fieldPanel.add(brandLabel);
        fieldPanel.add(brandTextField);
        fieldPanel.add(cpuFamilyLabel);
        fieldPanel.add(cpuFamilyTextField);
        fieldPanel.add(memorySizeLabel);
        fieldPanel.add(memorySizeTextField);
        fieldPanel.add(ssdSizeLabel);
        fieldPanel.add(ssdSizeTextField);
        fieldPanel.add(screenSizeLabel);
        fieldPanel.add(screenSizeTextField);
        fieldPanel.add(priceLabel);
        fieldPanel.add(priceTextField);

        // adding the fieldPanel to the main panel in the middle
        panel.add(fieldPanel, BorderLayout.CENTER);

        // making new panel to hold all the buttons
        JPanel buttonPanel = new JPanel();

        // making the different buttons and setting their text
        JButton exitButton = new JButton("Exit");
        JButton addButton = new JButton("Add this device");
        JButton clearButton = new JButton("Clear");

        // adding all the buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        // adding the button panel to the main panel in the bottom area of the panel
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // adding the main panel to the frame
        add(panel);

        // setting the frame to be visible
        setVisible(true);

        // adding an action listener to the device type dropdown
        deviceTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // string for the selected device type from the device type dropdown
                String selectedType = (String) deviceTypeComboBox.getSelectedItem();
                // change device category options based on whatever the device type is
                switch (selectedType) {
                    case "Desktop PC":
                        deviceCategoryComboBox.setModel(new DefaultComboBoxModel<>(
                                new String[] { "Gaming", "Home & Study", "Business", "Compact" }));
                        break;
                    case "Laptop":
                        deviceCategoryComboBox.setModel(new DefaultComboBoxModel<>(
                                new String[] { "Gaming", "Home & Study", "Business", "Thin & Light" }));
                        break;
                    case "Tablet":
                        deviceCategoryComboBox.setModel(new DefaultComboBoxModel<>(
                                new String[] { "Android", "Apple", "Windows" }));
                        break;
                }

                // strings for the current device type and the new type which is taken from what
                // ever the selected type is in the drop down menu
                String previousType = newDevice.getType();
                String currentType = (String) deviceTypeComboBox.getSelectedItem();

                // switch out the displayed text fields based on what ever type is selected and
                // add and remove them accordingly based on what ever the previous type was
                switch (currentType) {
                    case "Desktop PC":
                        if (previousType.equals("Laptop")) {
                            fieldPanel.remove(screenSizeLabel);
                            fieldPanel.remove(screenSizeTextField);
                        } else if (previousType.equals("Tablet")) {
                            fieldPanel.remove(screenSizeLabel);
                            fieldPanel.remove(screenSizeTextField);
                            fieldPanel.add(memorySizeLabel);
                            fieldPanel.add(memorySizeTextField);
                            fieldPanel.add(ssdSizeLabel);
                            fieldPanel.add(ssdSizeTextField);
                        }
                        // refreshing the panel so the changes to the text fields show up
                        // same is repeated below for all different type selections
                        fieldPanel.revalidate();
                        fieldPanel.repaint();
                        break;
                    case "Laptop":
                        if (previousType.equals("Desktop PC")) {
                            fieldPanel.add(screenSizeLabel);
                            fieldPanel.add(screenSizeTextField);
                        } else if (previousType.equals("Tablet")) {
                            fieldPanel.add(memorySizeLabel);
                            fieldPanel.add(memorySizeTextField);
                            fieldPanel.add(ssdSizeLabel);
                            fieldPanel.add(ssdSizeTextField);
                        }
                        fieldPanel.revalidate();
                        fieldPanel.repaint();
                        break;
                    case "Tablet":
                        if (previousType.equals("Desktop PC")) {
                            fieldPanel.remove(memorySizeLabel);
                            fieldPanel.remove(memorySizeTextField);
                            fieldPanel.remove(ssdSizeLabel);
                            fieldPanel.remove(ssdSizeTextField);
                            fieldPanel.add(screenSizeLabel);
                            fieldPanel.add(screenSizeTextField);
                        } else if (previousType.equals("Laptop")) {
                            fieldPanel.remove(memorySizeLabel);
                            fieldPanel.remove(memorySizeTextField);
                            fieldPanel.remove(ssdSizeLabel);
                            fieldPanel.remove(ssdSizeTextField);
                        }
                        fieldPanel.revalidate();
                        fieldPanel.repaint();
                        break;
                } // change the device type to what ever is currently selected, this process above
                  // loops as many times until the user stops changing the device type
                newDevice.setType(currentType);
            }
        });

        // adding an action listener to the add button, where the new devices text
        // fields and model ID are checked to make sure they're valid input
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // checking if any text fields are blank
                if (checkCompleteFieldInput()) {
                    // if the device text fields have valid in put in their respective fields
                    // continue on
                    if (checkIfNewDeviceIsValid()) {
                        addButton.setEnabled(true);
                        // check if the model ID is not already in the list of all devices
                        if (checkModelID(store.getAllDevices())) {
                            // make a new device with the supplied information in the text fields
                            makeNewDevice(newDevice);
                            // add the newly created device to the storeMenus JTable with the addNewDevice
                            // method
                            sm.addNewDevice(newDevice);
                            // display success message and close the addDevice window- takes user back to
                            // StoreMenuGUI where the Jtable is
                            String successMessage = "New device added successfully.";
                            JOptionPane.showMessageDialog(null, successMessage, "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            // else- the model ID already exists
                        } else {
                            // display the error message for invalid ID
                            String errorMessage = "Invalid input. Model ID already exists. Please try again.";
                            // set the model ID text fields to blank again
                            modelIdTextField.setText("");
                            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                            // re-enable the addbutton so the user can click it again to try and re add when
                            // they fix the model ID
                            addButton.setEnabled(true);
                        }
                        // else- there is some problem with how the user has enter device information
                        // in the text fields, prompted correct formatting- then add button is re
                        // enabled for user to try and add again
                    } else {
                        String errorMessage = "Error, invalid device input. Please try again. (memory & ssd size = int, price & screen size = double";
                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        addButton.setEnabled(true);
                    }
                } else {
                    String errorMessage = "Error, text fields can not be blank. ";
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    addButton.setEnabled(true);
                }
            }
        });

        // action listener added to the clear button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // method to clear all text fields
                clearAllFields();
            }
        });

        // action listener added to the exit button that disposes of the add button gui
        // and takes user back to the storeMenu gui
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // setter method for storeMenu
    public void setStore(StoreMenu sm) {
        this.sm = sm;
    }

    // setter method for computerStore
    public void setComputerStore(ComputerStore store) {
        this.computerStore = store;
    }

    // setter method for new device
    public void setNewDevice(Laptop newDevice) {
        this.newDevice = newDevice;
    }

    // method to check if a device ID is valid
    public boolean checkModelID(ArrayList<Device> allDevices) {
        String newModelID = modelIdTextField.getText();
        for (Device device : allDevices) {
            // if a match is found the ID isn't valid, return false
            if (device.getID().equals(newModelID)) {
                return false;
            }
        } // if no match is found the ID is valid, return true
        return true;
    }

    // a method to clear all text fields, sets them all to blank strings
    public void clearAllFields() {
        modelIdTextField.setText("");
        brandTextField.setText("");
        cpuFamilyTextField.setText("");
        memorySizeTextField.setText("");
        ssdSizeTextField.setText("");
        screenSizeTextField.setText("");
        priceTextField.setText("");
    }

    // method used to make a new device from the text field data after it has been
    // validated
    public Device makeNewDevice(Device newDevice) {
        // gets input from device setters from the text fields and drop down menus
        newDevice.setType(String.valueOf(deviceTypeComboBox.getSelectedItem()));
        newDevice.setCategory(String.valueOf(deviceCategoryComboBox.getSelectedItem()));
        newDevice.setID(modelIdTextField.getText());
        newDevice.setBrand(brandTextField.getText());
        newDevice.setCpuFamily(cpuFamilyTextField.getText());
        newDevice.setPrice(Double.parseDouble(priceTextField.getText()));
        // Create the appropriate device based on the selected type
        switch (newDevice.getType()) {
            case "Desktop PC":
                newDevice.setMemorySize(Integer.parseInt(memorySizeTextField.getText()));
                newDevice.setSSDCapacity(Integer.parseInt(ssdSizeTextField.getText()));
                break;
            case "Laptop":
                newDevice.setMemorySize(Integer.parseInt(memorySizeTextField.getText()));
                newDevice.setSSDCapacity(Integer.parseInt(ssdSizeTextField.getText()));
                newDevice.setScreenSize(Double.parseDouble(screenSizeTextField.getText()));
                break;
            case "Tablet":
                newDevice.setScreenSize((Double.parseDouble(screenSizeTextField.getText())));
                break;
        }
        // adds new device to computer stores list of all devices so it can be displayed
        // in the table
        computerStore.getAllDevices().add(newDevice);
        return newDevice;
    }

    // method to check if the text fields have valid input before making a new
    // device out of them
    public boolean checkIfNewDeviceIsValid() {
        // turns text field data into strings
        String brand = brandTextField.getText();
        String cpuFamily = cpuFamilyTextField.getText();
        String priceText = priceTextField.getText();
        String memorySizeText = memorySizeTextField.getText();
        String ssdSizeText = ssdSizeTextField.getText();
        String screenSizeText = screenSizeTextField.getText();

        // if statement using regex to check if the text field input is valid or not, if
        // not valid return false, it is is valid return true
        if (!brand.matches(".+") || !cpuFamily.matches(".+") || !priceText.matches("[0-9]+(\\.[0-9]{1,2})?")
                || !memorySizeText.matches("[0-9]+") || Integer.parseInt(memorySizeText) < 0
                || !ssdSizeText.matches("[0-9]+") || Integer.parseInt(ssdSizeText) < 0
                || !screenSizeText.matches("[0-9]+(\\.[0-9]{1,2})?")) {
            return false;
        } else {
            return true;
        }
    }

    // method to check if any of the text fields are blank.
    // if any of the text fields are blank disable the add button- can't add
    // something to the list of devices if you haven't inputted all the device
    // information
    public boolean checkCompleteFieldInput() {
        if (modelIdTextField.getText().equals("") || brandTextField.getText().equals("")
                || cpuFamilyTextField.getText().equals("")
                || memorySizeTextField.getText().equals("") || ssdSizeTextField.getText().equals("")
                || screenSizeTextField.getText().equals("") || priceTextField.getText().equals("")) {
            return false;
        } else {
            return true;
        }

    }
}