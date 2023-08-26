import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ComputerStore {
    // data fields for the computer store, arrayList for all stored devices, StaffMember for the currently logged in member
    // arrayList of all employees of the computer store
    protected ArrayList<Device> allDevices = new ArrayList<Device>();
    private StaffMember loggedInMember;
    private ArrayList<StaffMember> allEmployees = new ArrayList<StaffMember>();

    // constructor for the computer store, adds all devices and employees to the store 
    public ComputerStore() {
        catalogueDevices();
        makeAllEmployees();
    }

    // method to read all devices for computer.txt file and add them to an arrayList
    public void catalogueDevices() {
        ArrayList<Device> devices = new ArrayList<Device>();
        try { 
            // read computers from the txt file, separated by ','
            BufferedReader reader = new BufferedReader(new FileReader("computers.txt"));
            String line = reader.readLine();
            // keep reading until there are no more lines of text to read 
            while (line != null) { 
                String [] fields = line.split(",");

                // if the first item on the line is 'Desktop' make a desktop object and have the following CSVs on that line added to the PC constructor 
                    if (fields[0].equals("Desktop PC")) {
                    PC pc = new PC(fields[0], fields[1], fields[2], fields[3], fields[4], Integer.parseInt(fields[5]), Integer.parseInt(fields[6]), Double.parseDouble(fields[7]));
                    // add the new PC to the arrayList of all devices 
                    devices.add(pc); 
                } // continue the above steps of checking device type and make the type at field [0] for Laptop and Tablet as well 
                else if (fields[0].equals("Laptop")) {
                    Laptop laptop = new Laptop(fields[0], fields[1], fields[2], fields[3], fields[4], Integer.parseInt(fields[5]), Integer.parseInt(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]));
                    devices.add(laptop);
                }
                else if (fields[0].equals("Tablet")) {
                    Tablet tablet = new Tablet(fields[0], fields[1], fields[2], fields[3], fields[4], Double.parseDouble(fields[5]), Double.parseDouble(fields[6]));
                    devices.add(tablet);
                }   
                // when the current line is over move on to the next line 
                line = reader.readLine();
            }// close reader when finished reading
            reader.close();
            // if there is an error print out the error message
        } catch (Exception e) {
            System.out.println("An error occurred. ");
            e.printStackTrace();
        } // set the devices list just created to computerStore's allDevices list 
        setAllDevices(devices);
    }
    
    // getter method for all devices 
    public ArrayList<Device> getAllDevices() {
        return allDevices;
    }
    
    // setter method for all devices 
    public void setAllDevices(ArrayList<Device> allDevices) {
        this.allDevices = allDevices;
    }
    
    // method to make all employees for the computerStore, setting their username, password and role in the constructor 
    public void makeAllEmployees () {
        StaffMember sm1 = new StaffMember("p1", "p1", "Saleperson");
        StaffMember sm2 = new StaffMember("p2", "p2", "Saleperson");
        StaffMember sm3 = new StaffMember("p3", "p3", "Saleperson");
        StaffMember sm4 = new StaffMember("m1", "m1", "Manager");
        StaffMember sm5 = new StaffMember("m2", "m2", "Manager");
        // adding all created employees to the allEmployees arrayList
        allEmployees.add(sm1);
        allEmployees.add(sm2);
        allEmployees.add(sm3);
        allEmployees.add(sm4);
        allEmployees.add(sm5);
    }

    // getter method for allEmployees arrayList
    public ArrayList<StaffMember> getAllEmployees() {
        return allEmployees;
    }

    // setter method for the currently logged in employee
    public void setLoggedInMember(StaffMember member) {
        this.loggedInMember = member;
    }

    // getter method for the currently logged in employee 
    public StaffMember getLoggedInMember() {
        return loggedInMember;
    }
}
