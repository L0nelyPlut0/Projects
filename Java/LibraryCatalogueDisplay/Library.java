/*Main library object class, where all the sorting, searching and menus are 
 * available for the user to interact with 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Library {
    
    // all library properties 
    private ArrayList<Item> allItems;
    private Item selectedItem;
    // 4 bools used to make sure that other menus don't run after 'q' has be selected to exit main menu 
    protected boolean menuRunning = true;
    protected boolean searchMenu = true;
    protected boolean selectedRunning = true;
    protected boolean rateRunning = true;
    // all scanners declared outside of their method so they can be reused
    private Scanner scannerMenuSelect;
    private Scanner scannerSearch;
    private Scanner scannerSelect;
    private Scanner scannerRate;
    private Scanner scannerSelectedMenu;

    // library constructor, setting up the scanners for use 
    public Library() {
        scannerMenuSelect = new Scanner(System.in);
        scannerSearch = new Scanner(System.in);
        scannerSelect = new Scanner(System.in);
        scannerRate = new Scanner(System.in);
        scannerSelectedMenu = new Scanner(System.in);
    }

    // setter method for all items, taking an ArrayList as argument
    public void setAllItems(ArrayList<Item> allItems) {
        this.allItems = allItems;
    }

    // getter method for all items
    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    // method to catalog all items.
    public void catalogAllItems() {
        // making new ArrayList of item type
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            // opening BufferedReader to read the supplied library txt doc to get all items for the catalog
            BufferedReader reader = new BufferedReader(new FileReader("library.txt"));
            String line = reader.readLine();
            // continue reading until the line is NULL
            while (line != null) {
                // having each item in a line, when it is separated by a comma, showing it to be a new item for the fields array
                String [] fields = line.split(",");
                // if the first element in the line is 'book' then add each following element to the Book constructor as its arguments
                // each element needing to be an int uses parseInt() so that it can be inputted into the constructor correctly. 
                if (fields[0].equals("Book")) {
                    Book book = new Book(fields[0], Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), fields[4], Integer.parseInt(fields[5]));
                    // add the newly created book object to the items ArrayList
                    items.add(book);
                } // then repeat the same as 'book' type for both journal and movie 
                else if (fields[0].equals("Journal")) {
                    Journal journal = new Journal(fields[0], Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]));
                    items.add(journal);
                }
                else if (fields[0].equals("Movie")){
                    Movie movie = new Movie(fields[0], Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]), fields[4]);
                    items.add(movie);
                }
                // move on to the next line in the txt doc 
                line = reader.readLine();
            }
            // close the reader
            reader.close();
            // catch any errors (like if there is something wrong with the txt file, or it it's not there)
        } catch (Exception e) {
            System.out.println("An error occurred. ");
            e.printStackTrace();
            }

            // calling setAllItems method to add the items ArrayList to the library ArrayList field 
            setAllItems(items);
    }

    // method to display all items in the library
    public void displayFullCatalog() {
        System.out.println("List of all items in the library: ");
        // iterate through all items and print out their information
        for (Item item : getAllItems()) {
            String type = String.format("%-8s", item.getType());
            System.out.println("ID: " + item.getID() + "  Type: " + type + "  Title: " + item.getTitle());
        }
    }

    // method to run the main menu
    public void runMenu() {
        // keep running until the menuBoolean is marked false
        while (menuRunning) {
            // print out menu options
        System.out.println("\nEnter 'q' to quit,");
        System.out.println("or enter 's' to sort (first by average rating and then by ID) and display all items, ");
        System.out.println("or enter 'i' to search by ID, title or phrase. ");
        // open scanner for user input, setting to lowercase to make sure that if user puts in capital letter its still valid 
        String selection = scannerMenuSelect.nextLine().toLowerCase();
            
        // switch statement to run different methods depending on user input 
        switch (selection) {
            //if 'q' is selected then mark all menus bools as false so they don't run after 'q' has been selected 
            // close all scanners to prevent resource leaks and free up the used objects for GC 
            case "q":
                searchMenu = false;
                selectedRunning = false;
                menuRunning = false;
                rateRunning = false;
                scannerRate.close();
                scannerSelect.close();
                scannerSelectedMenu.close();
                scannerSearch.close();
                scannerMenuSelect.close();
                System.out.println("\nThank you for using the library.");
                break;
            // if 's' is selected call sort catalog method  
            case "s": 
                sortCatalog();
                break;
            // if 'i' is selected call search method
            case "i": 
                search();
                break;
            // if any other input aside from the other cases is entered then print error message and re-run the main menu method
            default: 
                System.out.println("\nInvalid input, please try again.");
                runMenu();
        }
    }
    }

    // method to search through the library catalog
    public void search() {
        // while search menu bool is marked true, keep searching
        while(searchMenu) {
            // prompt user for input 
        System.out.println("\nPlease enter the ID number, title or phrase of the item you want to search \n(press 'b' to return to the previous menu): ");
        String search = scannerSearch.nextLine().toLowerCase();
        // make a new hasMap 
        HashMap<Integer, Item> itemMap = new HashMap<>();
        int count = 1;
        // if user entered 'b' then re-run main menu 
        if (search.equals("b")) {
            runMenu();
            return; // Added return statement to prevent further execution
        }   
        // loop through all items and see if the item ID, title or phrase match the user entered search.
        // if yes then print out the item details and add it to the hashMap with the current count as the key. Then increment count. 
        for (int i = 0; i < allItems.size(); i++) {
            Item item = allItems.get(i);
            if (item.getTitle().toLowerCase().equals(search) || item.getTitle().toLowerCase().contains(search) || String.valueOf(item.getID()).equals(search)) {
                System.out.println("\nItem " + count + ":\n\tType: " + item.getType() + "   ID: " + item.getID() + "   Title: " + item.getTitle());
                itemMap.put(count, item);
                count++;
            }
        }
        
        // if the size of the hashMap is > 1 (there was more than one item found in a search) then display each item found along with an item number
        if (itemMap.size() > 1) {
            System.out.println("\nPlease enter the number of the item you want to select \n(or press 'b' to return to the search menu): ");
            String selection = scannerSelect.nextLine();
            try {   // try an see if the inputted item selection number matches any of the keys in the item hashMap
                    // set the item selection as the selected item felid 
                    // then display the selected item and run the selectedItem menu 
                int selectionInt = Integer.parseInt(selection);
                if (itemMap.containsKey(selectionInt)) {
                    selectedItem = itemMap.get(selectionInt);
                    System.out.println("\nThe selected item is:");
                    showSelectedItem();
                    showSelectedItemOptions();
                } else { // if the item number inputted is not found run the search menu again and print an error
                    System.out.println("\nInvalid selection. Please try again.");
                    search();
                } // if the inputted selection is 'b', then re-run the search menu again
            } catch (NumberFormatException e) {
                if (selection.toLowerCase().equals("b")) {
                    search();
                } else {
                    // else, throw an error and re-run the search menu 
                    System.out.println("\nInvalid selection. Please try again.");
                    search();
                }
            }
        } // if the item map size is just 1 (then there is only one item found in the search)
          // then show that items details and automatically run the selected item menu
        else if (itemMap.size() == 1) {
            // set selected item felid to that hashmap value 
            selectedItem = itemMap.get(1);
                    System.out.println("\nThe selected item is:");
                    showSelectedItem();
                    showSelectedItemOptions();
        } // no items found matching with entered search, re-run menu and show error message 
        else {
            System.out.println("\nNo items found.");
            search();
        }
    }
    }
    

    // a method to sort the catalog of items
    public void sortCatalog() {
        // make a new ArrayList and populate it with allItems
        // then sort it first by comparing each items average rating
        // if the average rating is 0.0 then sort based on item ID number instead 
        ArrayList<Item> sortedItems = getAllItems()
        .stream()
        .sorted((item1, item2) -> {
            double rating1 = item1.getAverageRating();
            double rating2 = item2.getAverageRating();
            if (rating1 != 0.0 || rating2 != 0.0) {
                return Double.compare(rating2, rating1);
            } else {
                return Integer.compare(item1.getID(), item2.getID());
            }
        }) // put the new sorted ArrayList in to a new ArrayList 
        .collect(Collectors.toCollection(ArrayList::new));

        // print out the sorted ArrayList, first by average rating, then by item ID number 
        System.out.println("\nSorted list of all items in library: ");
        for (Item item : sortedItems) {
            String type = String.format("%-8s", item.getType());
            System.out.println("Average rating: " + item.getAverageRating() + "  ID: " + item.getID() + "  Type: " + type + "Title: " + item.getTitle());
            } // re-run the main menu
            runMenu();
    }

    // method to rate selected item, taking in the selected item as argument 
    public void rateSelectedItem(Item item) {
        System.out.println("\nPlease enter your rating (0-10): ");
        double rating = 0;
        // while rateRunning bool is marked true prompt user for a valid rating
        // if entered rating is invalid, kept prompting user again
        while (rateRunning) {
            try {
                rating = scannerRate.nextDouble();
                if (rating >= 0 && rating <= 10) {
                    break;
                } else {
                    System.out.println("\nError invalid input. Please enter a valid rating (0-10): ");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError invalid input. Please enter a valid rating (0-10): ");
                scannerRate.next(); // to clear the scanner buffer
                continue;
            }
        }   
        // calling item specific methods to add the inputted rating to that item and then set the average rating. 
        item.addRating(rating);
        item.setAverageRating();
        System.out.println("\nThis item's new average rating is " + selectedItem.getAverageRating() + "\n");
    }
    

    // method to print out selected item information and show selected item options menu 
    public void showSelectedItem(){
        while (selectedRunning) {
        System.out.println(selectedItem.toString());
        showSelectedItemOptions();
        }
    }

    // method to show selected item options 
    public void showSelectedItemOptions() {
        // will run as long as selectedRunning is marked true 
        while (selectedRunning) {
        String statusLine = "";
        // switch statement used to set the printed out options based on if the item selected is available- printed options are different for each case 
        switch (selectedItem.getIsAvailable()) {
            case 0: statusLine = "'b' to borrow ";
            break;
            case 1: statusLine = "'r' to return ";
        }
        // prints out options and prompts user for input 
        System.out.println("\nEnter " + statusLine + "this item, enter 'a' to rate this item, or enter any other key to restart. ");
        if (!scannerSelectedMenu.hasNextLine()) {
            break;
        }
        String selected = scannerSelectedMenu.nextLine().toLowerCase();
        switch(selected) {
            // call borrow method and re-run the selected item options 
            case "b": selectedItem.borrowItem();
            showSelectedItem();
            break;
            // call return method and re-run the selected item options
            case "r": selectedItem.returnItem();
            showSelectedItem();
            // call the rate method and re-run the selected item options
            case "a": rateSelectedItem(selectedItem);
            showSelectedItem();
            break;
        }   // if the selected option is anything other than the listed options re-run the search menu
        if (selected != "b" && selected != "a"&& selected != "r") {
            search();
            }
        }
    }
    
}