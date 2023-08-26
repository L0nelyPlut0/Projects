// subclass of Device, used to make a Tablet specific version of a device initially 
// has memorySize and ssdCapacity getters and setters for when the device type is changed from PC or Laptop to Tablet by a manager in the DeviceInformationGUI

public class Tablet extends Device {
    private double screenSize;
    private int memorySize;
    private int ssdCapacity;

    // constructor for Tablet, setting Tablet specific data fields after a call to
    // the super class's (Device) constructor has been called to set all the super
    // class methods as well
    public Tablet(String type, String catagory, String ID, String brand, String cpuFamily, double screenSize,
            double price) {
        super(type, catagory, ID, brand, cpuFamily, price);
        setScreenSize(screenSize);
    }

    // setter method for memory size
    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    // getter method for memory size
    public int getMemorySize() {
        return memorySize;
    }

    // setter method for ssd capacity
    public void setSSDCapacity(int SSDCapacity) {
        this.ssdCapacity = SSDCapacity;
    }

    // getter method for ssdCapacity
    public int getSSDCapacity() {
        return ssdCapacity;
    }

    // setter method for screen size
    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    // getter method for screen size
    public double getScreenSize() {
        return screenSize;
    }

}
