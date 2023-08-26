// subclass of Device, used to make a PC specific version of a device initially 

public class PC extends Device{
    private int memorySize;
    private int SSDCapacity;
    private double screenSize;

    // constructor for PC, setting PC specific data fields after a call to the super class's (Device) constructor has been called to set all the super class methods as well
    public PC(String type, String catagory, String ID, String brand, String cpuFamily, int memorySize, int SSDCapacity, double price) {
        super(type, catagory, ID, brand, cpuFamily, price);
        setMemorySize(memorySize);
        setSSDCapacity(SSDCapacity);
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
        this.SSDCapacity = SSDCapacity;
    }

    // getter method for ssdCapacity 
    public int getSSDCapacity() {
        return SSDCapacity;
    }

    // has screen size getter and setter for when the device type is changed from PC to Laptop or tablet by a manager in the DeviceInformationGUI
    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    // getter method for screen size 
    public double getScreenSize() {
        return screenSize;
    }

}

