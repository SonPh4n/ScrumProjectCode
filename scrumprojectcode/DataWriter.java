package scrumprojectcode;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * DataWriter: Writes data from the software to JSON files
 * 
 * @AidanH002
 * @kuriakm
 */

public class DataWriter extends DataConstants {
    // Attributes
    private boolean saveTasks;
    private boolean saveUsers;
    private boolean saveProjects;

    // Constructor
    public DataWriter(boolean saveTasks, boolean saveUsers, boolean saveProjects) {
        this.saveTasks = saveTasks;
        this.saveUsers = saveUsers;
        this.saveProjects = saveProjects;
    }

    // Getters and Setters

    /**
     * Check if saving tasks is enabled.
     *
     * @return True if saving tasks is enabled, false otherwise.
     */
    public boolean isSaveTasks() {
        return saveTasks;
    }

    /**
     * Set whether to enable saving tasks.
     *
     * @param saveTasks True to enable saving tasks, false to disable.
     */
    public void setSaveTasks(boolean saveTasks) {
        this.saveTasks = saveTasks;
    }

    /**
     * Void method that saves the current values in TaskList() and stores it in
     * task.json
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        JSONArray jsonTasks = new JSONArray();

        /*
         * for (int i = 0; i < tasks.size(); i++) {
         * jsonTasks.add(getTaskJSON(tasks.get(i)));
         * }
         */

        for (Task task : tasks) { 
            jsonTasks.add(getTaskJSON(task));
        }

        try (FileWriter file = new FileWriter(TASK_FILE_NAME)) {
            file.write(jsonTasks.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a Task object to a JSON object
     * task Comments are added to task object as an array using
     * getCommentJSON(Comment comment);
     * 
     * @param task
     * @return
     */
    public static JSONObject getTaskJSON(Task task) {
        JSONObject taskDetails = new JSONObject();
        taskDetails.put(TASK_PROJECT_ID, task.getProjectUUID().toString());
        taskDetails.put(TASK_COLUMN_ID, task.getColumnUUID().toString());
        taskDetails.put(TASK_ID, task.getTaskUUID().toString());
        taskDetails.put(TASK_TITLE, task.getTaskName());
        taskDetails.put(TASK_DESCRIPTION, task.getTaskDescription());
        taskDetails.put(TASK_TYPE, task.getTaskType());
        taskDetails.put(TASK_CREATION_DATE, task.getCreationDate());
        taskDetails.put(TASK_DUE_DATE, task.getDueDate());

        JSONArray arrayComments = new JSONArray();
        JSONArray arrayHistory = new JSONArray();
        JSONArray arrayUser = new JSONArray();

        if (arrayComments != null) {
            for (Comment comment : task.getTaskComments())
                arrayComments.add(getCommentJSON(comment));
        }

        for (HashMap.Entry<String, History> entry : task.getTaskHistory().entrySet())
            arrayHistory.add(getHistoryJSON(entry.getValue()));

        for (UUID user : task.getAssignedUsersUUID()) {
            JSONObject userIDs = new JSONObject();
            userIDs.put(TASK_USER_ID, user.toString());
            arrayUser.add(userIDs);
        }
        taskDetails.put(TASK_USERS, arrayUser);
        taskDetails.put(TASK_COMMENT_TITLE, arrayComments);
        taskDetails.put(TASK_HISTORY, arrayHistory);

        return taskDetails;
    }

    /**
     * Converts a Comment object to JSON object
     * 
     * @param comment
     * @return a JSON object representation of a comment
     */
    public static JSONObject getCommentJSON(Comment comment) {
        JSONObject commentDetails = new JSONObject();
        if (commentDetails != null) {
            // System.out.println(comment.getUserUUID().toString());
            commentDetails.put(TASK_COMMENT_ID, comment.getCommentUUID().toString());
            commentDetails.put(TASK_COMMENTOR, comment.getUserUUID().toString());
            commentDetails.put(TASK_COMMENT, comment.getComment().toString());

            JSONArray arrayMoreComments = new JSONArray();
            for (Comment moreComment : comment.getMoreComments()) {
                arrayMoreComments.add(getCommentJSON(moreComment));
            }
            commentDetails.put(TASK_MORE_COMMENTS, arrayMoreComments);
        }

        return commentDetails;
    }

    public static JSONObject getHistoryJSON(History history) {
        JSONObject historyDetails = new JSONObject();
        historyDetails.put(HISTORY_ID, history.getHistoryUUID().toString());
        historyDetails.put(HISTORY_USER, history.getUserUUID().toString());
        historyDetails.put(HISTORY_DETAILS, history.getDetails());
        historyDetails.put(HISTORY_RECORDED_DATE, history.getDate());

        return historyDetails;
    }

    /**
     * Check if saving users is enabled.
     *
     * @return True if saving users is enabled, false otherwise.
     */
    public boolean isSaveUsers() {
        return saveUsers;
    }

    /**
     * Set whether to enable saving users.
     *
     * @param saveUsers True to enable saving users, false to disable.
     */
    public void setSaveUsers(boolean saveUsers) {
        this.saveUsers = saveUsers;
    }

    /**
     * Saves Users from ArrayList<User> to user.json
     * TODO: Figure out how to save Users in user.json without appending preexisting
     * TODO: values
     * 
     * @param users
     */
    public static void saveUsers(ArrayList<User> users) {
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < users.size(); i++) {
            jsonUsers.add(getUserJSON(users.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSONObject method that takes in a User and adds its attributes to JSONObject
     * 
     * @param user User to be converted into a JSONObject
     * @return JSONObject to be added to JSONArray
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUserUUID().toString());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        userDetails.put(USER_TYPE, user.getUserType());

        JSONArray arrayProjects = new JSONArray();
        JSONArray arrayTasks = new JSONArray();

        /*
         * for (UUID project : user.getMyProjects()) {
         * JSONObject projectIDs = new JSONObject();
         * projectIDs.put(USER_PROJECT_ID, project.toString());
         * arrayProjects.add(projectIDs);
         * }
         * for (UUID task : user.getMyTasks()) {
         * JSONObject taskIDs = new JSONObject();
         * taskIDs.put(USER_TASK_ID, task.toString());
         * arrayTasks.add(taskIDs);
         * }
         * userDetails.put(USER_PROJECTS, arrayProjects);
         * userDetails.put(USER_TASKS, arrayTasks);
         */

        return userDetails;
    }

    /**
     * Check if saving projects is enabled.
     *
     * @return True if saving projects is enabled, false otherwise.
     */
    public boolean isSaveProjects() {
        return saveProjects;
    }

    /**
     * Set whether to enable saving projects.
     *
     * @param saveProjects True to enable saving projects, false to disable.
     */
    public void setSaveProjects(boolean saveProjects) {
        this.saveProjects = saveProjects;
    }

    /**
     * Void method that saves the current values in ProjectList() and stores it in
     * project.json
     */
    public static void saveProjects(ArrayList<Project> projects) {
        JSONArray jsonProjects = new JSONArray();

        for (int i = 0; i < projects.size(); i++) {
            jsonProjects.add(getProjectJSON(projects.get(i)));
        }

        try (FileWriter file = new FileWriter(PROJECT_FILE_NAME)) {
            file.write(jsonProjects.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSONObject method that takes in a Project and adds its attributes to
     * JSONObject
     * 
     * @param project Project to be converted into a JSONObject
     * @return JSONObject to be added to JSONArray
     */
    public static JSONObject getProjectJSON(Project project) {
        JSONObject projectDetails = new JSONObject();
        projectDetails.put(PROJECT_ID, project.getProjectUUID().toString());
        projectDetails.put(PROJECT_TITLE, project.getProjectName());

        JSONArray arrayUsers = new JSONArray();
        JSONArray arrayProjects = new JSONArray();

        for (UUID projectUser : project.getAssignedUsersUUID()) {
            if (projectUser != null) {
                JSONObject user = new JSONObject();
                user.put(PROJECT_USERS_ID, projectUser.toString());
                arrayUsers.add(user);
            }
        }

        JSONArray projectColumns = new JSONArray();
        for (Column column : project.getListOfColumns()) {
            JSONObject projectColumn = new JSONObject();
            projectColumn.put(COLUMN_ID, column.getColumnUUID().toString());
            projectColumn.put(COLUMN_TITLE, column.getColumnName());

            JSONArray columnTasks = new JSONArray();
            for (UUID columnTaskID : column.getColumnTasks()) {
                JSONObject columnTask = new JSONObject();
                columnTask.put(COLUMN_TASK_ID, columnTaskID.toString());
                columnTasks.add(columnTask);
            }
            projectColumn.put(COLUMN_TASKS, columnTasks);
            projectColumns.add(projectColumn);
        }

        projectDetails.put(PROJECT_USERS, arrayUsers);
        projectDetails.put(PROJECT_COLUMNS, projectColumns);
        // arrayProjects.add(projectDetails);

        return projectDetails;
    }
}
