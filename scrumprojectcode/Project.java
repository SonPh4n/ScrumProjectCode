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
    public Project(String projectUUID, String projectName, ArrayList<String> listOfColumns) {
        this.projectUUID = toUUID(projectUUID); // convert String UUID to object UUID
        this.projectName = projectName;
        this.listOfColumns = UUIDtoColumn(listOfColumns); // converts arrayList of String UUIDs to Column objects
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
<<<<<<< Updated upstream
    public String displayColumnTasks(Column column) {
=======
    public String displayColumnTasks(Column column) { // @author jedalto slightly edited this to compile, should work
                                                      // the same
>>>>>>> Stashed changes
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
    private ArrayList<Column> UUIDtoColumn(ArrayList<String> listOfColumns) {
        // TODO convert String UUIDs from DataLoader to Column Objects
        return null;
    }
}