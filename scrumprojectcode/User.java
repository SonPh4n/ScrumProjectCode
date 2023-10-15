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
    private ArrayList<Task> personalTasks;
    private ArrayList<Project> listOfProjects;
    private UUID userUUID;

    public User(UUID userID, String firstName, String lastName, String username, String email, String phoneNumber, ArrayList<Task> tasks, ArrayList<Project> projects)
    {
        this.userUUID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.personalTasks = tasks;
        this.listOfProjects = projects;
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
}
