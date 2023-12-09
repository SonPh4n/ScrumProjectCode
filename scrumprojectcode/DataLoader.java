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
 * Loads data from project.json and user.json
 * 
 * @author jedalto
 * @author kuriakm
 */
public class DataLoader extends DataConstants {
    private static ArrayList<User> users;
    private static ArrayList<Project> projects;

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
        users = loadUsers();

        try {
            FileReader reader = new FileReader(PROJECT_FILE_NAME);
            JSONArray projectsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < projectsJSON.size(); i++) {
                JSONObject projectJSON = ((JSONObject) projectsJSON.get(i));
                if (projectJSON != null) {
                    UUID projectID = UUID.fromString((String) projectJSON.get(PROJECT_ID));
                    String projectTitle = (String) projectJSON.get(PROJECT_TITLE);

                    // Parse Users
                    JSONArray usersJSON = (JSONArray) projectJSON.get(PROJECT_USERS);
                    ArrayList<UUID> usersFromFile = new ArrayList<>();

                    Iterator iu = usersJSON.iterator();

                    while (iu.hasNext()) {
                        JSONObject userJSON = (JSONObject) iu.next();
                        String userID = (String) userJSON.get(PROJECT_USERS_ID);
                        UUID userUUID = UUID.fromString(userID);
                        usersFromFile.add(userUUID);
                    }

                    ArrayList<User> assignedUsers = new ArrayList<>();

                    for (User user : users)
                        for (UUID userUUID : usersFromFile)
                            if (user.getUserUUID().equals(userUUID))
                                assignedUsers.add(user);

                    // Parse Columns
                    JSONArray projectColumnsJSON = (JSONArray) projectJSON.get(PROJECT_COLUMNS);
                    ArrayList<Column> projectColumns = new ArrayList<>();

                    for (int j = 0; j < projectColumnsJSON.size(); j++) {
                        JSONObject columnJSON = (JSONObject) projectColumnsJSON.get(j);

                        UUID columnID = UUID.fromString((String) columnJSON.get("column-id"));
                        String columnTitle = (String) columnJSON.get("column-title");

                        JSONArray columnTasksJSON = (JSONArray) columnJSON.get("column-tasks");
                        Iterator iCT = columnTasksJSON.iterator();

                        ArrayList<Task> columnTasks = new ArrayList<>();

                        // Parse Tasks
                        while (iCT.hasNext()) {
                            JSONObject columnTaskJSON = (JSONObject) iCT.next();
                            Task aT = (getTaskJSON(columnTaskJSON, projectID, columnID) == null ? null
                                    : getTaskJSON(columnTaskJSON, projectID, columnID));
                            if (aT != null)
                                columnTasks.add(aT);
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

    /**
     * Converts a JSONObject into a Task
     * 
     * @param taskJSON
     * @param projectID
     * @param columnID
     * @return Task object to be added to a Column's columnTasks
     */
    public static Task getTaskJSON(JSONObject taskJSON, UUID projectID, UUID columnID) {
        users = loadUsers();

        UUID taskID = UUID.fromString((String) taskJSON.get(TASK_ID));
        String taskTitle = (String) taskJSON.get(TASK_TITLE);
        String taskDesc = (String) taskJSON.get(TASK_DESCRIPTION);
        String taskType = (String) taskJSON.get(TASK_TYPE);
        String taskDueDate = (String) taskJSON.get(TASK_DUE_DATE);
        String taskCreationDate = (String) taskJSON.get(TASK_CREATION_DATE);

        ArrayList<UUID> usersFromFile = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        HashMap<String, History> history = new HashMap<>();

        JSONArray usersJSON = (JSONArray) taskJSON.get(TASK_USERS);
        JSONArray commentsJSON = (JSONArray) taskJSON.get(TASK_COMMENT_TITLE);
        JSONArray historiesJSON = (JSONArray) taskJSON.get(HISTORY);

        Iterator iu = usersJSON.iterator();
        Iterator ih = historiesJSON.iterator();
        Iterator ic = commentsJSON.iterator();

        // Parse Users
        while (iu.hasNext()) {
            JSONObject userJSON = (JSONObject) iu.next();
            String userID = (String) userJSON.get(TASK_USER_ID);
            UUID userUUID = UUID.fromString(userID);
            usersFromFile.add(userUUID);
        }

        ArrayList<User> assignedUsers = new ArrayList<>();

        for (User user : users)
            for (UUID userUUID : usersFromFile)
                if (user.getUserUUID().equals(userUUID))
                    assignedUsers.add(user);

        // Parse History
        while (ih.hasNext()) {
            JSONObject historyJSON = (JSONObject) ih.next();
            UUID historyUUID = UUID.fromString((String) historyJSON.get(HISTORY_ID));
            String historyDetails = (String) historyJSON.get(HISTORY_DETAILS);
            String date = (String) historyJSON.get(HISTORY_RECORDED_DATE);

            history.put(date, new History(historyUUID, date, historyDetails));
        }

        // Parse Comments
        while (ic.hasNext()) {
            JSONObject commentJSON = (JSONObject) ic.next();
            Comment aC = (getCommentJSON(commentJSON) == null ? null : getCommentJSON(commentJSON));
            if (aC != null)
                comments.add(aC);
        }

        return new Task(projectID, columnID, taskID, taskTitle, taskDesc, taskType, assignedUsers, history,
                comments, taskDueDate, taskCreationDate);

    }

    /**
     * Converts a JSONObject into a Comment
     * 
     * @param commentJSON
     * @return Comment object to be added to a Task's taskComments
     */
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

        User commentor = null;

        for (User user : users)
            if (user.getUserUUID().equals(commentorUUID)) {
                commentor = user;
                break;
            }

        return new Comment(commentUUID, commentor, comment, moreComments);

    }
}
