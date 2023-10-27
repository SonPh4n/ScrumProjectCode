package scrumprojectcode;
import java.util.ArrayList;

public class Sprint {
    private String startDate;
    private String endDate;
    private ArrayList<Task> tasksToComplete;

    public Sprint(String startDate, String endDate, ArrayList<Task> tasksToComplete) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasksToComplete = tasksToComplete;
    }

    public void addNewTask(Task task) {
        tasksToComplete.add(task);
    }

    public void removeTask(Task task) {
        tasksToComplete.remove(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sprint Start Date: ").append(startDate).append("\n");
        sb.append("Sprint End Date: ").append(endDate).append("\n");
        sb.append("Tasks to Complete:\n");

        for (Task task : tasksToComplete) {
            sb.append("Task Name: ").append(task.getTaskName()).append("\n");
            sb.append("Task Description: ").append(task.getTaskDescription()).append("\n");
            sb.append("Task Due Date: ").append(task.getDueDate()).append("\n");
            sb.append("Task Creation Date: ").append(task.getCreationDate()).append("\n\n");
        }

        return sb.toString();
    }
}
