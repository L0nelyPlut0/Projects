import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeviceInformationGUI extends JFrame {

    // data fields needed for DeviceInformationGUI
    private StoreMenu sm;
    private ComputerStore computerStore;
    private Device selectedDevice;
    private StaffMember member;

    // declaring all components for the frame
    private JLabel modelIdLabel, deviceTypeLabel, deviceCategoryLabel, brandLabel,
            cpuFamilyLabel, memorySizeLabel, ssdSizeLabel, screenSizeLabel, priceLabel;

    private JTextField modelIdTextField, brandTextField, cpuFamilyTextField,
            memorySizeTextField, ssdSizeTextField, screenSizeTextField, priceTextField;

    private JComboBox<String> deviceTypeComboBox, deviceCategoryComboBox;

    // constructor for deviceInformationGUI where computerstore, storeMenu, selected
    // device and logged in member are set
    public DeviceInformationGUI(StoreMenu sm, ComputerStore store, Device selectedDevice, StaffMember member) {
        setTitle("Device Information");
        setComputerStore(store);
        setStore(sm);
        setSelectedDevice(selectedDevice);
        setLoggedInMember(member);

        // setting frame size, location, close action and title
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Check/Update Product Details");

        // creating and giving names to all labels
        modelIdLabel = new JLabel("Model ID:");
        deviceTypeLabel = new JLabel("Device Type:");
        deviceCategoryLabel = new JLabel("Device Category:");
        brandLabel = new JLabel("Brand:");
        cpuFamilyLabel = new JLabel("CPU Family:");
        memorySizeLabel = new JLabel("Memory Size:");
        ssdSizeLabel = new JLabel("SSD Size:");
        screenSizeLabel = new JLabel("Screen Size:");
        priceLabel = new JLabel("Price:");
        modelIdTextField = new JTextField(selectedDevice.getID());
        brandTextField = new JTextField(selectedDevice.getBrand());
        cpuFamilyTextField = new JTextField(selectedDevice.getCpuFamily());
        priceTextField = new JTextField(String.valueOf(selectedDevice.getPrice()));
        memorySizeTextField = new JTextField();
        ssdSizeTextField = new JTextField();
        screenSizeTextField = new JTextField();

        // set the alignment of the labels to right
        modelIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        deviceTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        deviceCategoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        brandLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cpuFamilyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        memorySizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ssdSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        screenSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // create ComboBoxes for device type and category
        deviceTypeComboBox = new JComboBox<String>(new String[] { "Desktop PC", "Laptop", "Tablet" });
        deviceTypeComboBox.setSelectedItem(selectedDevice.getType());

        // making panel for field items
        JPanel fieldPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        // set dropdowns for category based on whatever selected device type is
        switch (selectedDevice.getType()) {
            case "Desktop PC":
                deviceCategoryComboBox = new JComboBox<String>(
                        new String[] { "Gaming", "Home & Study", "Business", "Compact" });
                deviceCategoryComboBox.setSelectedItem(selectedDevice.getCategory());
                break;

            case "Laptop":
                deviceCategoryComboBox = new JComboBox<String>(
                        new String[] { "Gaming", "Home & Study", "Business", "Thin & Light" });
                deviceCategoryComboBox.setSelectedItem(selectedDevice.getCategory());
                break;

            case "Tablet":
                deviceCategoryComboBox = new JComboBox<String>(new String[] { "Android", "Apple", "Windows" });
                deviceCategoryComboBox.setSelectedItem(selectedDevice.getCategory());
                break;
        }

        // action listener for device category drop down menu
        deviceTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // category box changes based on what ever selected type is
                String selectedType = (String) deviceTypeComboBox.getSelectedItem();

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

                // add or remove different text labels and fields depending on what type of
                // device is selected, then set selected type as selected device's new current
                // type
                String previousType = selectedDevice.getType();
                String currentType = (String) deviceTypeComboBox.getSelectedItem();
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
                        selectedDevice.setType(currentType);
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
                        selectedDevice.setType(currentType);
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
                        selectedDevice.setType(currentType);
                        break;
                }
                fieldPanel.revalidate();
                fieldPanel.repaint();
            }
        });

        // create new panel and add all labels and fields to the field panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
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
        modelIdTextField.setEditable(false);

        // add different labels to the panel based on what the selected device type is
        switch (selectedDevice.getType()) {
            case "Desktop PC":
                memorySizeLabel.setText("Memory Size:");
                memorySizeTextField.setText(String.valueOf(selectedDevice.getMemorySize()));
                ssdSizeLabel.setText("SSD Size:");
                ssdSizeTextField.setText(String.valueOf(selectedDevice.getSSDCapacity()));
                fieldPanel.add(memorySizeLabel);
                fieldPanel.add(memorySizeTextField);
                fieldPanel.add(ssdSizeLabel);
                fieldPanel.add(ssdSizeTextField);
                break;

            case "Laptop":
                // Cast selectedDevice to Laptop
                memorySizeLabel.setText("Memory Size:");
                memorySizeTextField.setText(String.valueOf(selectedDevice.getMemorySize()));
                ssdSizeLabel.setText("SSD Size:");
                ssdSizeTextField.setText(String.valueOf(selectedDevice.getSSDCapacity()));
                screenSizeLabel.setText("Screen Size:");
                screenSizeTextField.setText(String.valueOf(selectedDevice.getScreenSize()));
                fieldPanel.add(memorySizeLabel);
                fieldPanel.add(memorySizeTextField);
                fieldPanel.add(ssdSizeLabel);
                fieldPanel.add(ssdSizeTextField);
                fieldPanel.add(screenSizeLabel);
                fieldPanel.add(screenSizeTextField);
                break;

            case "Tablet":
                // Cast selectedDevice to Tablet
                screenSizeLabel.setText("Screen Size:");
                screenSizeTextField.setText(String.valueOf(selectedDevice.getScreenSize()));
                fieldPanel.add(screenSizeLabel);
                fieldPanel.add(screenSizeTextField);
                break;
        }

        // add price label and textfield to field panel
        fieldPanel.add(priceLabel);
        fieldPanel.add(priceTextField);
        // add field panel to main panel in center
        panel.add(fieldPanel, BorderLayout.CENTER);

        // add new panel for buttons
        JPanel buttonPanel = new JPanel();
        // make nex exit button and add it to button panel
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);

        // add action listener to exit button, when exit button is clicked close frame
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        // if logged in member is manager make manager specific buttons
        if (member.isManager()) {
            // make edit, update and delete buttons
            JButton editButton = new JButton("Edit");
            JButton updateButton = new JButton("Update");
            JButton deleteButton = new JButton("Delete");

            // add action listener to delete button
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // if delete button is pressed delete selected device then print out success
                    // message
                    deleteSelectedDevice(selectedDevice);
                    String successMessage = "Device deleted successfully.";
                    JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                    // update table so it reflects the device has been deleted then dispose of this
                    // frame
                    sm.updateTable(computerStore.getAllDevices());
                    dispose();
                }
            });

            // add action listener to the edit button, change update and edit button
            // depending on if the edit button is clicked
            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // set fields to editable
                    setFieldsEditable(true);
                    editButton.setEnabled(false);
                    updateButton.setEnabled(true); // Enable the Update button
                }
            });
            // add edit and delete buttons to button panel
            buttonPanel.add(deleteButton);
            buttonPanel.add(editButton);
            // set fields to not editable
            setFieldsEditable(false);

            // add action listener to update button
            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    // if text field edits are valid then update the selected device with the new
                    // information
                    if (checkIfUpdatedDeviceIsValid()) {
                        updateDeviceInformation(selectedDevice);
                        // update the storeMenu's table to reflect the newly update device information
                        sm.updateTableWithDevice(selectedDevice);
                        // display success message and set the button to different editable states
                        String successMessage = "Device updated successfully.";
                        JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                        editButton.setEnabled(true);
                        updateButton.setEnabled(false);
                        // set fields as not editable
                        setFieldsEditable(false);
                    } else { // if tetx field input is invalid show error message
                        String errorMessage = "Error, invalid device input. Please try again. (memory & ssd size = int, price & screen size = double";
                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }); // set update button as enabled and add it to the button panel
            updateButton.setEnabled(false); // Disable the Update button initially
            buttonPanel.add(updateButton);
        } else { // iff logged in member isn't manager then set all fields as uneditable
            setFieldsEditable(false); // Set fields as non-editable
        } // add button panel to main panel in south position
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // add panel to frame and make it visible
        add(panel);
        setVisible(true);
    }

    // setter method for storeMenu
    public void setStore(StoreMenu store) {
        this.sm = store;
    }

    // getter method for storeMenu
    public StoreMenu getStore() {
        return sm;
    }

    // setter method for computerStore
    public void setComputerStore(ComputerStore computerStore) {
        this.computerStore = computerStore;
    }

    // getter method for computerStore
    public ComputerStore getComputerStore() {
        return computerStore;
    }

    // setter method for logged in member
    public void setLoggedInMember(StaffMember member) {
        this.member = member;
    }

    // getter method for logged in member
    public StaffMember getLoggedInStaffMember() {
        return member;
    }

    // setter method for selected device
    public void setSelectedDevice(Device device) {
        this.selectedDevice = device;
    }

    public Device getSelectedDevice() {
        return selectedDevice;
    }

    // change all text fields editable status based on what boolean is passed
    private void setFieldsEditable(boolean editable) {
        brandTextField.setEditable(editable);
        cpuFamilyTextField.setEditable(editable);
        memorySizeTextField.setEditable(editable);
        ssdSizeTextField.setEditable(editable);
        screenSizeTextField.setEditable(editable);
        priceTextField.setEditable(editable);
        deviceTypeComboBox.setEnabled(editable);
        deviceCategoryComboBox.setEnabled(editable);
    }

    // method to set the selected devices information to what ever the text fields
    // and drop down menus have
    public void updateDeviceInformation(Device device) {
        // Get the updated values from the fields
        String brand = brandTextField.getText();
        String cpuFamily = cpuFamilyTextField.getText();
        String deviceType = null;
        String deviceCategory = null;
        double price = Double.parseDouble(priceTextField.getText());
        int memorySize = 0;
        int ssdSize = 0;
        double screenSize = 0.0;

        // setting the fields for type specific devices
        if (device.getType().equals("Desktop PC") || device.getType().equals("Laptop")) {
            memorySize = Integer.parseInt(memorySizeTextField.getText());
            ssdSize = Integer.parseInt(ssdSizeTextField.getText());
        }

        if (device.getType().equals("Laptop") || device.getType().equals("Tablet")) {
            screenSize = Double.parseDouble(screenSizeTextField.getText());
        }

        if (deviceTypeComboBox.getSelectedItem() != null) {
            deviceType = (String) deviceTypeComboBox.getSelectedItem();
        }

        if (deviceCategoryComboBox.getSelectedItem() != null) {
            deviceCategory = (String) deviceCategoryComboBox.getSelectedItem();
        }

        boolean invalidInput = false;

        // sets invalid input to true if there are blank textfields
        if (brandTextField.getText().equals("") || cpuFamilyTextField.getText().equals("")
                || memorySizeTextField.getText().equals("") || ssdSizeTextField.getText().equals("")
                || screenSizeTextField.getText().equals("") || memorySizeTextField.getText().equals("")
                || screenSizeTextField.getText().equals("") || ssdSizeTextField.getText().equals("")
                || priceTextField.getText().equals("")) {
            invalidInput = true;
        } // if invalid input is true then display and error message
        if (invalidInput) {
            String errorMessage = "Invalid input. Please try again.";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method
        }

        // Update the selected device with the new values
        selectedDevice.setBrand(brand);
        selectedDevice.setCpuFamily(cpuFamily);
        selectedDevice.setPrice(price);
        selectedDevice.setType(deviceType);
        selectedDevice.setCategory(deviceCategory);

        // update device specific setters
        switch (device.getType()) {
            case "Desktop":
                selectedDevice.setMemorySize(memorySize);
                selectedDevice.setSSDCapacity(ssdSize);
                break;
            case "Laptop":
                selectedDevice.setMemorySize(memorySize);
                selectedDevice.setSSDCapacity(ssdSize);
                selectedDevice.setScreenSize(screenSize);
                break;
            case "Tablet":
                selectedDevice.setScreenSize(screenSize);
                break;
        }
    }

    // method to remove the selected device from the stores list of all devices
    public void deleteSelectedDevice(Device selectedDevice) {
        computerStore.getAllDevices().remove(selectedDevice);
    }

    // method to check if the text fields have valid input
    public boolean checkIfUpdatedDeviceIsValid() {
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
}
