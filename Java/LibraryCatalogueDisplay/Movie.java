/* the movie object class which is a subclass of Item.
 * here is where the movie specific properties are stored and set.
 */

public class Movie extends Item{

    // movie specific properties
    private String director;

    // the movie constructor, where the super class constructor is called to set its own properties
    // and the movie properties are also stored and set here.
    public Movie(String type, int ID, String title, int year, String director) {
        super(type, ID, title, year);
        setDirector(director);
    }

    // setter method for the director 
    public void setDirector(String director) {
        this.director = director;
    }

    // getter method for the director
    public String getDirector() {
        return director;
    }

    // getter for the typeSpecific information- taken from the Item superclass' abstract method.
    // returns the type specific information for movie object
    public String getTypeSpecificString() {
        return "\nDirector: " + getDirector(); 
    }
}
