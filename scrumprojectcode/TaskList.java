package scrumprojectcode;

import java.util.ArrayList;

public class TaskList {
    private static TaskList taskList = null;
    private static ArrayList<Task> listOfTasks;

    private TaskList() {
        listOfTasks = DataLoader.loadTasks();
    }

    private TaskList(ArrayList<Task> tasks) {
        listOfTasks = tasks;
    }

    public static TaskList getInstance(ArrayList<Task> tasks) {
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

    public boolean findTask(String projectName, String taskName) { // TODO: remember to change task projects
        for (Task task : listOfTasks) {
            if (task.getProjectName().equals(projectName) && task.getTaskName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    public void saveTasks() { // is this all we have to do for this method?
        DataWriter.saveTasks(listOfTasks);
    }
}
