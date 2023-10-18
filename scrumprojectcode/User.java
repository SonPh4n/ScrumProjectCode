package scrumprojectcode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Changes
 * 
 * @author SonPh4n
 * @author kuriakm
 */
public class User {

    public String username;
    private String password;
    public String firstName;
    public String lastName;
    private String email;
    private String phoneNumber;
    public boolean loggedIn;
    private String type;

    private UUID currentlyLoggedIn; //holds the UUID of the user that is currently logged in

    private UUID currentlyLoggedIn; //holds the UUID of the user that is currently logged in

    // ****************************************************************************************************//
    // personalTasks and listOfProjects are the UUIDs of the objects
    // private ArrayList<String> personalTasks;
    // private ArrayList<String> listOfProjects;
    // we can write a method to convert them each to arrayLists of ojbects
    private ArrayList<Task> myTasks;
    private ArrayList<Project> myProjects;
    // ****************************************************************************************************//

    private String userID;
    private UUID userUUID;

    public User(String userID, String firstName, String lastName, String username, String email, String phoneNumber,
            ArrayList<String> tasks, ArrayList<String> projects, String type) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.myTasks = UUIDtoTasks(tasks); // arraylist of Task objects
        this.myProjects = UUIDtoProjects(projects); // arraylist of Project objects
        this.loggedIn = false;
        this.type = type;
    }

    public User(UUID userID, String firstName, String lastName, String username, String email, String phoneNumber,
            ArrayList<Task> tasks, ArrayList<Project> projects, String type) {
        this.userUUID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.myTasks = tasks; // arraylist of Task objects
        this.myProjects = projects; // arraylist of Project objects
        this.loggedIn = false;
        this.type = type;
    }

    //Setters and Getters 
    public String getUsername(String username) {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(String password) {
        return password;
    }

    public String getFirstName(String firstName) {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String LastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(String email) {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(String phoneNumber) {
        return phoneNumber;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public ArrayList<Task> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(ArrayList<Task> myTasks) {
        this.myTasks = myTasks;
    }

    public ArrayList<Project> getMyProjects() {
        return myProjects;
    }

    public void setMyProjects(ArrayList<Project> myProjects) {
        this.myProjects = myProjects;
    }

    public String getUserType() {
        return type;
    }

    public void setUserType(String type) {
        this.type = type;
    }

    public String getUserID() {
        return userID;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }
    //end of setters and getters


    private UUID generateUUID() {
        // check if it has an ID, if from dataloader must convert using toUUID
        return UUID.randomUUID();
    }

    public String toString() {
        return "something";
    }

    //checks if the username and password match to login
    private boolean login(String username, String passsword) {
        if(this.username.equals(username) && this.password.equals(password))
        {
            loggedIn = true;
            return true;
        }
        return false;
    }

    //logs out the user
    public void logout()
    {
        loggedIn = false;
    }


    //registers a new user by checking if their UUID already exists, if not, create a new UUID and set their info
    private boolean register(String username, String password, String firstName, String lastName, String email, String phoneNumber, String type) {
        if(this.userUUID == null)
        {
            this.userUUID = generateUUID();
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.type = type;
            return true;
        }
        return false;
    }

    private boolean addProject(String projectName) {
        if(listOfProjects == null)
        {
            listOfProjects = new ArrayList<>();
        }
        listOfProjects.add(projectName);
    }

    private Project findProject(String projectName) {
        for(Project project : myProjects)
        {
            if(project.getProjectName().equals(projectName)){
                return project;
            }
        }
        return null;
    }

    private ArrayList<Task> UUIDtoTasks(ArrayList<String> personalTasks) {
        // TODO populate myTasks with Task objects based on the UUIDs from personalTasks
        ArrayList<Task> tasks = new ArrayList<>();
        for(String taskUUID : personalTasks)
        {
            Task task = findTaskByUUID(taskUUID);
            if(task != null){
                tasks.add(task);
            }
        }
        return tasks;
    }

    private Task findTaskByUUID(String taskUUID){
        for(Task task : myTasks)
        {
            if(task.getTaskUUID().toString().equals(taskUUID)){
                return task;
            }
        }
        return null;
    }

    private ArrayList<Project> UUIDtoProjects(ArrayList<String> listOfProjects) {
        // TODO populate myProjects with Project objects based on the UUIDs from
        // listOfProjects
        ArrayList<Project> projects = new ArrayList<>();
        for(String projectUUID : listOfProjects)
        {
            Project project = findProjectByUUID(projectUUID);
            if(project != null){
                projects.add(project);
            }
        }
        return null;
    }

    private Project findProjectByUUID(String projectUUID){
        for(Project project : myProjects)
        {
            if(project.getProjectUUID().toString().equals(projectUUID)){
                return project;
            }
        }
        return null;
    }

    private UUID toUUID(String userID) {
        // TODO convert string UUID from dataLoader to object UUID
        return UUID.fromString(userID);
    }
}
