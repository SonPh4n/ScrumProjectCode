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
    private UUID projectUUID;

    /**
     * Constructor method for a new Project(), calls for methods with preset values
     * 
     * @param projectName String name set by the user for a new project
     */
    public Project(String projectName) {
        this.projectName = projectName;
        this.projectUUID = generateUUID();
        this.listOfColumns = new ArrayList<Column>();
    }

    /**
     * Constructor method for a Project() loaded from a file, calls for methods with
     * preset values
     * 
     * @param projectName   String name set by the user for the project
     * @param listOfColumns ArrayList<Column> which holds Column() and Task()
     *                      objects for the project
     * @param projectUUID   UUID generated when project was first created
     */
    public Project(String projectName, ArrayList<Column> listOfColumns, UUID projectUUID) {
        this.projectName = projectName;
        this.listOfColumns = new ArrayList<Column>(listOfColumns);
        this.projectUUID = projectUUID;
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
     * Void method that adds a new Column to the project using the ArrayList.add()
     * method
     * 
     * @param column New Column with ArrayList<Task> for the project tasks
     */
    private void addColumn(Column column) {
        this.listOfColumns.add(column);
    }

    /**
     * Void method that removes a Column from the project using the
     * ArrayList.remove() method
     * 
     * @param column Column to be removed from the project
     */
    private void removeColumn(Column column) {
        this.listOfColumns.remove(column);
    }

    /**
     * String method that prints out the contents of the selected column using
     * ArrayList.forEach() method
     */
    public String displayColumnTasks(Column column) {
        String tasksToString = "";
        for (Task task : column.columnTasks)
            tasksToString = tasksToString + "- " + column.columnTasks.getTaskName() + "\n";
        return "--- " + column.columnName + " ---\n" + tasksToString;
    }

    /**
     * Void method that adds a new Task in a project column
     * 
     * @param task   User made Task to be added to the Projec
     * @param column Column in the Project's listOfColumns
     */
    private void addTask(Task task, Column column) {
        for (int i = 0; i < this.listOfColumns.size(); i++) {
            if (this.listOfColumns.get(i) == column)
                this.listOfColumns.get(i).columnTasks.add(task);
        }
    }

    /**
     * Void method that removes a Task from a project column
     * 
     * @param task   Task to be removed from the Project
     * @param column Column where the Task was in
     */
    private void removeTask(Task task, Column column) {
        for (int i = 0; i < this.listOfColumns.size(); i++) {
            if (this.listOfColumns.get(i) == column)
                this.listOfColumns.get(i).columnTasks.remove(task);
        }
    }

    /**
     * String method that returns the value of projectName with the project columns
     * and tasks underneath
     * 
     * @return String value of the Project and their values
     */
    public String toString() {
        return "[" + this.projectName + "]:\n"
                + listOfColumns.toString();
    }
}