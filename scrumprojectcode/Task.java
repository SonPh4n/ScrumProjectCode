package scrumprojectcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Represents a task in a scrum project.
 */
public class Task {

    // Attributes
    private String taskName;  // Stores the name of the task
    private ArrayList<Comment> taskComments;  // Stores comments related to the task
    private String taskDescription;  // Stores the description of the task
    private ArrayList<User> assignedUsers;  // Stores users assigned to the task
    private Column column;  // Represents the column the task belongs to
    private HashMap<Date, History> taskHistory;  // Stores the task's history

    // Additional Attributes
    private Date creationDate;  // Stores the creation date of the task
    private int timeToComplete;  // Stores the estimated time to complete the task
    private UUID taskUUID;  // Provides a unique identifier for the task

    /**
     * Constructor to create a Task object.
     *
     * @param taskName        The name of the task.
     * @param taskDescription The description of the task.
     * @param timeToComplete  The estimated time to complete the task.
     */
    public Task(String taskName, String taskDescription, int timeToComplete) {
        // Initialize the Task object with provided parameters
        // Initialize other attributes accordingly
    }

    /**
     * Generates a unique UUID for the task.
     *
     * @return The generated UUID.
     */
    private UUID generateUUID() {
        // UUID generation logic (to be implemented based on application requirements)
        return null;  // Replace with actual logic
    }

    /**
     * Moves the task to a new column.
     *
     * @param newColumn The new column to which the task will be moved.
     */
    public void moveTask(Column newColumn) {
        // Move the task to a new column
    }

    /**
     * Adds a comment to the task.
     *
     * @param comment The comment to be added.
     */
    public void addComment(String comment) {
        // Add a comment to the task
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representing the Task object.
     */
    @Override
    public String toString() {
        // Override the toString() method
        return "";
    }
}


