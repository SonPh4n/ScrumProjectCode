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
 * @author jedalto
 * @author kuriakm
 */
public class DataLoader extends DataConstants {
    private static ArrayList<Task> tasks;
    private static ArrayList<User> users;
    private static ArrayList<Project> projects;
    private static ArrayList<Column> columns;
    private static ArrayList<History> histories;

    public DataLoader() {
        tasks = new ArrayList<>();
        users = new ArrayList<>();
        projects = new ArrayList<>();
        columns = new ArrayList<>();
        histories = new ArrayList<>();
    }

    /**
     * loads tasks from json files
     * takes task info from json and creates task objects
     * 
     * @return returns an arraylist of task objects
     */
    public static ArrayList<Task> loadTasks() { // TODO: Read comments recursively and fix getTaskHistory to properly
                                                // TODO: populate HashMap<String, History>
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            FileReader reader = new FileReader(TASK_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray tasksJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < tasksJSON.size(); i++) {
                JSONObject taskJSON = (JSONObject) tasksJSON.get(i);
                UUID projectID = UUID.fromString((String) taskJSON.get(TASK_PROJECT_ID));
                UUID columnID = UUID.fromString((String) taskJSON.get(TASK_COLUMN_ID));
                UUID taskID = UUID.fromString((String) taskJSON.get(TASK_ID));
                String taskTitle = (String) taskJSON.get(TASK_TITLE);
                String taskDesc = (String) taskJSON.get(TASK_DESCRIPTION);
                String taskDueDate = (String) taskJSON.get(TASK_DUE_DATE);
                String taskCreationDate = (String) taskJSON.get(TASK_CREATION_DATE);

                ArrayList<UUID> usersUUID = new ArrayList<UUID>();
                ArrayList<Comment> comments = new ArrayList<Comment>();
                HashMap<String, History> history = new HashMap<>();

                JSONArray usersJSON = (JSONArray) taskJSON.get(TASK_USERS);
                JSONArray commentsJSON = (JSONArray) taskJSON.get(TASK_COMMENT);
                JSONArray historiesJSON = (JSONArray) taskJSON.get(TASK_HISTORY);

                Iterator iu = usersJSON.iterator();
                Iterator ih = historiesJSON.iterator();
                Iterator ic = commentsJSON.iterator();

                while (iu.hasNext()) {
                    JSONObject userJSON = (JSONObject) iu.next();
                    UUID userUUID = UUID.fromString((String) userJSON.get(TASK_USER_ID));
                    usersUUID.add(userUUID);
                }

                while (ih.hasNext()) {
                    JSONObject historyJSON = (JSONObject) ih.next();
                    UUID historyUUID = UUID.fromString((String) historyJSON.get(TASK_HISTORY_ID));
                    UUID historyUser = UUID.fromString((String) historyJSON.get(HISTORY_USER));
                    String historyDetails = (String) historyJSON.get(HISTORY_DETAILS);
                    String date = (String) historyJSON.get(HISTORY_RECORDED_DATE);
                    history.put(date, new History(historyUUID, historyUser, date, historyDetails));
                }

                while (ic.hasNext()) {
                    JSONObject commentJSON = (JSONObject) ic.next();
                    Comment aC = getCommentJSON(commentJSON);
                    comments.add(aC);
                }

                Task aT = new Task(projectID, columnID, taskID, taskTitle, taskDesc, usersUUID, history, comments,
                        taskDueDate, taskCreationDate);
                tasks.add(aT);
            }
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Comment getCommentJSON(JSONObject commentJSON) {
        UUID commentor = UUID.fromString((String) commentJSON.get(TASK_COMMENTOR));
        String comment = (String) commentJSON.get(TASK_COMMENT);
        UUID commentUUID = UUID.fromString((String) commentJSON.get(TASK_COMMENT_ID));

        JSONArray moreCommentsJSONArray = (JSONArray) commentJSON.get(TASK_MORE_COMMENTS);
        ArrayList<Comment> moreComments = new ArrayList<>();
        Iterator iMC = moreCommentsJSONArray.iterator();
        while (iMC.hasNext()) {
            JSONObject moreCommentsJSON = (JSONObject) iMC.next();
            Comment jsonToMoreComments = getCommentJSON(moreCommentsJSON);
            moreComments.add(jsonToMoreComments);
        }
        return new Comment(commentor, commentUUID, comment, moreComments);
    }

    /**
     * loads users from json files
     * takes user info from json and creates user objects
     * 
     * @return returns an arraylist of user objects
     */
    public static ArrayList<User> loadUsers() { // Modified loadUsers() to convert Objects directly from user.json and
                                                // read UUID arrays for my-projects and my-tasks @kuriakm
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);
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
        try {
            FileReader reader = new FileReader(PROJECT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray projectsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < projectsJSON.size(); i++) {
                JSONObject projectJSON = (JSONObject) projectsJSON.get(i);
                UUID projectID = UUID.fromString((String) projectJSON.get(PROJECT_ID));
                String projectTitle = (String) projectJSON.get(PROJECT_TITLE);

                // Parse "assigned-users" and "project-columns" as JSON arrays
                JSONArray assignedUsersJSON = (JSONArray) projectJSON.get(PROJECT_USERS);
                JSONArray projectColumnsJSON = (JSONArray) projectJSON.get(PROJECT_COLUMNS);

                // Convert JSON arrays to JAVA arrayLists
                ArrayList<UUID> assignedUsers = new ArrayList<>();
                ArrayList<Column> projectColumns = new ArrayList<>();

                Iterator iU = assignedUsersJSON.iterator();
                Iterator iPC = projectColumnsJSON.iterator();

                while (iU.hasNext()) {
                    JSONObject userJSON = (JSONObject) iU.next();
                    UUID userUUID = UUID.fromString((String) userJSON.get(PROJECT_USERS_ID));
                    assignedUsers.add(userUUID);
                }

                while (iPC.hasNext()) {
                    JSONObject columnJSON = (JSONObject) iPC.next();
                    UUID columnUUID = UUID.fromString((String) columnJSON.get(COLUMN_ID));
                    String columnTitle = (String) columnJSON.get(COLUMN_TITLE);
                    JSONArray columnTasksJSON = (JSONArray) columnJSON.get(COLUMN_TASKS);
                    ArrayList<UUID> columnTasks = new ArrayList<>();
                    Iterator iCT = columnTasksJSON.iterator();
                    while (iCT.hasNext()) {
                        JSONObject columnTaskJSON = (JSONObject) iCT.next();
                        UUID taskUUID = UUID.fromString((String) columnTaskJSON.get(COLUMN_TASK_ID));
                        columnTasks.add(taskUUID);
                    }
                    projectColumns.add(new Column(projectID, columnUUID, columnTitle, columnTasks));
                }

                projects.add(new Project(projectID, projectTitle, projectColumns, assignedUsers));
            }

            return projects;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
