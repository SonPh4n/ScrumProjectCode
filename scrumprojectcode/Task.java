package scrumprojectcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Task {
    // Attributes
    private String taskName;
    private ArrayList<Comment> taskComments;
    private String taskDescription;
    private ArrayList<User> assignedUsers;
    private Column column;
    private HashMap<Date, History> taskHistory;

    // Additional Attributes
    private Date creationDate;
    private int timeToComplete;
    private UUID taskUUID;

    // Constructor
    public Task(String taskName, String taskDescription, int timeToComplete) {
        // Initialize the Task object with provided parameters
        // Initialize other attributes accordingly
    }

    // Methods
    private UUID generateUUID() {
        // Generate and return a UUID
    }

    public void moveTask(Column newColumn) {
        // Move the task to a new column
    }

    public void addComment(String comment) {
        // Add a comment to the task
    }

    @Override
    public String toString() {
        // Override the toString() method to provide a meaningful string representation
        // of the Task object
        return "";
    }
}

