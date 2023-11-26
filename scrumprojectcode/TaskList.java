package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class TaskList {
    private static TaskList taskList = null;
    private static ArrayList<Task> listOfTasks;

    private TaskList() {
        listOfTasks = (DataLoader.loadTasks() == null ? new ArrayList<>() : DataLoader.loadTasks());
    }

    private TaskList(ArrayList<Task> tasks) {
        listOfTasks = tasks;
    }

    public static TaskList getInstance(ArrayList<Task> tasks) { // why is there a constructor?
        if (taskList == null)
            taskList = new TaskList(tasks);
        return taskList;
    }

    public static TaskList getInstance() {
        if (taskList == null)
            taskList = new TaskList();
        return taskList;
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    public Task findTask(UUID uuid) { // TODO: remember to change task projects
        for (Task task : listOfTasks) {
            if (task.getTaskUUID().equals(uuid)) {
                return task;
            }
        }
        return null;
    }

    public Task findTask(String taskName) { // Takes in a String value taskName and finds a Task with the same taskName
                                            // @kuriakm
        for (Task task : listOfTasks) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    public void saveTasks() { // is this all we have to do for this method?
        DataWriter.saveTasks(listOfTasks);
    }

    public String toString() {
        String toString = "";
        for (Task task : listOfTasks)
            toString = toString + task + "\n\n";
        return toString;
    }
}
