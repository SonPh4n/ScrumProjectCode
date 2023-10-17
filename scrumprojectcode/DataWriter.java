package scrumprojectcode;

import ava.

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
        priate boolean saveProjects;

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

    public static void saveTasks() {
        TaskList taskList = TaskList.getInstance();
     

           

            jsonTasks.add(getTaskJSON(tasks.get(i)));

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonTasks.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getTaskJSON(Task task) {
        JSONObject taskDetails = new JSONObject();
        // TODO: Figure out how to get projectID, columnID from Task
        return taskDetails;
    }

    /**
     * Check if saving users is enabled.
     *
     * @return True if saving users is enabled, false otherwise.
     

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
}
