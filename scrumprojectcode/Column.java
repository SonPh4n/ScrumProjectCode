package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class Column {
    public String columnName;
    public ArrayList<Task> columnTasks;
    private UUID columnUUID;
    private UUID projectUUID;
    private static ProjectList projectList = ProjectList.getInstance();

    /**
     * Constructor method for a new Column() which initializes the Column
     * 
     * @param columnName  String name of the column
     * @param projectUUID The project ID where the column is located
     */
    public Column(String columnName, UUID projectUUID) { // slightly changed this from String columnName, UUID taskUUID
        setProjectUUID(projectUUID);
        setColumnName(columnName);
        setColumnUUID(generateUUID());
        this.columnTasks = new ArrayList<Task>();
    }

    /**
     * Constructor method for a new Column() from project.json
     *
     * @param projectUUID The project ID where the column is located
     * @param columnID    The column's unique ID
     * @param columnTitle String name of the column
     * @param columnTasks The tasks listed in the column task section of each column
     */
    public Column(UUID projectID, UUID columnID, String columnTitle, ArrayList<Task> columnTasks) {
        setProjectUUID(projectID);
        setColumnUUID(columnID);
        setColumnName(columnTitle);
        setColumnTasks(columnTasks);
    }

    public boolean facadeAddTask(UUID projectUUID, UUID columnUUID, User user, String taskName, String taskDesc,
            String taskType, String dueDate) {
        return addTask(projectUUID, columnUUID, user, taskName, taskDesc, taskType, dueDate);
    }

    /**
     * Boolean method that adds a new Task to the Column using the ArrayList.add()
     * method
     * 
     * @param task New project Task to be added to columnTasks
     */
    public boolean addTask(UUID projectUUID, UUID columnUUID, User user, String taskName, String taskDesc,
            String taskType, String dueDate) {
        if (projectList.findTask(taskName) != null)
            return false;
        Task newTask = new Task(projectUUID, columnUUID, taskName, taskDesc, taskType, user, dueDate);
        columnTasks.add(newTask);
        projectList.addTask(newTask, columnUUID);
        projectList.saveProjects();
        return true;
    }

    /**
     * Boolean method that is used by ProjectSystemFACADE to remove a task within
     * Column
     * 
     * @param taskName String name
     * @return
     */
    public boolean facadeRemoveTask(String taskName) {
        return removeTask(taskName);
    }

    /**
     * Void method that removes a Task from the Column using the ArrayList.remove()
     * method
     * 
     * @param task Task to be removed from the column
     */
    private boolean removeTask(String taskName) {
        if (findTask(taskName) == null) {
            return false;
        }
        Task task = findTask(taskName);
        projectList.removeTask(task, columnUUID);
        columnTasks.remove(task);
        return true;
    }

    public Task findTask(String taskName) {
        return projectList.findTask(taskName);
    }

    public Task findTask(UUID taskUUID) {
        return projectList.findTask(taskUUID);
    }

    /**
     * UUID method that generates a unique UUID for Column
     * 
     * @return UUID value to be set as the value of columnUUID
     */
    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ArrayList<Task> getColumnTasks() {
        return this.columnTasks;
    }

    public void setColumnTasks(ArrayList<Task> columnTasks) {
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
        for (Task task : columnTasks)
            tasksToString = tasksToString + (task == null ? "" : "- " + task.getTaskName() + "\n");
        return "--- " + this.columnName + " ---\n" + (tasksToString == null ? "" : tasksToString);
    }
}