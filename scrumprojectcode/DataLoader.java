package scrumprojectcode;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * loads all data from json files
 * 
 * @author jedalto
 * @author kuriakm
 */
public class DataLoader extends DataConstants {
    private static ArrayList<User> users;
    private static ArrayList<Task> tasks;
    private static ArrayList<Project> projects;

    /**
     * loads tasks from json files
     * takes task info from json and creates task objects
     * 
     * @return returns an arraylist of task objects
     */
    public static ArrayList<Task> loadTasks() { // TODO: Read comments recursively and fix getTaskHistory to properly
                                                // TODO: populate HashMap<String, History>
        tasks = new ArrayList<Task>();
        users = loadUsers();

        try {
            FileReader reader = new FileReader(TASK_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray tasksJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < tasksJSON.size(); i++) {
                JSONObject taskJSON = (JSONObject) tasksJSON.get(i);
                if (taskJSON != null) {
                    UUID projectID = UUID.fromString((String) taskJSON.get(TASK_PROJECT_ID));
                    UUID columnID = UUID.fromString((String) taskJSON.get(TASK_COLUMN_ID));
                    UUID taskID = UUID.fromString((String) taskJSON.get(TASK_ID));
                    String taskTitle = (String) taskJSON.get(TASK_TITLE);
                    String taskDesc = (String) taskJSON.get(TASK_DESCRIPTION);
                    String taskType = (String) taskJSON.get(TASK_TYPE);
                    String taskDueDate = (String) taskJSON.get(TASK_DUE_DATE);
                    String taskCreationDate = (String) taskJSON.get(TASK_CREATION_DATE);

                    ArrayList<UUID> usersUUID = new ArrayList<UUID>();
                    ArrayList<Comment> comments = new ArrayList<Comment>();
                    HashMap<String, History> history = new HashMap<>();

                    JSONArray usersJSON = (JSONArray) taskJSON.get(TASK_USERS);
                    JSONArray commentsJSON = (JSONArray) taskJSON.get(TASK_COMMENT_TITLE);
                    JSONArray historiesJSON = (JSONArray) taskJSON.get(TASK_HISTORY);

                    Iterator iu = usersJSON.iterator();
                    Iterator ih = historiesJSON.iterator();
                    Iterator ic = commentsJSON.iterator();

                    while (iu.hasNext()) {
                        JSONObject userJSON = (JSONObject) iu.next();
                        UUID userUUID = UUID.fromString((String) userJSON.get(TASK_USER_ID));
                        usersUUID.add(userUUID);
                    }

                    ArrayList<User> assignedUsers = new ArrayList<>();

                    for (int j = 0; j < usersUUID.size(); j++) {
                        if (users.get(0).getUserUUID().equals(usersUUID.get(j))) {
                            assignedUsers.add(users.get(0));
                            users.remove(0);
                            usersUUID.remove(j);
                            j = 0;
                        }
                    }

                    while (ih.hasNext()) {
                        JSONObject historyJSON = (JSONObject) ih.next();
                        UUID historyUUID = UUID.fromString((String) historyJSON.get(TASK_HISTORY_ID));
                        UUID historyUserUUID = UUID.fromString((String) historyJSON.get(HISTORY_USER));
                        String historyDetails = (String) historyJSON.get(HISTORY_DETAILS);
                        String date = (String) historyJSON.get(HISTORY_RECORDED_DATE);

                        int index = 0;
                        boolean findUser = false;
                        User historyUser = null;

                        for (int k = 0; k < assignedUsers.size(); i++) {
                            if (assignedUsers.get(k).getUserUUID().equals(historyUserUUID))
                                historyUser = assignedUsers.get(k);
                        }

                        history.put(date, new History(historyUUID, historyUser, date, historyDetails));
                    }

                    while (ic.hasNext()) {
                        JSONObject commentJSON = (JSONObject) ic.next();
                        Comment aC = (getCommentJSON(commentJSON) == null ? null : getCommentJSON(commentJSON));
                        if (aC != null)
                            comments.add(aC);
                    }

                    Task aT = new Task(projectID, columnID, taskID, taskTitle, taskDesc, taskType, users, history,
                            comments, taskDueDate, taskCreationDate);
                    tasks.add(aT);
                }
            }
            return tasks;
        } catch (

        Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Comment getCommentJSON(JSONObject commentJSON) {
        users = loadUsers();

        UUID commentorUUID = UUID.fromString((String) commentJSON.get(TASK_COMMENTOR));
        String comment = (String) commentJSON.get(TASK_COMMENT);
        UUID commentUUID = UUID.fromString((String) commentJSON.get(TASK_COMMENT_ID));

        JSONArray moreCommentsJSONArray = (JSONArray) commentJSON.get(TASK_MORE_COMMENTS);
        ArrayList<Comment> moreComments = new ArrayList<>();
        Iterator iMC = moreCommentsJSONArray.iterator();
        if (commentJSON.containsKey(TASK_MORE_COMMENTS) && commentJSON.get(TASK_MORE_COMMENTS) instanceof JSONArray) {
            moreCommentsJSONArray = (JSONArray) commentJSON.get(TASK_MORE_COMMENTS);
        } else {
            while (iMC.hasNext()) {
                JSONObject moreCommentsJSON = (JSONObject) iMC.next();
                Comment jsonToMoreComments = getCommentJSON(moreCommentsJSON);
                moreComments.add(jsonToMoreComments);
            }
        }

        int index = 0;
        boolean findUser = false;
        User commentor = null;

        for (int k = 0; k < users.size(); k++) {
            if (users.get(k).getUserUUID().equals(commentorUUID))
                commentor = users.get(k);
        }

        return new Comment(commentUUID, commentor, comment, moreComments);

    }

    /**
     * loads users from json files
     * takes user info from json and creates user objects
     * 
     * @return returns an arraylist of user objects
     */
    public static ArrayList<User> loadUsers() {
        users = new ArrayList<User>();
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);
                if (userJSON != null) {
                    String phoneNumber = (String) userJSON.get(USER_PHONE_NUMBER);
                    String firstName = (String) userJSON.get(USER_FIRST_NAME);
                    String lastName = (String) userJSON.get(USER_LAST_NAME);
                    String password = (String) userJSON.get(USER_PASSWORD);
                    String userName = (String) userJSON.get(USER_USERNAME);
                    UUID userID = UUID.fromString((String) userJSON.get(USER_ID));
                    String type = (String) userJSON.get(USER_TYPE);
                    String email = (String) userJSON.get(USER_EMAIL);
                    User aU = new User(userID, firstName, lastName, userName, password, email, phoneNumber, type);
                    users.add(aU);
                }
            }
            reader.close();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * loads projects from json files
     * takes project info from json and creates project objects
     * 
     * @return returns an arraylist of project objects
     */
    public static ArrayList<Project> loadProjects() {
        projects = new ArrayList<>();
        tasks = loadTasks();
        users = loadUsers();

        try {
            FileReader reader = new FileReader(PROJECT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray projectsJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < projectsJSON.size(); i++) {
                JSONObject projectJSON = ((JSONObject) projectsJSON.get(i));
                if (projectJSON != null) {
                    UUID projectID = UUID.fromString((String) projectJSON.get(PROJECT_ID));
                    String projectTitle = (String) projectJSON.get(PROJECT_TITLE);

                    // Parse assigned users
                    JSONArray assignedUsersJSON = (JSONArray) projectJSON.get(PROJECT_USERS);
                    ArrayList<UUID> assignedUsersUUID = new ArrayList<>();
                    for (Object userObj : assignedUsersJSON) {
                        JSONObject userJSON = (JSONObject) userObj;
                        if (userJSON != null) {
                            UUID userUUID = UUID.fromString((String) userJSON.get(PROJECT_USERS_ID));
                            assignedUsersUUID.add(userUUID);
                        }
                    }

                    ArrayList<User> assignedUsers = new ArrayList<>();

                    for (int j = 0; j < assignedUsersUUID.size(); j++) {
                        if (users.get(0).getUserUUID().equals(assignedUsersUUID.get(j))) {
                            assignedUsers.add(users.get(0));
                            users.remove(0);
                            assignedUsersUUID.remove(j);
                            j = 0;
                        }
                    }

                    // Parse project columns
                    JSONArray projectColumnsJSON = (JSONArray) projectJSON.get(PROJECT_COLUMNS);
                    ArrayList<Column> projectColumns = new ArrayList<>();

                    for (int j = 0; j < projectColumnsJSON.size(); j++) {
                        JSONObject columnJSON = (JSONObject) projectColumnsJSON.get(j);

                        UUID columnID = UUID.fromString((String) columnJSON.get("column-id"));
                        String columnTitle = (String) columnJSON.get("column-title");

                        JSONArray columnTasksJSON = (JSONArray) columnJSON.get("column-tasks");

                        ArrayList<UUID> columnTaskIDs = new ArrayList<>();
                        if (columnTasksJSON != null) {
                            for (int k = 0; k < columnTasksJSON.size(); k++) {
                                JSONObject columnTaskJSON = (JSONObject) columnTasksJSON.get(k);
                                UUID taskUUID = UUID.fromString((String) columnTaskJSON.get("column-task-id"));
                                columnTaskIDs.add(taskUUID);
                            }
                        }

                        ArrayList<Task> columnTasks = new ArrayList<>();

                        for (i = 0; i < columnTaskIDs.size(); i++) {
                            if (tasks.get(0).getTaskUUID().equals(columnTaskIDs.get(i))) {
                                columnTasks.add(tasks.get(0));
                                tasks.remove(0);
                                columnTaskIDs.remove(i);
                                i = 0;
                            }
                        }

                        Column column = new Column(projectID, columnID, columnTitle, columnTasks);
                        projectColumns.add(column);
                    }

                    Project project = new Project(projectID, projectTitle, projectColumns, assignedUsers);
                    projects.add(project);
                }
            }

            return projects;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
