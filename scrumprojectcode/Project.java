package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Project: Main class code that holds an ArrayList<Column> with the project
 * tasks within each Column
 * 
 * @author kuriakm
 */
public class Project {
    public String projectName;
    public ArrayList<Column> listOfColumns;
    public ArrayList<UUID> assignedUsers;
    private UUID projectUUID;
    private static UserList userList = UserList.getInstance();

    /**
     * Constructor method for a new Project that initializes the project attributes
     * 
     * @param projectName String name set by the user for a new project
     */
    public Project(String projectName) {
        setProjectName(projectName);
        this.projectUUID = generateUUID();
        this.listOfColumns = new ArrayList<>();
        this.assignedUsers = new ArrayList<>();
    }

    /**
     * Constructor method for a Project() loaded from a file and initializes the
     * project attributes with the file contents
     * 
     * @param projectName   String name set by the user for the project
     * @param listOfColumns ArrayList<Column> which holds Column() and Task()
     *                      objects for the project
     * @param projectUUID   UUID generated when project was first created
     */
    public Project(UUID projectUUID, String projectName, ArrayList<Column> listOfColumns,
            ArrayList<UUID> uuidToUser) {
        this.projectUUID = projectUUID;
        this.projectName = projectName;
        this.listOfColumns = listOfColumns;
        setAssignedUsers(uuidToUser);
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * String method that returns the current value of projectName
     * 
     * @return String value of the project's name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * UUID method that generates a value for projectUUID
     * 
     * @return UUID value that is set as the project's unique identifier
     */
    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * UUID method that returns the current value of projectUUID
     * 
     * @return UUID value that acts as the project's unique identifier
     */
    public UUID getProjectUUID() {
        return this.projectUUID;
    }

    /**
     * ArrayList<Column> method that returns the current value of listOfColumns
     * 
     * @return ArrayList<Column> with the project's columns and column tasks
     */
    public ArrayList<Column> getListOfColumns() {
        return this.listOfColumns;
    }

    /**
     * Returns an ArrayList of User objects that are assigned to this project.
     *
     * @return ArrayList of User objects assigned to this project.
     */
    public ArrayList<User> getAssignedUsers() {
        ArrayList<User> usersFromUUID = new ArrayList<>();
        for (UUID userUUID : assignedUsers) {
            User assignedUser = userList.findUser(userUUID);
            usersFromUUID.add(assignedUser);
        }
        return usersFromUUID;
    }

    public ArrayList<UUID> getAssignedUsersUUID() {
        return this.assignedUsers;
    }

    public void setAssignedUsers(ArrayList<UUID> userUUID) {
        this.assignedUsers = userUUID;
    }

    public boolean facadeAddColumn(String columnName) {
        return addColumn(columnName);
    }

    /**
     * Void method that adds a new Column to the project using the ArrayList.add()
     * method
     * 
     * @param column New Column with ArrayList<Task> for the project tasks
     */
    private boolean addColumn(String columnName) {
        if (findColumn(columnName) == null) {
            Column newColumn = new Column(columnName, projectUUID);
            this.listOfColumns.add(newColumn);
            // projectList.saveProjects(); should we save right after modifying?
            return true;
        }
        return false;
    }

    public boolean facadeRemoveColumn(String columnName) {
        return removeColumn(columnName);
    }

    /**
     * Void method that removes a Column from the project using the
     * ArrayList.remove() method
     * 
     * @param column Column to be removed from the project
     */
    private boolean removeColumn(String columnName) {
        if (findColumn(columnName) == null) {
            return false;
        }
        listOfColumns.remove(findColumn(columnName));
        return true;
    }

    public boolean facadeMoveTask(String sourceColumn, String targetColumn, String taskName) {
        return moveTask(sourceColumn, targetColumn, taskName);
    }

    /**
     * Moves a task from one column to another.
     * 
     * @param sourceColumn the name of the column the task is currently in
     * @param targetColumn the name of the column to move the task to
     * @param taskName the name of the task to move
     * @return true if the task was successfully moved, false otherwise
     */
    private boolean moveTask(String sourceColumn, String targetColumn, String taskName) {
        Column oldColumn = findColumn(sourceColumn);
        Column newColumn = findColumn(targetColumn);
        Task task = oldColumn.findTask(taskName);
        oldColumn.facadeRemoveTask(taskName);
        task.setColumnUUID(newColumn.getColumnUUID());
        newColumn.columnTasks.add(task.getTaskUUID());
        if (newColumn.findTask(taskName) == null) {
            return false;
        }
        return true;
    }

    public Column findColumn(String columnName) { 
        for (Column column : listOfColumns) {
            if (column.getColumnName().equals(columnName)) {
                return column;
            }
        }
        return null;
    }

    public String facadePrintProject() {
        return toString();
    }

    /**
     * String method that prints out the contents of the selected column using
     * ArrayList.forEach() method
     * 
     * @return String value of the columnName and columnTasks
     */
    public String displayColumnTasks(Column column) {
        String tasksToString = "";
        for (Task task : column.uuidToTasks()) // TODO: Figure out how to print taskName from UUID taskUUID
            tasksToString = tasksToString + "- " + task.getTaskName() + "\n";
        return "--- " + column.columnName + " ---\n" + tasksToString;
    }

    /**
     * String method that returns the value of projectName with the project columns
     * and tasks underneath
     * 
     * @return String value of projectName and listOfColumns
     */
    public String toString() {
        String columnsToString = "";
        for (Column column : listOfColumns)
            columnsToString = columnsToString + column.toString() + "\n\n";
        return "[" + this.projectName + "]:\n"
                + (columnsToString == null ? "" : columnsToString);
    }

    public boolean addUser(User user) {
        if (user == null)
            return false;
        this.assignedUsers.add(user.getUserUUID());
        return true;
    }

    public boolean removeUser(User user) {
        if (user == null)
            return false;
        this.assignedUsers.remove(user.getUserUUID());
        return true;
    }
}