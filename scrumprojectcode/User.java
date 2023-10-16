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


    public User(String userID, String firstName, String lastName, String username, String email, String phoneNumber, ArrayList<String> tasks, ArrayList<String> projects)
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
    }

    public User(UUID userID, String firstName, String lastName, String username, String email, String phoneNumber, ArrayList<Task> tasks, ArrayList<Project> projects)
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
    }

    public User()
    {

    }

    private void setUsername(String username)
    {
        this.username = username;
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
