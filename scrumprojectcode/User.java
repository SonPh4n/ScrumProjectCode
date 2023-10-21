package scrumprojectcode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Changes
 * 
 * @author SonPh4n
 * @author kuriakm
 * @author jedalto
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

    private UUID userUUID;

    private ArrayList<Task> myTasks;
    private ArrayList<Project> myProjects;

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber,
            String type) {
        this.userUUID = generateUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.loggedIn = false;
        this.type = type;
    }

    public User(UUID userID, String firstName, String lastName, String username, String password, String email,
            String phoneNumber,
            String type, ArrayList<UUID> tasks, ArrayList<UUID> projects) {
        this.userUUID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.myTasks = UUIDtoTasks(tasks); // arraylist of Task objects
        this.myProjects = UUIDtoProjects(projects); // arraylist of Project objects
        this.loggedIn = false;
        this.type = type;
    }

    // generates a new UUID for new Users
    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    // Setters and Getters
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
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

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }
    // end of setters and getters

    // prints public User attributes
    public String toString() {
        return "[" + this.username + "]:\n"
                + "Type: " + this.type + "\n"
                + "Name: " + this.firstName + " " + this.lastName + "\n"
                + "Logged In: " + this.loggedIn;
    }

    // prints full User attributes
    private String printFullAttributes() {
        return "[" + this.username + "]:\n"
                + "Type: " + this.type + "\n"
                + "Name: " + this.firstName + " " + this.lastName + "\n"
                + "Email: " + this.email + "\n"
                + "Phone Number: " + this.phoneNumber;
    }

    // checks if the username and password match to login
    private boolean login(String username, String passsword) {
        if (this.username.equals(username) && this.password.equals(password)) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    // logs out the user
    public void logout() {
        loggedIn = false;
    }

    // registers a new user by checking if their UUID already exists, if not, create
    // a new UUID and set their info
    private boolean register(String username, String password, String firstName, String lastName, String email,
            String phoneNumber, String type) {
        if (this.userUUID == null) {
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
        if (myProjects == null) {
            myProjects = new ArrayList<>();
        }
        myProjects.add(new Project(projectName));
        return true;
    }

    private Project findProject(String projectName) {
        for (Project project : myProjects) {
            if (project.getProjectName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    private ArrayList<Task> UUIDtoTasks(ArrayList<UUID> personalTasks) {
        // TODO populate myTasks with Task objects based on the UUIDs from personalTasks
        ArrayList<Task> tasks = new ArrayList<>();
        for (UUID taskUUID : personalTasks) {
            Task task = findTaskByUUID(taskUUID);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private Task findTaskByUUID(UUID taskUUID) {
        for (Task task : myTasks) {
            if (task.getTaskUUID().equals(taskUUID)) {
                return task;
            }
        }
        return null;
    }

    private ArrayList<Project> UUIDtoProjects(ArrayList<UUID> listOfProjects) {
        // TODO populate myProjects with Project objects based on the UUIDs from
        // listOfProjects
        ArrayList<Project> projects = new ArrayList<>();
        for (UUID projectUUID : listOfProjects) {
            Project project = findProjectByUUID(projectUUID);
            if (project != null) {
                projects.add(project);
            }
        }
        return projects;
    }

    private Project findProjectByUUID(UUID projectUUID) {
        for (Project project : myProjects) {
            if (project.getProjectUUID().equals(projectUUID)) {
                return project;
            }
        }
        return null;
    }

    // converts string UUID from dataLoader to object UUID
    private UUID toUUID(String userID) {
        return UUID.fromString(userID);
    }
}
