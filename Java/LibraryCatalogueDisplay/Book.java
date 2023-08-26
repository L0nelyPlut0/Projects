/* The book object which is a subclass of Item.
 * here is where the book specific properties are stored and set.
 */

public class Book extends Item{

    // book specific properties
    private String author;
    private int pageCount; 


    // the book constructor, where the super class constructor is called to set its own properties
    // and the book properties are also stored and set here.
    public Book(String type, int ID, String title, int year, String author, int pageCount) {
        super(type, ID, title, year);
        setAuthor(author);
        setPageCount(pageCount);
    }

    // setter method for the author 
    public void setAuthor(String author) {
        this.author = author;
    }

    // getter method for the author
    public String getAuthor() {
        return author;
    }

    // setter method for the page count 
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // getter method for the page count
    public int getPageCount() {
        return pageCount;
    }

    // getter for the typeSpecific information- taken from the Item superclass' abstract method. 
    // returns type specific information for book object 
    public String getTypeSpecificString() {
        return "\nAuthor: " + getAuthor() + "\nNumber of pages: " + getPageCount();
    }
}
