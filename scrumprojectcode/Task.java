package scrumprojectcode;

import java.time.LocalDateTime; // Handles creationDate attribute @kuriakm
import java.time.format.DateTimeFormatter; // Formats creationDate to MM/dd/YYYY @kuriakm
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Represents a task in a project management system.
 */

/*
 * TODO: Update classes that use getters/setters for Column, Project,
 * ArrayList<User> assignedUser, HashMap<Date, History> taskHistory, Date
 * creationDate, String timeToComplete to new attribute types
 */

public class Task {
    private String taskName;
    private String taskDescription;
    private ArrayList<Comment> taskComments;
    private ArrayList<UUID> assignedUsers; // Was unable to use User object when loading from task.json so I changed it
                                           // to ArrayList<UUID> @kuriakm
    private HashMap<String, History> taskHistory;
    private String creationDate; // Changed creationDate from Date to String to make it easier to load from file
                                 // @kuriakm
    private String taskDueDate;
    private UUID taskUUID;
    private UUID projectUUID;
    private UUID columnUUID;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");

    /**
     * Debugger method, remove in final submission
     */
    /* public Task(UUID user) {
        this.taskUUID = generateUUID();
        this.taskName = "Test Task Name";
        this.taskDescription = "Test Task Description";
        this.taskComments = new ArrayList<Comment>();
        taskComments.add(new Comment(user));
        this.assignedUsers = new ArrayList<UUID>();
        assignedUsers.add(user);
        this.taskHistory = new HashMap<String, History>();
        taskHistory.put(creationDate, new History(user, creationDate, user + " created a task"));
        LocalDateTime dateNow = LocalDateTime.now();
        this.creationDate = formatter.format(dateNow);
        this.taskDueDate = "N/A";
        this.projectUUID = generateUUID();
        this.columnUUID = generateUUID();
    } */

    /**
     * Constructor for Task class.
     *
     * @param taskName        The name of the task.
     * @param taskDescription The description of the task.
     * @param timeToComplete  The estimated time to complete the task.
     */
    public Task(UUID projectID, UUID columnID, String taskTitle, String taskDesc,
            UUID userUUID, String taskDueDate, String taskCreationDate) { // TODO: update Task constructor
        this.taskUUID = generateUUID();
        this.taskName = taskTitle;
        this.taskDescription = taskDesc;
        this.taskComments = new ArrayList<Comment>();
        this.assignedUsers = new ArrayList<>();
        this.assignedUsers.add(userUUID); // Adds first User that created
        this.taskHistory = new HashMap<String, History>();
        this.creationDate = taskCreationDate;
        this.taskDueDate = taskDueDate;
        this.projectUUID = projectID;
        this.columnUUID = columnID;
    }

/**
 * This constructor allows the Bug class to function properly.
 * @param projectID
 * @param columnID
 * @param taskName 
 *   V V V V V V
 *    V V V V V
 *     V V V V
 *      V V V
 *       V V
 *        V
 */
    public Task(UUID projectID, UUID columnID, String taskName) {
        this.taskUUID = generateUUID();
        this.taskName = taskName;
        this.taskDescription = "Default Task Description";
        this.taskComments = new ArrayList<Comment>();
        this.assignedUsers = new ArrayList<UUID>();
        this.taskHistory = new HashMap<String, History>();
        LocalDateTime dateNow = LocalDateTime.now();
        this.creationDate = formatter.format(dateNow);
        this.taskDueDate = "N/A";
        this.projectUUID = projectID;
        this.columnUUID = columnID;
    }
    
    /**
     * creates a Task from DataLoader
     * 
     * @param projectID
     * @param columnID
     * @param taskID
     * @param taskTitle
     * @param taskDesc
     * @param taskUsers
     * @param taskHistory
     * @param taskComments
     * @param taskDueDate
     * @param taskCreationDate
     */
    public Task(UUID projectID, UUID columnID, UUID taskID, String taskTitle, String taskDesc,
            ArrayList<UUID> taskUsers, HashMap<String, History> taskHistory, ArrayList<Comment> taskComments,
            String taskDueDate, String taskCreationDate) { // TODO: update Task constructor
        this.taskUUID = taskID;
        this.taskName = taskTitle;
        this.taskDescription = taskDesc;
        this.taskComments = taskComments;
        this.assignedUsers = taskUsers;
        this.taskHistory = taskHistory;
        this.creationDate = taskCreationDate;
        this.taskDueDate = taskDueDate;
        this.projectUUID = projectID;
        this.columnUUID = columnID;
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
     * Overrides the toString() method to provide a string representation of the
     * task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String commentsToString = "";
        for (Comment comment : taskComments)
            commentsToString = commentsToString + comment.toString();
        return "[Task]: " + this.taskName + "\n"
                + "[Description]: " + this.taskDescription + "\n"
                + "[Due Date]: " + this.taskDueDate + "\n"
                + "[Creation Date]: " + this.creationDate + "\n\n"
                + "[Task Comments]: \n" + commentsToString;
    }

    // getters and setters for all attributes
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

    public ArrayList<UUID> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(ArrayList<UUID> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public HashMap<String, History> getTaskHistory() {
        return taskHistory;
    }

    public void setTaskHistory(HashMap<String, History> taskHistory) {
        this.taskHistory = taskHistory;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDueDate() {
        return taskDueDate;
    }

    public void setDueDate(String dueDate) {
        this.taskDueDate = dueDate;
    }

    public UUID getProjectUUID() {
        return projectUUID;
    }

    public void setProjectUUID(String projectID) {
        this.projectUUID = UUID.fromString(projectID);
    }

    public UUID getColumnUUID() {
        return columnUUID;
    }

    public void setColumnUUID(String columnID) {
        this.columnUUID = UUID.fromString(columnID);
    }

    public UUID getTaskUUID() {
        return taskUUID;
    }

    public void setTaskUUID(String taskID) {
        this.taskUUID = UUID.fromString(taskID);
    }

}
