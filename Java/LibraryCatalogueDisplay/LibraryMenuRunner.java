/*main file to run the library catalog program */

public class LibraryMenuRunner {
    public static void main(String[] args) {

        // creating a new instance of library object
        Library library = new Library();

        // calling the catalog method to add all items from the library text doc into the library object
        library.catalogAllItems();
        
        // displaying all items in the library
        library.displayFullCatalog();

        //running the menu for the user to use the library catalog 
        library.runMenu();
    }

}
