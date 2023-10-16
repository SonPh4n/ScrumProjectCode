package scrumprojectcode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Represents a task in a project management system.
 */
public class Task {
    private String taskName;
    private ArrayList<Comment> taskComments;
    private String taskDescription;
    private ArrayList<User> assignedUsers;
    private Column column;
    private HashMap<Date, History> taskHistory;
    private Date creationDate;
    private String timeToComplete;
    private UUID taskUUID;

    /**
     * Constructor for Task class.
     *
     * @param taskName         The name of the task.
     * @param taskDescription  The description of the task.
     * @param timeToComplete   The estimated time to complete the task.
     */
    public Task(String taskName, String taskDescription, String timeToComplete) { //TODO update Task constructor
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.timeToComplete = timeToComplete;
        this.taskUUID = generateUUID();
    }

    /**
     * Generates a UUID for the task.
     *
     * @return The generated UUID for the task.
     */
    private UUID generateUUID() {
        // Implementation to generate UUID
        return UUID.randomUUID();
    }

    /**
     * Moves the task to a different column.
     *
     * @param taskName   The name of the task.
     * @param columnName The name of the target column.
     * @return True if the task was successfully moved, false otherwise.
     */
    public boolean moveTask(String taskName, String columnName) {
        // Implementation for moving the task to a different column
        return false;
    }

    /**
     * Adds a comment to the task.
     *
     * @param comment     The comment to be added.
     * @param projectName The name of the project.
     * @param columnName  The name of the column.
     * @param taskName    The name of the task.
     * @return True if the comment was successfully added, false otherwise.
     */
    public boolean addComment(String comment, String projectName, String columnName, String taskName) {
        // Implementation for adding a comment to the task
        return false;
    }

    /**
     * Overrides the toString() method to provide a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        // Implementation for generating the string representation of the task
        return "Task: " + taskName;
    }

    //getters and setters for all attributes
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public ArrayList<Comment> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(ArrayList<Comment> taskComments) {
        this.taskComments = taskComments;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public ArrayList<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(ArrayList<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public HashMap<Date, History> getTaskHistory() {
        return taskHistory;
    }

    public void setTaskHistory(HashMap<Date, History> taskHistory) {
        this.taskHistory = taskHistory;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(String timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public UUID getTaskUUID() {
        return taskUUID;
    }

    public void setTaskUUID(UUID taskUUID) {
        this.taskUUID = taskUUID;
    }
}



