package scrumprojectcode;

//import java.util.ArrayList;
//import java.util.UUID;

public class ProjectSystemFACADE {
    private static ProjectSystemFACADE facade; // Singleton instance
    private static UserList userList = UserList.getInstance();
    private User currentUser; // Track the currently logged-in user

    // Private constructor to prevent direct instantiation
    private ProjectSystemFACADE() {
        this.currentUser = null;
    }

    // Get the singleton instance
    public static ProjectSystemFACADE getFacadeInstance() {
        if (facade == null) {
            facade = new ProjectSystemFACADE();
        }
        return facade;
    }

    // User management methods

    /**
     * logs in a current user
     * 
     * @param username String of user's username
     * @param password String of user's password
     * @return returns true if the user was found from current user and updates
     *         status of loggedIn to true, returns false if user was not found
     */
    public boolean login(String username, String password) {
        User user = userList.findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            currentUser.facadeLogin(username, password);
            return true;
        }
        return false;
    }

    /**
     * updates status of loggedIn to false
     * currentUser changed to null
     * 
     * @return false if no user is currently logged in, true if user is logged in
     *         and was successfully logged out
     */
    public boolean logout() {
        if (currentUser == null) {
            return false;
        }
        currentUser.logout();
        currentUser = null;
        return true;
    }

    /**
     * creates a new User and adds it to the list of users if they don't already
     * exist
     * 
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param email
     * @param phoneNumber
     * @param type
     * @return returns true of a new user was successfully added, false if not or if
     *         already exits
     */
    public boolean register(String firstName, String lastName, String username, String password, String email,
            String phoneNumber, String type) {
        return currentUser.facadeRegister(firstName, lastName, username, password, email, phoneNumber, type);
    }

    // getter for currentUser
    public User getCurrentUser() {
        return currentUser;
    }

    // Project management methods

    public boolean addProject(String projectName) {
        return currentUser.facadeAddProject(projectName);
        // projects.add(new Project(projectName));
    }

    public boolean removeProject(String projectName) {
        return currentUser.facadeRemoveProject(projectName);
    }

    public boolean addColumn(String projectName, String columnName) {
        if (currentUser != null) {
            return currentUser.findProject(projectName).facadeAddColumn(columnName);
        }
        return false;
    }

    public boolean removeColumn(String projectName, String columnName) {
        return currentUser.findProject(projectName).facadeRemoveColumn(columnName);
    }

    public boolean addTask(String projectName, String columnName, String taskName,
            String taskDesc, String taskType, String dueDate) {
        Project project = currentUser.findProject(projectName);
        Column column = project.findColumn(columnName);
        return column.facadeAddTask(project.getProjectUUID(), column.getColumnUUID(), currentUser.getUserUUID(),
                taskName, taskDesc, taskType, dueDate);
    }

    public boolean removeTask(String projectName, String columnName, String taskName) {
        Project project = currentUser.findProject(projectName);
        Column column = project.findColumn(columnName);
        return column.facadeRemoveTask(taskName);
    }

    public boolean moveTask(String projectName, String sourceColumn, String targetColumn, String taskName) {
        Project project = currentUser.findProject(projectName);
        return project.facadeMoveTask(sourceColumn, targetColumn, taskName);
    }

    public void addComment(Task task, String comment) {
        task.addComment(comment, comment, comment, comment);
    }
}
