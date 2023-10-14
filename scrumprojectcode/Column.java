package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Column: Main class code that holds an ArrayList<Task> with tasks for the
 * of the Project class
 * 
 * @author SonPh4n
 * @author kuriakm
 */
public class Column {
    public String columnName;
    public ArrayList<Task> columnTasks;
    private UUID columnUUID;

    /**
     * Constructor method for a new Column() which initializes the Column attributes
     * 
     * @param columnName
     * @param task
     */
    public Column(String columnName, Task task) {
        this.createColumn(columnName);
        this.columnTasks = new ArrayList<Task>();
        this.addTask(task);
        this.columnUUID = generateUUID();
    }

    /**
     * Constructor method for a Column() loaded from a file and initializes the
     * column attributes with the file contents
     * 
     * @param columnUUID  UUID generated when the column was initially created
     * @param columnName  String name of the column
     * @param columnTasks ArrayList<Task> that holds the tasks of the project column
     */
    public Column(UUID columnUUID, String columnName, ArrayList<Task> columnTasks) {
        this.columnUUID = columnUUID;
        this.columnName = columnName;
        this.columnTasks = columnTasks;
    }

    /**
     * Void method that sets String this.columnName to the user-set column name
     * 
     * @param columnName String value that holds the user-set column name
     */
    private void createColumn(String columnName) {
        this.columnName = columnName;
    }

    /**
     * String method that returns the current value of columnName
     * 
     * @return String value of the column's name
     */
    public String getColumnName() {
        return this.columnName;
    }

    /**
     * UUID method that generates a value for columnUUID
     * 
     * @return UUID value that is set as the column's unique identifier
     */
    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    /**
     * UUID method that returns the current value of columnUUID
     *
     * @return UUID value that acts as the column's unique identifier
     */
    public UUID getColumnUUID() {
        return this.columnUUID;
    }

    /**
     * ArrayList<Task> method that return the current value of columnTasks
     * 
     * @return ArrayList<Task> with the column's tasks
     */
    public ArrayList<Task> getColumnTasks() {
        return this.columnTasks;
    }

    /**
     * Void method that adds a new Task to the Column using the ArrayList.add()
     * method
     * 
     * @param task New project Task to be added to columnTasks
     */
    private void addTask(Task task) {
        this.columnTasks.add(task);
    }

    /**
     * Void method that removes a Task from the Column using the ArrayList.remove()
     * method
     * 
     * @param task Task to be removed from the column
     */
    private void removeTask(Task task) {
        this.columnTasks.remove(task);
    }

    /**
     * String method that returns the value of columnName with the tasks underneath
     * 
     * @return String value of the columnName and columnTasks
     */
    public String toString() {
        String tasksToString = "";
        for (Task task : columnTasks)
            tasksToString = tasksToString + "- " + task.getTaskName() + "\n";
        return "--- " + this.columnName + " ---\n" + tasksToString;
    }

}