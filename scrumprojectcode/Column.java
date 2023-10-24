package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class Column {
    public String columnName;
    public ArrayList<UUID> columnTasks; // Preemptively changed this to ArrayList<UUID> to make it easier to read tasks
                                        // from column.json @kuriakm
    private UUID columnUUID;
    private UUID projectUUID;

    /**
     * Debugger method, remove in final submission
     */

    Column(Project project) {
        this.projectUUID = project.getProjectUUID();
        this.columnUUID = generateUUID();
        this.columnName = "Test column name";
        this.columnTasks = new ArrayList<>();
    }

    /**
     * Constructor method for a new Column() which initializes the Column attributes
     * 
     * @param columnName
     * @param task
     */
    public Column(String columnName, UUID taskUUID) {
        this.createColumn(columnName);
        this.columnTasks = new ArrayList<UUID>();
        this.addTask(taskUUID);
        this.columnUUID = generateUUID();
    }

    public Column(UUID projectID, UUID columnID, String columnTitle, ArrayList<UUID> columnTasks) {
        this.projectUUID = projectID;
        this.columnUUID = columnID;
        this.columnName = columnTitle;
        this.columnTasks = columnTasks;
    }

    public UUID generateUUID() {
        return UUID.randomUUID();
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
     * Void method that adds a new Task to the Column using the ArrayList.add()
     * method
     * 
     * @param task New project Task to be added to columnTasks
     */
    private void addTask(UUID task) {
        this.columnTasks.add(task);
    }

    /**
     * Void method that removes a Task from the Column using the ArrayList.remove()
     * method
     * 
     * @param task Task to be removed from the column
     */
    private void removeTask(UUID task) {
        this.columnTasks.remove(task);
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ArrayList<UUID> getColumnTasks() {
        return this.columnTasks;
    }

    public void setColumnTasks(ArrayList<UUID> columnTasks) {
        this.columnTasks = columnTasks;
    }

    public UUID getColumnUUID() {
        return this.columnUUID;
    }

    public void setColumnUUID(UUID columnUUID) {
        this.columnUUID = columnUUID;
    }

    public UUID getProjectUUID() {
        return this.projectUUID;
    }

    public void setProjectUUID(UUID projectUUID) {
        this.projectUUID = projectUUID;
    }

    public String toString() {
        String tasksToString = "";
        /*
         * for (Task task : columnTasks)
         * tasksToString = tasksToString + "- " + task.getTaskName() + "\n";
         */
        return "--- " + this.columnName + " ---\n" + tasksToString;
    }

}