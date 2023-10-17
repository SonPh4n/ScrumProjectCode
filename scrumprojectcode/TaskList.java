package scrumprojectcode;

import java.util.ArrayList;

public class TaskList {
        priate static TaskList taskList = null;
    private static ArrayList<Task> listO

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
}
