package scrumprojectcode;

import java.util.Date;

/**
 * Represents the history of a task in a scrum project.
 */
public class History {

    private User user;      // Represents the user associated with this history entry
    private String details; // Stores details of the history entry
    private String date;      // Represents the date of the history entry

    /**
     * Constructor to create a History object.
     *
     * @param user    The user associated with this history entry.
     * @param details Details of the history entry.
     * @param date    The date of the history entry.
     */
    public History(User user, String details, String date) {
        // Initialize the History object with provided parameters
        this.user = user;
        this.details = details;
        this.date = date;
    }

    public History(String userID, String details, String date){
        this.user = UUIDtoUser(userID);
        this.details = details;
        this.date = date;
    }

    private User UUIDtoUser(String userID){
        //convert string uuid to object UUID
        return null;
    }

    /**
     * Returns a string representation of the History object.
     *
     * @return A string representing the History object.
     */
    @Override
    public String toString() {
        // Override the toString() method
        // of the History object
        return null;  // Replace with actual logic
    }
}
