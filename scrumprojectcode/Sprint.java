package scrumprojectcode;

import java.util.ArrayList;

/**
 * The Sprint class represents a sprint in a Scrum project. A sprint has a start
 * date, an end date, and a list of tasks to complete.
 */
public class Sprint {
    private String startDate;
    private String endDate;
    private ArrayList<Task> tasksToComplete;

    /**
     * Constructs a new Sprint object with the given start date, end date, and list
     * of tasks to complete.
     *
     * @param startDate       the start date of the sprint
     * 
     * @param endDate         the end date of the sprint
     * 
     * @param tasksToComplete the list of tasks to complete in the sprint
     */
    public Sprint(String startDate, String endDate, ArrayList<Task> tasksToComplete) {
        setStartDate(startDate);
        setEndDate(endDate);
        setTasksToComplete(tasksToComplete);
    }

    /**
     * Adds a new task to the list of tasks to complete in the sprint.
     *
     * @param task the task to add
     */
    public void addNewTask(Task task) {
        tasksToComplete.add(task);
    }

    /**
     * Removes a task from the list of tasks to complete in the sprint.
     *
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        tasksToComplete.remove(task);
    }

    // Added setters and getters for attributes to make testing easier @kuriakm

    /**
     * String method that returns the current value of startDate
     * 
     * @return String representation of the sprint's start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Void method that initializes this.startDate with the param startDate
     * 
     * @param startDate String that is set to this.startDate
     */
    public void setStartDate(String startDate) {
        if (startDate != null)
            this.startDate = startDate;
        else
            this.startDate = "N/A";
    }

    /**
     * String method that returns the current value of endDate
     * 
     * @return String representation of the sprint's end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Void method that initializes this.endDate with the param endDate
     * 
     * @param endDate String that is set to this.endDate
     */
    public void setEndDate(String endDate) {
        if (endDate != null)
            this.endDate = endDate;
        else
            this.endDate = "N/A";
    }

    /**
     * ArrayList<Task> method that returns the current value of tasksToComplete
     * 
     * @return ArrayList<Task> of current tasks in the sprint
     */
    public ArrayList<Task> getTasksToComplete() {
        return tasksToComplete;
    }

    /**
     * Void method that initializes this.tasksToComplete with the param
     * tasksToComplete
     * 
     * @param tasksToComplete ArrayList<Task> that is set to this.tasksToComplete
     */
    public void setTasksToComplete(ArrayList<Task> tasksToComplete) {
        if (tasksToComplete != null)
            this.tasksToComplete = tasksToComplete;
        else
            this.tasksToComplete = new ArrayList<>();
    }

    /**
     * Returns a string representation of the sprint, including its start date, end
     * date, and list of tasks to complete.
     *
     * @return a string representation of the sprint
     */
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
