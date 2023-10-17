package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Changes
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

    //****************************************************************************************************//
    //personalTasks and listOfProjects are the UUIDs of the objects
    //private ArrayList<String> personalTasks;
    //private ArrayList<String> listOfProjects;
    //we can write a method to convert them each to arrayLists of ojbects
    private ArrayList<Task> myTasks;
    private ArrayList<Project> myProjects;
    //****************************************************************************************************//

    private String userID;
    private UUID userUUID;


    public User(String userID, String firstName, String lastName, String username, String email, String phoneNumber, ArrayList<String> tasks, ArrayList<String> projects, String type)
    {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber; 
        this.myTasks = UUIDtoTasks(tasks);  //arraylist of Task objects
        this.myProjects = UUIDtoProjects(projects);   //arraylist of Project objects
        this.loggedIn = false;
        this.type = type;
    }

    public User(UUID userID, String firstName, String lastName, String username, String email, String phoneNumber, ArrayList<Task> tasks, ArrayList<Project> projects, String type)
    {
        this.userUUID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber; 
        this.myTasks = tasks;  //arraylist of Task objects
        this.myProjects = projects;   //arraylist of Project objects
        this.loggedIn = false;
        this.type = type;
    }

    

     public String getUsername(String username)
    {
        return username;
    }

    private void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password= password;
    }

    public String getPassword(String password)
    {
        return password;
    }

    public String getFirstName(String firstName)
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String LastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getEmail(String email)
    {
        return email;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(String phoneNumber)
    {
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

    public String getUserID() {
        return userID;
    }

    public UUID getUserUUID() {
        return userUUID;
    }
    
    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    private UUID generateUUID()
    {
        //check if it has an ID, if from dataloader must convert using toUUID
        return UUID.randomUUID();
    }

    public String toString()
    {
        return "something";
    }

    private boolean login(String username, String passsword)
    {
        return false;
    }

    private boolean register()
    {
        return false;
    }

    private boolean addProject(String projectName)
    {
        return false;
    }

    private Project findProject(String projectName)
    {
        return null;
    }

    private ArrayList<Task> UUIDtoTasks(ArrayList<String> personalTasks){
        //TODO populate myTasks with Task objects based on the UUIDs from personalTasks
        return null;
    }

    private ArrayList<Project> UUIDtoProjects(ArrayList<String> listOfProjects){
        //TODO populate myProjects with Project objects based on the UUIDs from listOfProjects
        return null;
    }

    private UUID toUUID(String userID){
        //TODO convert string UUID from dataLoader to object UUID
        return null;
    }
}


