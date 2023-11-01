package scrumprojectcode;
/*
 * @kurikam
 * @AidanH002
 */

import java.time.LocalDate;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


/**
 * The Task class represents a task in a project management system. 
 * It contains information about the task such as its 
 * name, description, type, comments, assigned users, history, creation date, due date, and UUID. 
 * It also provides methods to add comments and reply to comments, 
 * find a specific comment, and generate a UUID for the task. 
 * The class overrides the toString() method to provide a string representation of the task.
 */
public class Task {
    private String taskName;
    private String taskDescription;
    private String taskType;
    private ArrayList<Comment> taskComments;
    private ArrayList<UUID> assignedUsers; 
    private HashMap<String, History> taskHistory;
    private String creationDate; 
    private String taskDueDate;
    private UUID taskUUID;
    private UUID projectUUID;
    private UUID columnUUID;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
    private static UserList userList = UserList.getInstance();

    /**
     * Constructor for Task class.
     *
     * @param taskName        The name of the task.
     * @param taskDescription The description of the task.
     * @param timeToComplete  The estimated time to complete the task.
     */
    public Task(UUID projectID, UUID columnID, String taskTitle, String taskDesc, String taskType,
            UUID userUUID, String taskDueDate) { // TODO: update Task constructor
        this.taskUUID = generateUUID();
        this.taskName = taskTitle;
        this.taskDescription = taskDesc;
        this.taskType = taskType;
        this.taskComments = new ArrayList<Comment>();
        this.assignedUsers = new ArrayList<>();
        this.assignedUsers.add(userUUID);
        this.taskHistory = new HashMap<String, History>();

        LocalDateTime now = LocalDateTime.now();
        String creationDate = formatter.format(now);
        String historyDetails = userList.findUser(userUUID).getUsername() + " created " + taskName;
        taskHistory.put(creationDate, new History(userUUID, creationDate, historyDetails));

        this.creationDate = creationDate;
        this.taskDueDate = taskDueDate;
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
    public Task(UUID projectID, UUID columnID, UUID taskID, String taskTitle, String taskDesc, String taskType,
            ArrayList<UUID> taskUsers, HashMap<String, History> taskHistory, ArrayList<Comment> taskComments,
            String taskDueDate, String taskCreationDate) { 
        this.taskUUID = taskID;
        this.taskName = taskTitle;
        this.taskDescription = taskDesc;
        this.taskType = taskType;
        this.taskComments = taskComments;
        this.assignedUsers = new ArrayList<>();
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
        return UUID.randomUUID();
    
    /**
     * Adds a comment to the task.
     *
     * @param comment     The comment to be added.
     * @param projectName The name of the project.
     * @param columnName  The name of the column.
     * @param taskName    The name of the task.
     * @return True if the comment was successfully added, false otherwise.
     */
    public boolean addComment(User user, String comment) {
        Comment newComment = new Comment(comment, user.getUserUUID());
        if (newComment == null)
            return false;
        taskComments.add(newComment);
        LocalDate now = LocalDate.now();
        String date = formatter.format(now);
        History addUserHistory = new History(user.getUserUUID(), date,
                user.getUsername() + "added a comment to " + getTaskName());
        taskHistory.put(date, addUserHistory);
        return true;
    }

    public boolean addReplyComment(User moreUser, String moreComment, User originalUser, String originalComment) {
        for (Comment commentToFind : taskComments) {
            if (findComment(originalComment).equals(commentToFind)) {
                Comment newComment = new Comment(moreComment, moreUser.getUserUUID());
                if (newComment == null)
                    return false;
                commentToFind.getMoreComments().add(newComment);
                LocalDate now = LocalDate.now();
                String date = formatter.format(now);
                History addUserHistory = new History(moreUser.getUserUUID(), date,
                        moreUser.getUsername() + " added a comment to " + originalUser.getUsername() + "'s comment");
                taskHistory.put(date, addUserHistory);
                return true;
            }
        }
        return false;
    }

    public Comment findComment(String comment) {
        for (Comment findComment : taskComments) {
            if (findComment.getComment().equals(comment))
                return findComment;
        }
        return null;
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
                + "[Task Type]: " + this.taskType + "\n"
                + "[Description]: " + this.taskDescription + "\n"
                + "[Due Date]: " + this.taskDueDate + "\n"
                + "[Creation Date]: " + this.creationDate + "\n\n"
                + "[Task Comments]: \n" + commentsToString;
    }

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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public ArrayList<User> getAssignedUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (UUID userUUID : assignedUsers) {
            User uuidToUser = userList.findUser(userUUID);
            users.add(uuidToUser);
        }
        return users;
    }

    public ArrayList<UUID> getAssignedUsersUUID() {
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

    public void setProjectUUID(UUID projectID) {
        this.projectUUID = projectID;
    }

    public UUID getColumnUUID() {
        return columnUUID;
    }

    public void setColumnUUID(UUID columnID) {
        this.columnUUID = columnID;
    }

    public UUID getTaskUUID() {
        return taskUUID;
    }

    public void setTaskUUID(UUID taskID) {
        this.taskUUID = taskID;
    }

    public boolean addUser(User user) {
        this.assignedUsers.add(user.getUserUUID());
        LocalDate now = LocalDate.now();
        String date = formatter.format(now);
        History addUserHistory = new History(user.getUserUUID(), date,
                user.getUsername() + " was added to " + getTaskName());
        taskHistory.put(date, addUserHistory);
        return true;
    }

    public boolean removeUser(User user) {
        if (user == null)
            return false;
        this.assignedUsers.remove(user.getUserUUID());
        LocalDate now = LocalDate.now();
        String date = formatter.format(now);
        History addUserHistory = new History(user.getUserUUID(), date,
                user.getUsername() + " was removed from " + getTaskName());
        taskHistory.put(date, addUserHistory);
        return true;
    }

    public boolean facadePrintComments() {
        return printComments();
    }

    public boolean printComments() {
        if (taskComments.isEmpty() || taskComments == null)
            return false;
        for (Comment comment : taskComments)
            System.out.println(comment);
        return true;
    }
}
