/* Main superclass for all the different item types 
 * this is where all the generic methods for Item are defined
*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public abstract class Item {

    // all item specific properties 
    private String title;
    private String type;
    private int ID;
    private int year;
    private int numberOfReviews;
    private int maxBorrowingTime;
    private Double averageRating;
    // an ArrayList of all total ratings- used for calculating average rating
    private ArrayList<Double> allRatings = new ArrayList<Double>();
    // current status of the item
    private boolean isAvailable = true;

    // constructor for Item object, where many of the properties are polymorphically. 
    public Item(String type, int ID, String title, int year) {
        setType(type);
        setTitle(title);
        setID(ID);
        setYear(year);
        // max borrowing time set based on what type the item is 
        setMaxBorrowingTime(type);
        // average rating initially set to 0.0 
        this.averageRating = 0.0;
    }

    // setter method for the title
    public void setTitle(String title) {
        this.title = title;
    }

    // getter method for the title 
    public String getTitle() {
        return title;
    }

    // setter method for the type
    public void setType(String type) {
        this.type = type;
    }

    // getter method for the type
    public String getType() {
        return type;
    }

    // setter method for the ID
    public void setID(int ID) {
        this.ID = ID;
    }

    // getter method for the ID
    public int getID() {
        return ID;
    }

    // setter method for the year 
    public void setYear(int year) {
        this.year = year;
    }

    // getter method for the year
    public int getYear() {
        return year;
    }

    // setter method for the max borrowing time- time is different dependent on the type of item 
    public void setMaxBorrowingTime(String type) {
        switch (type) {
            case "Book":
                this.maxBorrowingTime = 28;
                break;
            case "Journal":
                this.maxBorrowingTime = 14;
                break;
            case "Movie":
                this.maxBorrowingTime = 7;
                break;
        }
    }

    // getter for max borrowing time 
    public int getMaxBorrowingTime() {
        return maxBorrowingTime;
    }

    // getter for due date. Calculates date date based on what the max borrowing time is- different for each type of item. 
    public String getDueDate() {
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(maxBorrowingTime);
        return dueDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    // method to add a rating to the allRatings ArrayList- needed for average rating calculation
    // error checking to make sure rating is in acceptable range 
    public void addRating(double rating) {
        if (rating < 0 || rating > 10) {
            System.out.println("Error, please select a valid rating. ");
        } else {
            // call to set review count, 1 added to it 
            setCurrentReviewCount();
            this.allRatings.add(rating);
        }
    }

    // setter for average rating. Calculates rating from all ratings ArrayList and then sets 
    // the average rating double felid to the calculated rating
    public void setAverageRating() {
        double sum = 0.0;
        int count = 0;
        for (Double rating : allRatings) {
            sum += rating;
            count++;
            this.averageRating = sum / count;
        }
        // printing out the new average rating 
    }

    // getter for the average rating- using Math.round() to round the rating to 2 decimal places. 
    public double getAverageRating() {
        return Math.round(averageRating * 100.0) / 100.0;
    }

    // setter for current reviews, which just increments the reviewer count field. 
    public void setCurrentReviewCount() {
        this.numberOfReviews++;
    }

    // getter for number of reviews
    public int getNumberOfReviewers() {
        return numberOfReviews;
    }

    // method to borrow an item- checking if the item is already on loan
    // if so, print out item is already on loan, else set available to status as unavailable and show user the due date of the item. 
    public void borrowItem() {
        if (isAvailable) {
            setIsAvailable(false);
            System.out.println("\nThis item's due date is: " + getDueDate() + "\n");
        } else {
            System.out.println("\nThis item is already on loan. ");
        }
    }

    // method to return an item- checking if the item is already available
    // if so, print out item is not borrowed, else change status of item to available again and print out item has be returned. . 
    public void returnItem() {
        if (!isAvailable) {
            setIsAvailable(true);
            System.out.println("\nItem has been returned.\n");
        }
        else {
            System.out.println("\nThis item is not on loan.");
        }
    }

    // setter method for item status 
    public void setIsAvailable(boolean status) {
        this.isAvailable = status;
    }

    // int getter for item status, used in Library object
    public int getIsAvailable() {
        if (isAvailable) {
            return 0;
        }
        else {
            return 1;
        }
    }

    // bool getter for item status
    public boolean isAvailable() {
        return isAvailable;
    }

    // to string method for getting item information printed out. 
    // calls getTypeSpecificString() method polymorphically and returns whatever each item has for the overridden method
    public String toString() {
        return "Type: " + getType() + "\nTitle: " + getTitle() + "\nID: " + getID() + "\nYear: " + getYear() + "\nAverage rating: " + getAverageRating() + "\nNumber of reviewers: " + getNumberOfReviewers() + "\nAvailable: " + isAvailable() + getTypeSpecificString() + "\nMax number of days for borrrowing: " + getMaxBorrowingTime();
    }

    // abstract method getTypeSpecificString() method to polymorphically return whatever each item has for the overridden method
    public abstract String getTypeSpecificString();
}
