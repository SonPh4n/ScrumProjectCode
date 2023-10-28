package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class Bug extends Task {
    private User tester;
    private Priority priority;
    private ArrayList<String> reproductionSteps;

    public Bug(String taskName, Priority priority, User tester) {
        super(UUID.randomUUID(), UUID.randomUUID(), taskName);
        this.priority = priority;
        this.tester = tester;
        this.reproductionSteps = new ArrayList<>();
    }

    public String toString() {
        return "[Bug Task]\n" +
               "Task Name: " + getTaskName() + "\n" +
               "Priority: " + priority + "\n" +
               "Tester: " + tester.getUsername() + "\n" +
               "Reproduction Steps: " + reproductionSteps + "\n" +
               "Description: " + getTaskDescription() + "\n" +
               "Due Date: " + getDueDate() + "\n" +
               "Creation Date: " + getCreationDate() + "\n";
    }
}
