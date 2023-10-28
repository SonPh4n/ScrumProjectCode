package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
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
    private UUID userUUID;
    private static UserList userList = UserList.getInstance();
    private static ProjectList projectList = ProjectList.getInstance();
    /*
     * private ArrayList<UUID> myTasks;
     */ // Was unable to use Task and Project object when loading from user.json so I
        // changed these to ArrayList<UUID> @kuriakm
    private ArrayList<UUID> myProjects;

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
            String phoneNumber, String type) {
        this.userUUID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.loggedIn = false;
        this.type = type;
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

    public void setLastName(String lastName) {
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

    private UUID generateUUID() {
        // check if it has an ID, if from dataloader must convert using toUUID
        return UUID.randomUUID();
    }

    public String toString() {
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
    public void logout() { // TODO: Call UserList.saveTasks(), saveUsers(), saveProjects() and set loggedIn
                           // to false
        loggedIn = false;
    }

    // registers a new user by checking if their UUID already exists, if not, create
    // a new UUID and set their info
    private boolean register(String username, String password, String firstName, String lastName, String email,
            String phoneNumber, String type) {
        if (userList.findUser(email) != null) { // TODO: Create UserList.findUser() to find User before registering
            User newUser = new User(firstName, lastName, username, password, email, phoneNumber, type);
            userList.getListOfUsers().add(newUser);
            return true;
        }
        return false;
    }

    // Temp method to prevent duplicate Users with different UUIDs from being added
    // to user.json
    public boolean equals(User aU) {
        return this.username.equals(aU.getUsername()) &&
                this.password.equals(aU.getPassword()) &&
                this.firstName.equals(aU.getFirstName()) &&
                this.lastName.equals(aU.getLastName()) &&
                this.email.equals(aU.getEmail()) &&
                this.phoneNumber.equals(aU.getPhoneNumber()) &&
                this.type.equals(aU.getUserType());
    }

    /*
     * public ArrayList<Project> UUIDtoProjects(ArrayList<UUID> projectsUUID) {
     * Project testProject = new Project();
     * myProjects.add(testProject);
     * return myProjects;
     * }
     * 
     * public ArrayList<Task> UUIDtoTasks(ArrayList<UUID> tasksUUID) {
     * Task testTask = new Task();
     * myTasks.add(testTask);
     * return myTasks;
     * }
     */

    
    private boolean addProject(String projectName) {
        if (projectList.getListOfProjects().) {
         = new ArrayList<Project>();
        }
        listOfProjects.add(projectName);
    }
     
    private Project findProject(String projectName) {
        for (Project project : myProjects) {
            if (project.getProjectName().equals(projectName)) {
                return project;
            }
        }
        return null;
    } 
     /* * 
     * private ArrayList<Task> UUIDtoTasks(ArrayList<String> personalTasks) {
     * // TODO populate myTasks with Task objects based on the UUIDs from
     * personalTasks
     * ArrayList<Task> tasks = new ArrayList<>();
     * for (String taskUUID : personalTasks) {
     * Task task = findTaskByUUID(taskUUID);
     * if (task != null) {
     * tasks.add(task);
     * }
     * }
     * return tasks;
     * }
     * 
     * private Task findTaskByUUID(String taskUUID) {
     * for (Task task : myTasks) {
     * if (task.getTaskUUID().toString().equals(taskUUID)) {
     * return task;
     * }
     * }
     * return null;
     * }
     * 
     * private ArrayList<Project> UUIDtoProjects(ArrayList<String> listOfProjects) {
     * // TODO populate myProjects with Project objects based on the UUIDs from
     * // listOfProjects
     * ArrayList<Project> projects = new ArrayList<>();
     * for (String projectUUID : listOfProjects) {
     * Project project = findProjectByUUID(projectUUID);
     * if (project != null) {
     * projects.add(project);
     * }
     * }
     * return null;
     * }
     * 
     * private Project findProjectByUUID(String projectUUID) {
     * for (Project project : myProjects) {
     * if (project.getProjectUUID().toString().equals(projectUUID)) {
     * return project;
     * }
     * }
     * return null;
     * }
     * 
     * private UUID toUUID(String userID) {
     * // TODO convert string UUID from dataLoader to object UUID
     * return UUID.fromString(userID);
     * }
     */ */

}
