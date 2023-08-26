/* the class that does the main heavy lifting in the computer product GUI program. 
 * this is where all the devices from the stored arrayList is put into a JTable and then 
 * the employee using the system can interact with that data in different ways depending on their role
 * and what they want to do 
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class StoreMenu extends JFrame {

    // needed data fields for the storeMenu class
    private StoreMenu sm;
    private ComputerStore store;
    private StaffMember member;
    private int selectedRowIndex;
    private JLabel deviceTypeLabel;
    private JComboBox<String> deviceTypeComboBox;
    private JLabel deviceCategoryLabel;
    private JComboBox<String> deviceCategoryComboBox;
    private JTable deviceTable;
    private JButton logoutButton;

    // constructor for the storeMenu class, sets the computerStore and to current
    // loogged in member, passed from the login menu
    public StoreMenu(ComputerStore store, StaffMember member) {
        setStore(store);
        setLoggedInMember(member);

        // set up for the frame, setting size and exit button action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 850));
        // giving a title to the frame
        setTitle("Computer Products Management System");

        // making a new panel to go in the frame and setting up its colour and layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(191, 219, 255));
        GridBagConstraints c = new GridBagConstraints();

        // setting up the device type dropdown label, adding it to the panel
        deviceTypeLabel = new JLabel("Device Type:");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 20, 0, 20);
        panel.add(deviceTypeLabel, c);

        // setting up the dropdown box for the device type and adding it to the panel
        deviceTypeComboBox = new JComboBox<String>(new String[] { "ALL", "Desktop PC", "Laptop", "Tablet" });
        c.gridx = 1;
        c.gridy = 0;
        panel.add(deviceTypeComboBox, c);

        // setting up the device category dropdown label, adding it to the panel
        deviceCategoryLabel = new JLabel("Device Category:");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 20, 0, 20);
        panel.add(deviceCategoryLabel, c);

        // setting up the dropdown box for the device category and adding it to the
        // panel
        deviceCategoryComboBox = new JComboBox<String>(
                new String[] { "ALL", "Gaming", "Home & Study", "Business", "Compact", "Thin & Light", "Android",
                        "Apple", "Windows" });
        c.gridx = 1;
        c.gridy = 1;
        panel.add(deviceCategoryComboBox, c);

        // Add listener to device type dropdown, which changes what is displayed in the
        // device category dropdown depending on what device type is selected in the
        // dropdown
        deviceTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String deviceTypeSelected = (String) deviceTypeComboBox.getSelectedItem();
                // switch statement to change category dropdown based on whatever the device
                // type dropdown is set on
                switch (deviceTypeSelected) {
                    case "ALL":
                        deviceCategoryComboBox.setModel(new DefaultComboBoxModel<String>(
                                new String[] { "ALL", "Gaming", "Home & Study", "Business", "Compact", "Thin & Light",
                                        "Android", "Apple", "Windows" }));
                        break;
                    case "Desktop PC":
                        deviceCategoryComboBox.setModel(new DefaultComboBoxModel<String>(
                                new String[] { "ALL", "Gaming", "Home & Study", "Business", "Compact" }));
                        break;
                    case "Laptop":
                        deviceCategoryComboBox.setModel(new DefaultComboBoxModel<String>(
                                new String[] { "ALL", "Gaming", "Home & Study", "Business", "Thin & Light" }));
                        break;
                    case "Tablet":
                        deviceCategoryComboBox.setModel(new DefaultComboBoxModel<String>(
                                new String[] { "ALL", "Android", "Apple", "Windows" }));
                        break;
                    default:
                        break;
                }

                // refreshing the full JTable once 'all' is selected, otherwise the type
                // dropdown needed to be clicked twice for it to work correctly
                String selectedDeviceType = (String) deviceTypeComboBox.getSelectedItem();
                if (selectedDeviceType.equals("ALL")) {
                    // filter the device table with all devices
                    filterTable(store.getAllDevices());
                } else {
                    // filter the table with devices of the selected type
                    filterTable(store.getAllDevices());
                }

                // updating the table with filtered results
                updateTable(filterTable(store.getAllDevices()));
            }
        });

        // an action listener to filter and update the table based on whatever device
        // category is selected
        deviceCategoryComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTable(filterTable(store.getAllDevices()));
            }
        });

        // adding the manager specific button to the GUI, checking if the logged in
        // member is manager, if yes add the button, if not dont add the buttons
        if (member.isManager()) {
            // make new add device button, set its font, constraints, layout and the padding
            JButton addButton = new JButton("Add new device");
            Font buttonFont = new Font(addButton.getFont().getName(), Font.BOLD, 16); // Adjust the font size as desired
            addButton.setFont(buttonFont);
            c.gridx = 1;
            c.gridy = 0;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.NORTHEAST;
            c.insets = new Insets(20, 0, 0, 20);
            // add the add button to the panel
            panel.add(addButton, c);

            // set up an action listener for the add button, run addNewDeviceGUI when add
            // button is clicked and make that visible
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AddDeviceGUI addGUI = new AddDeviceGUI(getStoreMenu(), store);
                    addGUI.setVisible(true);
                }
            });
        }

        /// Create the table and add it to the panel, used a lot of formatting to get it
        /// to not look ugly from the default settings... this was painful trial and
        /// error and many many google attempts to find nice formatting- so I hope it
        /// looks good to you :)
        String[] columnNames = { "Type", "Category", "ID", "Brand", "CPU Family", "Price" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        deviceTable = new JTable(tableModel);
        Font tableFont = deviceTable.getFont().deriveFont(Font.BOLD, 16); // Set the font size to 16 and make it bold
        deviceTable.setFont(tableFont); // Set the font for the table cells
        // Set the row height to create spacing between rows
        deviceTable.setRowHeight(30); // Adjust the value as needed to increase or decrease the row height

        // Set the intercell spacing to add padding between columns
        deviceTable.setIntercellSpacing(new Dimension(10, 10)); // Set the desired padding between columns and rows

        // all set up for getting the table in the panel, make a new JTable instance
        JTableHeader tableHeader = deviceTable.getTableHeader();
        tableHeader.setReorderingAllowed(false); // Disable column reordering

        // make new scroll bar for JTable and set up the layout and constraints for the
        // table and scroll bar
        JScrollPane scrollPane = new JScrollPane(deviceTable);
        deviceTable.setDefaultEditor(Object.class, null);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        // add the scroll bar to the panel
        panel.add(scrollPane, c);

        // adding a mouse listener for when a row is clicked in the JTable
        deviceTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // if a row has been clicked get that selected row, and set that selected row to
                // this classes selected row data field to be used in editing devices
                if (deviceTable.getSelectedRow() != -1) {
                    int selectedRow = deviceTable.getSelectedRow();
                    sm.setSelectedRowIndex(selectedRow);
                    // get the device ID at the selected row, then loop through all devices in all
                    // device list and try and match the selectedID with the current device ID
                    String selectedID = (String) deviceTable.getValueAt(selectedRow, 2);
                    for (Device device : store.getAllDevices()) {
                        // when the device ID match is found make a new deviceInformationGUI and pass in
                        // the StoreMenu, ComputerStore, selectedDevice and the logged in member as
                        // arguments
                        if (selectedID.equals(device.getID())) {
                            new DeviceInformationGUI(sm, store, device, getLoggedInMember());
                            break;
                        }
                    }

                }
            }
        });

        // Populate the table model with all devices
        ArrayList<Device> allDevices = store.getAllDevices();
        // go through each device data getter and add the returned result as a string
        for (Device device : allDevices) {
            String type = device.getType();
            String category = device.getCategory();
            String id = device.getID();
            String brand = device.getBrand();
            String cpuFamily = device.getCpuFamily();
            double price = device.getPrice();

            // make an array of all device information per row and then add the row to the
            // table model;
            Object[] rowData = { type, category, id, brand, cpuFamily, price };
            tableModel.addRow(rowData);
        }

        // Add logout button
        logoutButton = new JButton("Logout");

        // action listener for logout button, prompt user if they actually want to
        // logout, if yes dispose of the window and make new login screen,
        // if not just close the logout confirm window and keep being on same screen
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(StoreMenu.this, "Are you sure you want to log out?",
                        "Logout", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginScreen(store);
                }
            }
        });

        // set up layput and constraints of logout button and add it to the panel
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(0, 20, 20, 20);
        panel.add(logoutButton, c);

        // add the panel to the frame and pack it to make sure the layout is set as the
        // windows preferred constraints
        add(panel);
        pack();
        setLocationRelativeTo(null);
        // make frame visible
        setVisible(true);
    }

    // setter method for the store menu
    public void setStoreMenu(StoreMenu sm) {
        this.sm = sm;
    }

    // getter method for the store menu
    public StoreMenu getStoreMenu() {
        return sm;
    }

    // setter method for the computer store
    public void setStore(ComputerStore store) {
        this.store = store;
    }

    // getter method for the computer store
    public ComputerStore getComputerStore() {
        return store;
    }

    // setter method for the selected row
    public void setSelectedRowIndex(int rowIndex) {
        this.selectedRowIndex = rowIndex;
    }

    // getter method for the selected row
    public int getSelectedRowIndex() {
        return selectedRowIndex;
    }

    // setter method for the logged in member
    public void setLoggedInMember(StaffMember member) {
        this.member = member;
    }

    // getter method for the logged in member
    public StaffMember getLoggedInMember() {
        return member;
    }

    // method that updates the table based on the array list provided (is provided
    // by filterTable method, gives back a filter arraylist based on the 2 drop down
    // menus )
    public void updateTable(ArrayList<Device> devices) {
        // gets whatever table model currently used
        DefaultTableModel tableModel = (DefaultTableModel) deviceTable.getModel();

        // gets rid of all rows in the table
        tableModel.setRowCount(0);

        // goes through the devices lis provided and re-adds them to the table
        for (Device device : devices) {
            String type = device.getType();
            String category = device.getCategory();
            String id = device.getID();
            String brand = device.getBrand();
            String cpuFamily = device.getCpuFamily();
            double price = device.getPrice();

            Object[] rowData = { type, category, id, brand, cpuFamily, price };
            tableModel.addRow(rowData);
        }
    }

    // method to sort out the displayed table based on the 2 drop down menus
    private ArrayList<Device> filterTable(ArrayList<Device> devices) {

        // makes strings out of the selections of deviceType dropdown and deviceCategory
        // dropdown
        String deviceTypeSelected = (String) deviceTypeComboBox.getSelectedItem();
        String deviceCategorySelected = (String) deviceCategoryComboBox.getSelectedItem();
        // makes a new arraylist to store the filter devices in
        ArrayList<Device> filteredDevices = new ArrayList<Device>();

        // filter the devices based on the selected dropdown values
        for (Device device : devices) {
            // go through alldevices and select only those that match the selected type and
            // selected category, then add them to the new array list
            if ((deviceTypeSelected.equals("ALL") || deviceTypeSelected.equals(device.getType()))
                    && (deviceCategorySelected.equals("ALL") || deviceCategorySelected.equals(device.getCategory()))) {
                filteredDevices.add(device);
            }
        } // return the new array list (will be passed into updateTable method)
        return filteredDevices;
    }

    // method used to update the information of an edited device
    public void updateTableWithDevice(Device device) {
        DefaultTableModel tableModel = (DefaultTableModel) deviceTable.getModel();

        // gets the selected row index from when the device was taken from
        int rowIndex = getSelectedRowIndex();

        // turns the device information into strings
        String type = device.getType();
        String category = device.getCategory();
        String id = device.getID();
        String brand = device.getBrand();
        String cpuFamily = device.getCpuFamily();
        double price = device.getPrice();

        // re-adds string information back to the table at its selected index
        tableModel.setValueAt(type, rowIndex, 0);
        tableModel.setValueAt(category, rowIndex, 1);
        tableModel.setValueAt(id, rowIndex, 2);
        tableModel.setValueAt(brand, rowIndex, 3);
        tableModel.setValueAt(cpuFamily, rowIndex, 4);
        tableModel.setValueAt(price, rowIndex, 5);
    }

    // method for adding a new device to the table at the last row
    public void addNewDevice(Device device) {
        DefaultTableModel tableModel = (DefaultTableModel) deviceTable.getModel();

        // gets the passed device (gotten from AddNewDevice) and turns it all into
        // strings and an array of its device information in the order of how its read
        // in the table
        String type = device.getType();
        String category = device.getCategory();
        String id = device.getID();
        String brand = device.getBrand();
        String cpuFamily = device.getCpuFamily();
        double price = device.getPrice();
        Object[] rowData = { type, category, id, brand, cpuFamily, price };
        // add the newly created device to the end of the table
        tableModel.addRow(rowData);
    }
}