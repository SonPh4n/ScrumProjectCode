package scrumprojectcode;

import java.util.ArrayList;
import java.util.Date;

public class Sprint {
    private Date startDate;
    private Date endDate;
    private ArrayList<Task> tasksToComplete;

    public Sprint(Date startDate, Date endDate, ArrayList<Task> tasksToComplete) {
        // Constructor logic
    }

    public void addNewTask(Task task) {
        // Add a new task to the sprint
    }

    public void removeTask(Task task) {
        // Remove a task from the sprint
    }

    @Override
    public String toString() {
        // Convert sprint information to a string representation
        return super.toString();
    }
}