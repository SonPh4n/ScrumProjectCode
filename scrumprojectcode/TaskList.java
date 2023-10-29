package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class TaskList {
    private static TaskList taskList = null;
    private static ArrayList<Task> listOfTasks;

    private TaskList() {
        listOfTasks = DataLoader.loadTasks();
    }

    private TaskList(ArrayList<Task> tasks) {
        listOfTasks = tasks;
    }

    public static TaskList getInstance(ArrayList<Task> tasks) { //why is there a constructor?
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

    public Task findTask(UUID uuid/*String projectName, String taskName*/) { // TODO: remember to change task projects
        for (Task task : listOfTasks) {
            /* if (task.getProjectName().equals(projectName) && task.getTaskName().equals(taskName)) {
                return true;
            } */
            if(task.getTaskUUID().equals(uuid)){
                return task;
            }
        }
        return null;
    }

    public void saveTasks() { // is this all we have to do for this method?
        DataWriter.saveTasks(listOfTasks);
    }
}
