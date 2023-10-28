package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class ProjectSystemFACADE {
    private static ProjectSystemFACADE facade; // Singleton instance
    private UserList userList;
    private ArrayList<Project> projects;
    private User currentUser; // Track the currently logged-in user

    // Private constructor to prevent direct instantiation
    private ProjectSystemFACADE() {
        this.userList = UserList.getInstance();
        this.projects = new ArrayList<>();
        this.currentUser = null;
        facade = getFacadeInstance();
    }

    // Get the singleton instance
    public static ProjectSystemFACADE getFacadeInstance() {
        if (facade == null) {
            facade = new ProjectSystemFACADE();
        }
        return facade;
    }

    // User management methods

    public boolean login(String username, String password) {
        User user = userList.findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean register(String firstName, String lastName, String username, String password, String email, String phoneNumber, String type) {
        User user = new User(firstName, lastName, username, password, email, phoneNumber, type);
        if (!userList.getListOfUsers().contains(user)) {
            userList.getListOfUsers().add(user);
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Project management methods

    public void addProject(String projectName) {
        projects.add(new Project(projectName));
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public void addColumn(Project project, Column column) {
        project.addColumn(column);
    }

    public void removeColumn(Project project, Column column) {
        project.removeColumn(column);
    }

    public void addTask(Project project, UUID task, Column column) {
        project.addTask(task, column);
    }

    public void removeTask(Project project, UUID task, Column column) {
        project.removeTask(task, column);
    }

    public void moveTask(Column sourceColumn, Column targetColumn, UUID task) {
        // Implement task moving logic
    }

    public void addComment(Task task, String comment) {
        task.addComment(comment, comment, comment, comment);
    }
}
