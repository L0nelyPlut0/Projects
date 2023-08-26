/* superclass used for making all 3 other subclasses, used mainly to switch device types when 
updating a device stored in the computerStore system. 
 */

public abstract class Device {
    // data fields for superclass fields for all 3 device types
    private String type;
    private String category;
    private String ID;
    private String brand;
    private String cpuFamily;
    private double price;

    // extra fields needed here to be used in both the DeviceInformationGUI and
    // AddDeviceGUI to change
    // device type easily and to pass in device type specific methods that weren't
    // available to the changed devices original class,
    // data fields have been put here as the subclasses extend this class and take
    // these data fields anyway
    private int memorySize;
    private int SSDCapacity;
    private double screenSize;

    // constructor to set up the device information, super passed in all 3 subclass
    // constructors and the information passed in subclass constructors ends up here
    // and is set
    public Device(String type, String category, String ID, String brand, String cpuFamily, double price) {
        setType(type);
        setCategory(category);
        setID(ID);
        setBrand(brand);
        setCpuFamily(cpuFamily);
        setPrice(price);
    }

    // setter method for type
    public void setType(String type) {
        this.type = type;
    }

    // getter method for type
    public String getType() {
        return type;
    }

    // setter method for category
    public void setCategory(String category) {
        this.category = category;
    }

    // getter method for category
    public String getCategory() {
        return category;
    }

    // setter method for ID
    public void setID(String ID) {
        this.ID = ID;
    }

    // getter method for ID
    public String getID() {
        return ID;
    }

    // setter method for brand
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // getter method for brand
    public String getBrand() {
        return brand;
    }

    // setter method for cpu family
    public void setCpuFamily(String cpuFamily) {
        this.cpuFamily = cpuFamily;
    }

    // getter method for cpu family
    public String getCpuFamily() {
        return cpuFamily;
    }

    // setter method for price
    public void setPrice(double price) {
        this.price = price;
    }

    // getter method for price, rounded to 2 decimal places
    public double getPrice() {
        return Math.round(price * 100.0) / 100.0;
    }

    // abstract method for setting device specific information, used when changing
    // device type after type has already been set and created for an object, as it
    // would not let me cast the types
    public abstract void setMemorySize(int memorySize);

    public abstract int getMemorySize();

    public abstract void setSSDCapacity(int ssdCapacity);

    public abstract int getSSDCapacity();

    public abstract void setScreenSize(double screenSize);

    public abstract double getScreenSize();
}
