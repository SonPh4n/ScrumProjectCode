package scrumprojectcode;

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

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber,
            String type) {
        this.userUUID = generateUUID();

        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setLoggedIn(true);
        setUserType(type);
    }

    public User(UUID userID, String firstName, String lastName, String username, String password, String email,
            String phoneNumber, String type) {
        setUserUUID(userID);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setLoggedIn(true);
        setUserType(type);
    }

    public boolean facadeLogin(String username, String password) {
        return login(username, password);
    }

    // checks if the username and password match to login
    private boolean login(String username, String passsword) {
        if (this.username.equals(username) && this.password.equals(password)) {
            this.loggedIn = true;
            return true;
        }
        this.loggedIn = false;
        return false;
    }

    // logs out the user
    public void logout() {
        userList.saveUsers();
        projectList.saveProjects();
        loggedIn = false;
    }

    public boolean facadeRegister(String firstName, String lastName, String username, String password, String email,
            String phoneNumber, String type) {
        return register(firstName, lastName, username, password, email, phoneNumber, type);
    }

    // registers a new user by checking if their UUID already exists, if not, create
    // a new UUID and set their info
    private boolean register(String firstName, String lastName, String username, String password, String email,
            String phoneNumber, String type) {
        if (userList.findUser(username, password) == null) {
            User newUser = new User(firstName, lastName, username, password, email, phoneNumber, type);
            userList.getListOfUsers().add(newUser);
            userList.saveUsers();
            return true;
        }
        return false;
    }

    public boolean facadeAddProject(String projectName) {
        return addProject(projectName);
    }

    private boolean addProject(String projectName) {
        if (findProject(projectName) == null) {
            Project newProject = new Project(projectName);
            projectList.getListOfProjects().add(newProject);
            projectList.saveProjects();
            return true;
        }
        return false;
    }

    public boolean facadeRemoveProject(String projectName) {
        return removeProject(projectName);
    }

    private boolean removeProject(String projectName) {
        if (findProject(projectName) == null)
            return false;
        projectList.getListOfProjects().remove(findProject(projectName));
        return true;
    }

    public Project findProject(String projectName) {
        projectList = ProjectList.getInstance();
        for (Project project : projectList.getListOfProjects())
            if (project.getProjectName().equals(projectName))
                return project;
        return null;
    }

    public boolean facadeAddUserToTask(String username, String taskName) {
        return addUserToTask(username, taskName);
    }

    public boolean facadeRemoveUserToTask(String username, String taskName) {
        return removeUserFromTask(username, taskName);
    }

    public boolean addUserToTask(String username, String taskName) {
        Task task = projectList.findTask(taskName);
        User user = userList.findUser(username);
        if (user == null || task == null)
            return false;
        return task.addUser(user);
    }

    public boolean removeUserFromTask(String username, String taskName) {
        Task task = projectList.findTask(taskName);
        User user = userList.findUser(username);
        if (user == null || task == null)
            return false;
        return task.removeUser(user);
    }

    public boolean facadeAddUserToProject(String username, String projectName) {
        return addUserToProject(username, projectName);
    }

    public boolean facadeRemoveUserFromProject(String username, String projectName) {
        return removeUserFromProject(username, projectName);
    }

    public boolean addUserToProject(String username, String projectName) {
        Project project = projectList.findProject(projectName);
        User user = userList.findUser(username);
        if (user == null || project == null || projectList.findProject(projectName).getAssignedUsers().contains(user))
            return false;
        return project.addUser(user);
    }

    public boolean removeUserFromProject(String username, String projectName) {
        Project project = projectList.findProject(projectName);
        User user = userList.findUser(username);
        if (user == null || project == null || !projectList.findProject(projectName).getAssignedUsers().contains(user))
            return false;
        return project.removeUser(user);

    }

    public boolean facadeAddComment(String taskName, String comment) {
        return addComment(taskName, comment);
    }

    public boolean addComment(String taskName, String comment) {
        userList = UserList.getInstance();
        projectList = ProjectList.getInstance();
        Task task = projectList.findTask(taskName);
        if (task == null || comment.equals("") || task.findComment(comment) != null)
            return false;
        return task.addComment(this, comment);
    }

    public boolean facadeAddReplyComment(String taskName, String comment, String originalUsername,
            String originalComment) {
        return addReplyComment(taskName, comment, originalUsername, originalComment);
    }

    public boolean addReplyComment(String taskName, String comment, String originalUsername,
            String originalComment) {
        userList = UserList.getInstance();
        projectList = ProjectList.getInstance();
        Task task = projectList.findTask(taskName);
        User originalUser = userList.findUser(originalUsername);
        if (task == null || originalUser == null || comment.equals("") || task.findComment(originalComment) == null)
            return false;
        return task.addReplyComment(this, comment, originalUser, originalComment);
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

    private UUID generateUUID() {
        // check if it has an ID, if from dataloader must convert using toUUID
        return UUID.randomUUID();
    }

    public String toString() {
        return this.username;
    }
}