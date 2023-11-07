package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class Column {
    public String columnName;
    public ArrayList<UUID> columnTasks; // Preemptively changed this to ArrayList<UUID> to make it easier to read tasks
                                        // from column.json @kuriakm
    private UUID columnUUID;
    private UUID projectUUID;
    private ArrayList<Task> taskList = DataLoader.loadTasks(); // Temp attribute to bypass list classes @kuriakm
    
    // TODO: Reimplement attribute after testing @kuriakm
    // private static TaskList taskList = TaskList.getInstance();

    /**
     * Constructor method for a new Column() which initializes the Column attributes
     * 
     * @param columnName
     * @param task
     */
    public Column(String columnName, UUID projectUUID) { // slightly changed this from String columnName, UUID taskUUID
        this.createColumn(columnName);
        this.columnTasks = new ArrayList<UUID>();
        this.columnUUID = generateUUID();
        this.projectUUID = projectUUID;
    }

    public Column(UUID projectID, UUID columnID, String columnTitle, ArrayList<UUID> columnTasks) {
        this.projectUUID = projectID;
        this.columnUUID = columnID;
        this.columnName = columnTitle;
        this.columnTasks = columnTasks;
    }

    /**
     * UUID method that generates a unique UUID for Column
     * 
     * @return UUID value to be set as the value of columnUUID
     */
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

    public boolean facadeAddTask(UUID projectUUID, UUID columnUUID, UUID userUUID, String taskName, String taskDesc,
            String taskType, String dueDate) {
        return addTask(projectUUID, columnUUID, userUUID, taskName, taskDesc, taskType, dueDate);
    }

    /**
     * Void method that adds a new Task to the Column using the ArrayList.add()
     * method
     * 
     * @param task New project Task to be added to columnTasks
     */
    private boolean addTask(UUID projectUUID, UUID columnUUID, UUID userUUID, String taskName, String taskDesc,
            String taskType, String dueDate) {
        if (/* taskList.findTask(taskName) != null */ taskName == null)
            return false;
        Task newTask = new Task(projectUUID, columnUUID, taskName, taskDesc, taskType, userUUID, dueDate);
        columnTasks.add(newTask.getTaskUUID());

        // Temp code to bypass TaskList class @kuriakm
        taskList.add(newTask);
        DataWriter.saveTasks(taskList);

        //taskList.getListOfTasks().add(newTask);
        //taskList.saveTasks();
        return true;
    }

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
        columnTasks.remove(task.getTaskUUID());
        // taskList.getListOfTasks().remove(task);
        // Temp code to bypass TaskList class @kuriakm
        taskList.remove(task);
        DataWriter.saveTasks(taskList);
        return true;
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

    public ArrayList<Task> uuidToTasks() {
        ArrayList<Task> uuidToColumnTask = new ArrayList<>();
        for (UUID uuid : columnTasks)
            uuidToColumnTask.add(findTask(uuid));
        return uuidToColumnTask;
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

    //TODO: Reimplement these functions after testing @kuriakm
    /* 
    public Task findTask(String taskName) {
        for (Task task : taskList.getListOfTasks()) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    public Task findTask(UUID taskUUID) {
        return taskList.findTask(taskUUID);
    } */

    //TODO: Modify method after testing @kuriakm
    public Task findTask(UUID taskUUID) {
        for (Task task : taskList) {
            if (task.getTaskUUID().equals(taskUUID)) {
                return task;
            }
        }
        return null;
    }

    //TODO: Modify method after testing @kuriakm
    public Task findTask(String taskName) {
        for (Task task : taskList) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    //TODO: Remove method after testing @kuriakm
    public void setTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty() || taskList == null)
            return;
        else
            this.taskList = taskList;
    }

    //TODO: Remove method after testing @kuriakm
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String toString() {
        String tasksToString = "";
        for (Task task : uuidToTasks())
            tasksToString = tasksToString + "- " + task.getTaskName() + "\n";
        return "--- " + this.columnName + " ---\n" + (tasksToString == null ? "" : tasksToString);
    }
}