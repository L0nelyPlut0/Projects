/* The journal object class which is a subclass of Item.
 * here is where the journal specific properties are stored and set.
 */

public class Journal extends Item {
    
    // journal specific properties
    private int volume;
    private int number;

    // the journal constructor, where the super class constructor is called to set its own properties
    // and the journal properties are also stored and set here.
    public Journal(String type, int ID, String title, int year, int volume, int number) {
        super(type, ID, title, year);
        setVolume(volume);
        setNumber(number);
    }

    // setter method for the volumes
    public void setVolume(int volume) {
        this.volume = volume;
    }

    // getter method for the volumes
    public int getVolume() {
        return volume;
    }

    // setter method for the number
    public void setNumber(int number) {
        this.number = number;
    }

    // getter method for the number
    public int getNumber() {
        return number;
    }

    // getter for the typeSpecific information- taken from the Item superclass' abstract method. 
    // returns type specific information for journal object 
    public String getTypeSpecificString() {
        return "\nVolume: " + getVolume() + "\nNumber: " + getNumber();
    }
}