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
                ArrayList<UUID> historiesUUID = new ArrayList<>(); // Used to convert UUIDs in task.json to History
                HashMap<String, History> history = new HashMap<>();

                JSONArray usersJSON = (JSONArray) taskJSON.get(TASK_USERS);
                JSONArray commentsJSON = (JSONArray) taskJSON.get(TASK_COMMENT);
                JSONArray moreCommentsJSON = (JSONArray) taskJSON.get(TASK_MORE_COMMENTS);
                JSONArray historiesJSON = (JSONArray) taskJSON.get(TASK_HISTORY);
                Iterator iu = usersJSON.iterator();
                Iterator ih = historiesJSON.iterator();
                Iterator ic = commentsJSON.iterator();
                Iterator iMC = moreCommentsJSON.iterator();

                while (iu.hasNext()) {
                    JSONObject userJSON = (JSONObject) iu.next();
                    UUID userUUID = UUID.fromString((String) userJSON.get(TASK_USER_ID));
                    usersUUID.add(userUUID);
                }

                while (ih.hasNext()) {
                    JSONObject historyJSON = (JSONObject) ih.next();
                    UUID historyUUID = UUID.fromString((String) historyJSON.get(TASK_HISTORY_ID));
                    historiesUUID.add(historyUUID);
                }

                history = getTaskHistory(historiesUUID);

                while (ic.hasNext()) {
                    ArrayList<Comment> moreComments = new ArrayList<Comment>();
                    JSONObject commentJSON = (JSONObject) ic.next();
                    UUID commentor = UUID.fromString((String) commentJSON.get(TASK_COMMENTOR));
                    String comment = (String) commentJSON.get(TASK_COMMENT);
                    UUID commentUUID = UUID.fromString((String) commentJSON.get(TASK_COMMENT_ID));
                    while (iMC.hasNext()) {
                        JSONObject moreCommentJSON = (JSONObject) iMC.next();
                        UUID moreCommentor = UUID.fromString((String) moreCommentJSON.get(TASK_COMMENTOR));
                        String moreComment = (String) moreCommentJSON.get(TASK_COMMENT);
                        UUID moreCommentUUID = UUID.fromString((String) moreCommentJSON.get(TASK_COMMENT_ID));
                        Comment aMC = new Comment(moreCommentUUID, moreCommentor, moreComment);
                        moreComments.add(aMC);
                    }
                    Comment aC = new Comment(commentUUID, commentor, comment, moreComments);
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

    // TODO: Handle getTaskHistory()/loadHistory() methods by either using
    // TODO: ArrayList<UUID> for taskHistory or loading HashMap<String, History>
    // TODO: from history.json in loadTasks()
    private static HashMap<String, History> getTaskHistory(ArrayList<UUID> historyUUID) {
        HashMap<String, History> history = new HashMap<>();
        try {
            FileReader reader = new FileReader(HISTORY_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray historiesJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < historiesJSON.size(); i++) {
                JSONObject historyJSON = (JSONObject) historiesJSON.get(i);
                UUID historyID = UUID.fromString((String) historyJSON.get(HISTORY_ID));
                UUID userID = UUID.fromString((String) historyJSON.get(HISTORY_USER));
                String historyDetails = (String) historyJSON.get(HISTORY_DETAILS);
                String recordedDate = (String) historyJSON.get(HISTORY_RECORDED_DATE);

                if (historyID.equals(historyUUID.get(i))) {
                    History aH = new History(historyID, userID, recordedDate, historyDetails);
                    history.put(recordedDate, aH);
                }
            }
            return history;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                String projectID = (String) projectJSON.get(PROJECT_ID);
                String projectTitle = (String) projectJSON.get(PROJECT_TITLE);
                // String assignedUsers = (String)projectJSON.get(TASK_USERS);
                // String projectColumns = (String)projectJSON.get(TASK_USERS);

                // Parse "assigned-users" and "project-columns" as JSON arrays
                JSONArray assignedUsersJSON = (JSONArray) projectJSON.get(PROJECT_USERS);
                JSONArray projectColumnsJSON = (JSONArray) projectJSON.get(PROJECT_COLUMNS);

                // Convert JSON arrays to JAVA arrayLists
                ArrayList<String> assignedUsers = new ArrayList<String>();
                ArrayList<String> projectColumns = new ArrayList<String>();

                for (int j = 0; j < assignedUsersJSON.size(); j++) {
                    assignedUsers.add((String) assignedUsersJSON.get(j));
                }

                for (int j = 0; j < projectColumnsJSON.size(); j++) {
                    projectColumns.add((String) projectColumnsJSON.get(j));
                }

                projects.add(new Project(projectTitle)); // TODO change Project constructor//
            }

            return projects;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * loads columns from json files
     * takes column info from json and creates column objects
     * 
     * @return returns an arraylist of column objects
     */
    public ArrayList<Column> loadColumns() {
        try {
            FileReader reader = new FileReader(COLUMN_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray columnsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < columnsJSON.size(); i++) {
                JSONObject columnJSON = (JSONObject) columnsJSON.get(i);
                String projectID = (String) columnJSON.get(COLUMN_PROJECT_ID);
                String columnID = (String) columnJSON.get(COLUMN_ID);
                String columnTitle = (String) columnJSON.get(COLUMN_TITLE);

                // Parse "column-tasks" as JSON arrays
                JSONArray columnTasksJSON = (JSONArray) columnJSON.get(COLUMN_TASKS);

                // Convert JSON array to JAVA arrayList
                ArrayList<String> columnTasks = new ArrayList<String>();

                for (int j = 0; j < columnTasksJSON.size(); j++) {
                    columnTasks.add((String) columnTasksJSON.get(j));
                }

                columns.add(new Column(columnID, columnTitle, columnTasks)); // TODO change Column constructor//
            }

            return columns;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * loads history from json files
     * takes history info from json and creates history objects
     * 
     * @return returns an arraylist of history objects
     */
    public ArrayList<History> loadHistory() {
        try {
            FileReader reader = new FileReader(TASK_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray historiesJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < historiesJSON.size(); i++) {
                JSONObject historyJSON = (JSONObject) historiesJSON.get(i);
                String historyID = (String) historyJSON.get(HISTORY_ID);
                String historyUser = (String) historyJSON.get(HISTORY_USER);
                String historyDetails = (String) historyJSON.get(HISTORY_DETAILS);
                String recordedDate = (String) historyJSON.get(HISTORY_RECORDED_DATE);

                histories.add(new History(historyUser, historyDetails, recordedDate)); // TODO change History
                                                                                       // constructor//
            }

            return histories;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
