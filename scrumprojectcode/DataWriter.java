package scrumprojectcode;

import java.io.FileWriter;
import java.util.ArrayList;

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
    public static void saveTasks() {
        TaskList taskList = TaskList.getInstance();
        ArrayList<Task> tasks = taskList.getListOfTasks();
        JSONArray jsonTasks = new JSONArray();

        for (int i = 0; i < tasks.size(); i++)
            jsonTasks.add(getTaskJSON(tasks.get(i)));

        try (FileWriter file = new FileWriter(TASK_FILE_NAME)) {
            file.write(jsonTasks.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSONObject method that takes in a Task and adds its attributes to JSONObject
     * 
     * @param task Task to be converted into a JSONObject
     * @return JSONObject to be added to JSONArray
     */
    public static JSONObject getTaskJSON(Task task) {
        JSONObject taskDetails = new JSONObject();
        taskDetails.put(TASK_PROJECT_ID, task.getProjectUUID());
        taskDetails.put(TASK_COLUMN_ID, task.getColumnUUID());
        taskDetails.put(TASK_ID, task.getTaskUUID());
        taskDetails.put(TASK_TITLE, task.getTaskName());
        taskDetails.put(TASK_DESCRIPTION, task.getTaskDescription());
        taskDetails.put(TASK_USERS, task.getAssignedUsers());
        taskDetails.put(TASK_HISTORY, task.getTaskHistory());
        taskDetails.put(TASK_COMMENTS, task.getTaskComments());
        taskDetails.put(TASK_CREATION_DATE, task.getCreationDate());
        taskDetails.put(TASK_DUE_DATE, task.getDueDate());
        // TODO: Figure out how to get projectID, columnID from Task
        return taskDetails;
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
     * Void method that saves the current values in UserList() and stores it in
     * user.json
     */
    public static void saveUsers() {
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getListOfUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < users.size(); i++)
            jsonUsers.add(getUserJSON(users.get(i)));

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
     * @param task User to be converted into a JSONObject
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

        for (Project project : user.getMyProjects()) {
            JSONObject projectIDs = new JSONObject();
            projectIDs.put(USER_PROJECT_ID, project.getProjectUUID().toString());
            arrayProjects.add(projectIDs);
        }
        for (Task task : user.getMyTasks()) {
            JSONObject taskIDs = new JSONObject();
            taskIDs.put(USER_TASK_ID, task.getTaskUUID().toString());
            arrayTasks.add(taskIDs);
        }
        userDetails.put(USER_PROJECTS, arrayProjects);
        userDetails.put(USER_TASKS, arrayTasks);

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
    public static void saveProjects() {
        ProjectList projectList = ProjectList.getInstance();
        ArrayList<Project> projects = projectList.getListOfProjects();
        JSONArray jsonProjects = new JSONArray();

        for (int i = 0; i < projects.size(); i++)
            jsonProjects.add(getProjectJSON(projects.get(i)));

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
     * @param task Project to be converted into a JSONObject
     * @return JSONObject to be added to JSONArray
     */
    public static JSONObject getProjectJSON(Project project) {
        JSONObject projectDetails = new JSONObject();
        projectDetails.put(PROJECT_ID, project.getProjectUUID());
        projectDetails.put(PROJECT_TITLE, project.getProjectName());
        projectDetails.put(PROJECT_USERS, project.getAssignedUsers());
        projectDetails.put(PROJECT_COLUMNS, project.getListOfColumns());
        return projectDetails;
    }
}
