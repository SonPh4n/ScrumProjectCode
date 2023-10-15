package scrumprojectcode;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    private TaskList(ArrayList<Task> tasks)
    {
        this.listOfTasks = tasks;
    }

    public static TaskList getInstance(ArrayList<Task> tasks)
    {
        return new TaskList(tasks);
    }
}
