// subclass of Device, used to make a Laptop specific version of a device initially 
public class Laptop extends Device {
    private int memorySize;
    private int SSDCapacity;
    private double screenSize;

    // constructor for Laptop, setting Laptop specific data fields after a call to the super 
    //class's (Device) constructor has been called to set all the super class methods as well
    public Laptop(String type, String category, String ID, String brand, String cpuFamily, int memorySize,
            int SSDCapacity, double screenSize, double price) {
        super(type, category, ID, brand, cpuFamily, price);
        setMemorySize(memorySize);
        setSSDCapacity(SSDCapacity);
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
        this.SSDCapacity = SSDCapacity;
    }

    // getter method for ssdCapacity 
    public int getSSDCapacity() {
        return SSDCapacity;
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
