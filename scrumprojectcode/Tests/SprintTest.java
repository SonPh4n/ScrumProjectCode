package scrumprojectcode.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrumprojectcode.Sprint;
import scrumprojectcode.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class holds the JUnit tests for Sprint.
 * 
 * Methods tested:
 * Sprint(String startDate, String endDate, ArrayList<Task> tasksToComplete)
 * - null value(s)
 * - incorrect date(s) (format should be "MM/dd/YYYY")
 * addNewTask(Task task)
 * - null value
 * - duplicate Task
 * removeTask(Task task)
 * - null value
 * - task does not exist
 * toString()
 * 
 * @author kuriakm
 */
public class SprintTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");

    // Test Sprint(String startDate, String endDate, ArrayList<Task>
    // tasksToComplete)

    Date startDate = 

    // Test addNewTask(Task task): void
    // Test removeTask(Task task): void
    // Test toString(): String
}
