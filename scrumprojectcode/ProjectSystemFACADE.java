package scrumprojectcode;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as the facade for the project management system. It
 * provides methods for user management, project management, and task
 * management. It also implements the Singleton design pattern to ensure that
 * only one instance of the class is created.
 * 
 * @version 1.0
 * @since 2021-10-18
 */
public class ProjectSystemFACADE {
    private static ProjectSystemFACADE facade;
    private UserList userList = UserList.getInstance();
    private User currentUser;
    private ProjectList projectList = ProjectList.getInstance();

    /**
     * Private constructor to prevent direct instantiation
     */
    private ProjectSystemFACADE() {
        this.currentUser = new User("guest", "user", "guest", "guest", "guest", "guest", "guest");
    }

    /**
     * Get the singleton instance
     */
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
     * 
     * @param password String of user's password
     * 
     * @return returns true if the user was found from current user and updates
     *         status of loggedIn to true, returns false if user was not found
     */
    public boolean login(String username, String password) {
        User user = userList.findUser(username, password);
        if (user == null || !user.getUsername().equals(username) || !user.getPassword().equals(password))
            return false;
        setCurrentUser(user);
        currentUser.facadeLogin(username, password);
        return true;
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
        userList.saveUsers();
        projectList.saveProjects();

        currentUser.logout();
        currentUser = null;
        return true;
    }

    /**
     * creates a new User and adds it to the list of users if they don't already
     * exist
     * 
     * @param firstName
     * 
     * @param lastName
     * 
     * @param username
     * 
     * @param password
     * 
     * @param email
     * 
     * @param phoneNumber
     * 
     * @param type
     * 
     * @return returns true of a new user was successfully added, false if not or if
     *         already exits
     */
    public boolean register(String firstName, String lastName, String username, String password, String email,
            String phoneNumber, String type) {
        return currentUser.facadeRegister(firstName, lastName, username, password, email, phoneNumber, type);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
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
            boolean found = currentUser.findProject(projectName).facadeAddColumn(columnName);
            if (found)
                projectList.saveProjects();
            return found;
        }
        return false;
    }

    public boolean removeColumn(String projectName, String columnName) {
        return currentUser.findProject(projectName).facadeRemoveColumn(columnName);
    }

    public boolean addTask(String projectName, String columnName, String taskName,
            String taskDesc, String taskType, String dueDate) {
        Project project = currentUser.findProject(projectName);
        System.out.println(project.getProjectName());
        Column column = project.findColumn(columnName);
        System.out.println(column.getColumnName());
        System.out.println(columnName);
        return column.facadeAddTask(project.getProjectUUID(), column.getColumnUUID(), currentUser,
                taskName, taskDesc, taskType, dueDate);
    }

    public boolean removeTask(String projectName, String columnName, String taskName) {
        Project project = currentUser.findProject(projectName);
        Column column = project.findColumn(columnName);
        projectList.saveProjects();
        return column.facadeRemoveTask(taskName);
    }

    public boolean moveTask(String projectName, String sourceColumn, String targetColumn, String taskName) {
        Project project = currentUser.findProject(projectName);
        return project.facadeMoveTask(sourceColumn, targetColumn, taskName);
    }

    public String findTask(String taskName) {
        projectList = ProjectList.getInstance();
        return projectList.findTask(taskName).getTaskDescription();
    }

    public boolean addComment(String taskName, String comment) {
        return currentUser.facadeAddComment(taskName, comment);
    }

    public boolean addReplyComment(String taskName, String comment, String originalUsername,
            String originalComment) {
        return currentUser.facadeAddReplyComment(taskName, comment, originalUsername, originalComment);
    }

    public boolean addUserToProject(String username, String projectName) {
        return currentUser.facadeAddUserToProject(username, projectName);
    }

    public boolean removeUserFromProject(String username, String projectName) {
        return currentUser.facadeRemoveUserFromProject(username, projectName);
    }

    public boolean addUserToTask(String username, String taskName) {
        return currentUser.facadeAddUserToTask(username, taskName);
    }

    public boolean removeUserFromTask(String username, String taskName) {
        return currentUser.facadeRemoveUserToTask(username, taskName);
    }

    public boolean printTaskComments(String taskName) {
        Task task = projectList.findTask(taskName);
        return task.facadePrintComments();
    }

    public boolean printProject(String projectName) {
        Project project = projectList.findProject(projectName);
        String projectToPrint = project.facadePrintProject();
        return projectToPrint != null;
    }

    // return todo tasks
    public ArrayList<Task> getProjectTasks(String columnName) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Task> todoTasks = new ArrayList<>();
        List<String> inProgressTasks;
        List<String> doneTasks;

        for (Project project : projectList.getListOfProjects()) {
            for (Column column : project.getListOfColumns()) {
                if (column.getColumnName().equals(columnName)) {
                    todoTasks.addAll(column.getColumnTasks());
                }
            }
        }

        return todoTasks;
    }

    public Project getProject(String projectName) {
        Project projectReturn = null;
        for (Project project : projectList.getListOfProjects()) {
            if (project.getProjectName().equals(projectName))
                projectReturn = project;
        }
        return projectReturn;
    }

    public String findTaskColumn(String taskName, String taskProject) {
        String returnColumnName = null;
        for (Column column : getProject(taskProject).getListOfColumns()) {
            for (Task task : column.getColumnTasks()) {
                if (task.getTaskName().equals(taskName))
                    returnColumnName = column.getColumnName();
            }
        }
        return returnColumnName;
    }
}
