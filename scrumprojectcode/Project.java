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
    TaskList taskList = TaskList.getInstance(); // TODO update UML

    /**
     * Constructor method for a new Project that initializes the project attributes
     * 
     * @param projectName String name set by the user for a new project
     */
    public Project(String projectName) {
        this.projectName = projectName;
        this.projectUUID = generateUUID();
        this.listOfColumns = new ArrayList<Column>();
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
            ArrayList<UUID> assignedUsers) {
        this.projectUUID = projectUUID; // convert String UUID to object UUID
        this.projectName = projectName;
        this.listOfColumns = listOfColumns; // converts arrayList of String UUIDs to Column objects
        // TODO: Convert ArrayList<String> assignedUsers to ArrayList<User>
        this.assignedUsers = assignedUsers;
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

    public ArrayList<UUID> getAssignedUsers() {
        return this.assignedUsers;
    }

    public void setAssignedUsers(ArrayList<UUID> assignedUsers) {
        this.assignedUsers = assignedUsers;
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

    public Column findColumn(String columnName) { // can this be public
        for (Column column : listOfColumns) {
            if (column.getColumnName().equals(columnName)) {
                return column;
            }
        }
        return null;
    }

    /**
     * String method that prints out the contents of the selected column using
     * ArrayList.forEach() method
     * 
     * @return String value of the columnName and columnTasks
     */
    public String displayColumnTasks(Column column) {
        String tasksToString = "";
        for (UUID task : column.getColumnTasks()) // TODO: Figure out how to print taskName from UUID taskUUID
            tasksToString = tasksToString + "- " + taskList.findTask(task).getTaskName() + "\n";
        return "--- " + column.columnName + " ---\n" + tasksToString;
    }

    /**
     * Void method that adds a new Task in a project column
     * 
     * @param task   User made Task to be added to the Projec
     * @param column Column in the Project's listOfColumns
     */
    /*
     * private void addTask(UUID task, Column column) {
     * for (int i = 0; i < this.listOfColumns.size(); i++) {
     * if (this.listOfColumns.get(i) == column)
     * this.listOfColumns.get(i).columnTasks.add(task);
     * }
     * }
     */

    /**
     * Void method that removes a Task from a project column
     * 
     * @param task   Task to be removed from the Project
     * @param column Column where the Task was in
     */
    /*
     * private void removeTask(UUID task, Column column) {
     * for (int i = 0; i < this.listOfColumns.size(); i++) {
     * if (this.listOfColumns.get(i) == column)
     * this.listOfColumns.get(i).columnTasks.remove(task);
     * }
     * }
     */

    /**
     * String method that returns the value of projectName with the project columns
     * and tasks underneath
     * 
     * @return String value of projectName and listOfColumns
     */
    public String toString() {
        return "[" + this.projectName + "]:\n"
                + listOfColumns.toString();
    }

    /**
     * UUID method that converts String columnUUID to a UUID
     * 
     * @param columnUUID String to be converted as a UUID
     * @return Converted columnUUID value
     */
    private UUID toUUID(String projectUUId) {
        // TODO convert string UUID from Dataloader to object UUID
        return null;
    }

    /**
     * ArrayList<Column> method that converts ArrayList<String> to ArrayList<Column>
     * 
     * @param columnTasks ArrayList<String> to be converted as ArrayList<Object>
     * @return ArrayList<Column> to set as Project.listOfColumns
     */
    // private ArrayList<Column> UUIDtoColumn(ArrayList<String> listOfColumns) {
    // TODO convert String UUIDs from DataLoader to Column Objects
    // return null;
    // }
}