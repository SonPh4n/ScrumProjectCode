package scrumprojectcode;

import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants{
    private ArrayList<Task> tasks;
    private ArrayList<User> users;
    private ArrayList<Project> projects;
    private ArrayList<Column> columns;
    private ArrayList<History> histories;

    public DataLoader()
    {
        this.tasks = new ArrayList<>();
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.histories = new ArrayList<>();
    }

    /**
     * loads tasks from json files
     * takes task info from json and creates task objects
     * @return returns an arraylist of task objects
     */
    public ArrayList<Task> loadTasks()
    {
        try {
            FileReader reader = new FileReader(TASK_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray tasksJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < tasksJSON.size(); i++) {
                JSONObject taskJSON = (JSONObject)tasksJSON.get(i);
                String projectID = (String)taskJSON.get(TASK_PROJECT_ID);
                String columnID = (String)taskJSON.get(TASK_PROJECT_ID);
                String taskID = (String)taskJSON.get(TASK_ID);
                String taskTitle = (String)taskJSON.get(TASK_TITLE);
                String taskDescription = (String)taskJSON.get(TASK_DESCRIPTION);
                String taskUsers = (String)taskJSON.get(TASK_USERS);
                String taskHistory = (String)taskJSON.get(TASK_HISTORY);
                String taskComments = (String)taskJSON.get(TASK_COMMENTS);
                String taskCreationDate = (String)taskJSON.get(TASK_CREATION_DATE);
                String taskDueDate = (String)taskJSON.get(TASK_DUE_DATE);

                tasks.add(new Task(taskTitle, taskDescription, taskDueDate));
            }

            return tasks;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  
    }

    /**
     * loads users from json files
     * takes user info from json and creates user objects
     * @return returns an arraylist of user objects
     */
    public ArrayList<User> loadUsers()
    {
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                String userID = (String)userJSON.get(USER_ID);
                String firstName = (String)userJSON.get(USER_FIRST_NAME);
                String lastName = (String)userJSON.get(USER_LAST_NAME);
                String userName = (String)userJSON.get(USER_USERNAME);
                String password = (String)userJSON.get(USER_PASSWORD);
                String email = (String)userJSON.get(USER_EMAIL);
                String phoneNumber = (String)userJSON.get(USER_PHONE_NUMBER);
                String type = (String)userJSON.get(USER_TYPE);

                //Parse "my-tasks" and "my-projects" as JSON arrays
                JSONArray myTasksJSON = (JSONArray) userJSON.get(USER_TASKS);
                JSONArray myProjectsJSON = (JSONArray) userJSON.get(USER_PROJECTS);

                //Convert JSON arrays to JAVA arrayLists
                ArrayList<String> myTasks = new ArrayList<String>();
                ArrayList<String> myProjects = new ArrayList<String>();

                for(int j=0; j < myTasksJSON.size(); j++){
                    myTasks.add((String) myTasksJSON.get(j));
                }

                for(int j=0; j < myProjectsJSON.size(); j++) {
                    myProjects.add((String) myProjectsJSON.get(j));
                }

                users.add(new User(userID, firstName, lastName, userName, email, phoneNumber, myTasks, myProjects));       
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
     * @return returns an arraylist of project objects
     */
    public ArrayList<Project> loadProjects()
    {
        try {
            FileReader reader = new FileReader(PROJECT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray projectsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < projectsJSON.size(); i++) {
                JSONObject projectJSON = (JSONObject)projectsJSON.get(i);
                String projectID = (String)projectJSON.get(PROJECT_ID);
                String projectTitle = (String)projectJSON.get(PROJECT_TITLE);
                //String assignedUsers = (String)projectJSON.get(TASK_USERS);
                //String projectColumns = (String)projectJSON.get(TASK_USERS);

                //Parse "assigned-users" and "project-columns" as JSON arrays
                JSONArray assignedUsersJSON = (JSONArray) projectJSON.get(PROJECT_USERS);
                JSONArray projectColumnsJSON = (JSONArray) projectJSON.get(PROJECT_COLUMNS);

                //Convert JSON arrays to JAVA arrayLists
                ArrayList<String> assignedUsers = new ArrayList<String>();
                ArrayList<String> projectColumns = new ArrayList<String>();

                for(int j=0; j < assignedUsersJSON.size(); j++){
                    assignedUsers.add((String) assignedUsersJSON.get(j));
                }

                for(int j=0; j < projectColumnsJSON.size(); j++) {
                    projectColumns.add((String) projectColumnsJSON.get(j));
                }


                projects.add(new Project(projectTitle));       //TODO change Project constructor//
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
     * @return returns an arraylist of column objects
     */
    public ArrayList<Column> loadColumns()
    {
        try {
            FileReader reader = new FileReader(COLUMN_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray columnsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < columnsJSON.size(); i++) {
                JSONObject columnJSON = (JSONObject)columnsJSON.get(i);
                String projectID = (String)columnJSON.get(COLUMN_PROJECT_ID);
                String columnID = (String)columnJSON.get(COLUMN_ID);
                String columnTitle = (String)columnJSON.get(COLUMN_TITLE);
                
                //Parse "column-tasks" as JSON arrays
                JSONArray columnTasksJSON = (JSONArray) columnJSON.get(COLUMN_TASKS);

                //Convert JSON array to JAVA arrayList
                ArrayList<String> columnTasks = new ArrayList<String>();

                for(int j=0; j < columnTasksJSON.size(); j++){
                    columnTasks.add((String) columnTasksJSON.get(j));
                }

                columns.add(new Column(columnID, columnTitle, columnTasks));       //TODO change Column constructor//
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
     * @return returns an arraylist of history objects
     */
    public ArrayList<History> loadHistory()
    {
        try {
            FileReader reader = new FileReader(TASK_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray historiesJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < historiesJSON.size(); i++) {
                JSONObject historyJSON = (JSONObject)historiesJSON.get(i);
                String historyID = (String)historyJSON.get(HISTORY_ID);
                String historyUser = (String)historyJSON.get(HISTORY_USER);
                String historyDetails = (String)historyJSON.get(HISTORY_DETAILS);
                String recordedDate = (String)historyJSON.get(HISTORY_RECORDED_DATE);
                

                histories.add(new History(historyUser, historyDetails, recordedDate));    //TODO change History constructor//
            }

            return histories;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  
    }
    
}
