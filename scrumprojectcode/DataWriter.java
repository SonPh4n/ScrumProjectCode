package scrumprojectcode;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * DataWriter: Writes data from the software to JSON files
 * 
 * @author AidanH002
 * @author kuriakm
 */

public class DataWriter extends DataConstants {

    /**
     * Saves Users from ArrayList<User> to user.json
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
     * 
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

        return userDetails;
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
     * 
     * @return JSONObject to be added to JSONArray
     */
    public static JSONObject getProjectJSON(Project project) {
        JSONObject projectDetails = new JSONObject();
        projectDetails.put(PROJECT_ID, project.getProjectUUID().toString());
        projectDetails.put(PROJECT_TITLE, project.getProjectName());

        JSONArray arrayUsers = new JSONArray();
        JSONArray projectColumns = new JSONArray();

        for (User projectUser : project.getAssignedUsers()) {
            if (projectUser != null) {
                JSONObject user = new JSONObject();
                user.put(PROJECT_USERS_ID, projectUser.getUserUUID().toString());
                arrayUsers.add(user);
            }
        }

        for (Column column : project.getListOfColumns()) {
            JSONObject projectColumn = new JSONObject();
            projectColumn.put(COLUMN_ID, column.getColumnUUID().toString());
            projectColumn.put(COLUMN_TITLE, column.getColumnName());

            JSONArray columnTasks = new JSONArray();
            for (Task columnTask : column.getColumnTasks()) {
                columnTasks.add(getTaskJSON(columnTask));
            }
            projectColumn.put(COLUMN_TASKS, columnTasks);
            projectColumns.add(projectColumn);
        }

        projectDetails.put(PROJECT_USERS, arrayUsers);
        projectDetails.put(PROJECT_COLUMNS, projectColumns);
        // arrayProjects.add(projectDetails);

        return projectDetails;
    }

    /**
     * Converts a Task object to a JSON object
     * task Comments are added to task object as an array using
     * getCommentJSON(Comment comment);
     * 
     * @param task
     * 
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

        for (Comment comment : task.getTaskComments())
            arrayComments.add(getCommentJSON(comment));

        for (HashMap.Entry<String, History> entry : task.getTaskHistory().entrySet())
            arrayHistory.add(getHistoryJSON(entry.getValue()));

        for (User user : task.getAssignedUsers()) {
            JSONObject userIDs = new JSONObject();
            userIDs.put(TASK_USER_ID, user.getUserUUID().toString());
            arrayUser.add(userIDs);
        }
        taskDetails.put(TASK_USERS, arrayUser);
        taskDetails.put(TASK_COMMENT_TITLE, arrayComments);
        taskDetails.put(HISTORY, arrayHistory);

        return taskDetails;
    }

    /**
     * Converts a Comment object to JSON object
     * 
     * @param comment
     * 
     * @return a JSON object representation of a comment
     */
    public static JSONObject getCommentJSON(Comment comment) {
        JSONObject commentDetails = new JSONObject();
        if (comment != null) {
            commentDetails.put(TASK_COMMENT_ID, comment.getCommentUUID().toString());
            commentDetails.put(TASK_COMMENTOR, comment.getUser().getUserUUID().toString());
            commentDetails.put(TASK_COMMENT, comment.getComment());

            JSONArray arrayMoreComments = new JSONArray();
            if (!comment.getMoreComments().isEmpty()) {
                for (Comment moreComment : comment.getMoreComments()) {
                    arrayMoreComments.add(getCommentJSON(moreComment));
                }
            }
            commentDetails.put(TASK_MORE_COMMENTS, arrayMoreComments);
        }
        return commentDetails;
    }

    /**
     * Converts a History object to JSON object
     * 
     * @param history
     * 
     * @return a JSON object representation of a task's history
     */
    public static JSONObject getHistoryJSON(History history) {
        JSONObject historyDetails = new JSONObject();
        historyDetails.put(HISTORY_ID, history.getHistoryUUID().toString());
        historyDetails.put(HISTORY_DETAILS, history.getDetails());
        historyDetails.put(HISTORY_RECORDED_DATE, history.getDate());

        return historyDetails;
    }
}
