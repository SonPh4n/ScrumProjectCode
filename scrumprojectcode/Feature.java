package scrumprojectcode;

import java.util.ArrayList;

public class Feature {
    private String taskName;
    private ArrayList<String> subTasks;

    public Feature(String taskName, ArrayList<String> subTasks) {
        this.taskName = taskName;
        this.subTasks = subTasks;
    }

    public void addSubtask(String subtask) {
        // Add a new subtask to the list
        subTasks.add(subtask);
    }

    public void removeSubtask(String subtask) {
        // Remove a subtask from the list
        subTasks.remove(subtask);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Feature: ").append(taskName).append("\n");
        stringBuilder.append("Subtasks:\n");
        for (String subtask : subTasks) {
            stringBuilder.append("- ").append(subtask).append("\n");
        }
        return stringBuilder.toString();
    }

    // Getters and setters
    public ArrayList<String> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<String> subTasks) {
        this.subTasks = subTasks;
    }
}
