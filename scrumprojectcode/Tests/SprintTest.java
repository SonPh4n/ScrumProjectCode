package scrumprojectcode.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrumprojectcode.DataLoader;
import scrumprojectcode.Sprint;
import scrumprojectcode.Task;

import java.time.LocalDate;
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
    private Sprint sprint;
    private String start;
    private String end;
    private ArrayList<Task> taskList;
    private Task[] taskListToArray;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");

    /*
     * public static void main(String[] args) {
     * SprintTest st = new SprintTest();
     * st.testConstructorWithNullStartDate();
     * }
     */

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.now();
        start = formatter.format(startDate);
        LocalDate endDate = startDate.plusDays(12);
        end = formatter.format(endDate);

        taskList = DataLoader.loadTasks();
        taskListToArray = taskList.toArray(new Task[taskList.size()]);

        sprint = new Sprint(start, end, taskList);
    }

    /**
     * Test Sprint(String startDate, String endDate, ArrayList<Task>
     * tasksToComplete) with normal values
     * 
     * Everything should return true if the class methods are working
     * properly @kuriakm
     */
    @Test
    void testConstructor() {
        assertNotNull(sprint);
        assertEquals(start, sprint.getStartDate());
        assertEquals(end, sprint.getEndDate());
        assertArrayEquals(taskListToArray, sprint.getTasksToComplete().toArray(new Task[taskList.size()]));
    }

    /*
     * Test Sprint(String startDate, String endDate, ArrayList<Task>
     * tasksToComplete) with null date values
     * 
     * Should return true as the Sprint should replace these null values with
     * "N/A" @kuriakm
     */
    @Test
    void testConstructorWithNullDates() {
        Sprint nullStartDate = new Sprint(null, end, taskList);
        assertNotNull(nullStartDate.getStartDate());

        Sprint nullEndDate = new Sprint(start, null, taskList);
        assertNotNull(nullEndDate.getEndDate());
    }

    /*
     * Test Sprint(String startDate, String endDate, ArrayList<Task>
     * tasksToComplete) with null ArrayList<Task>
     * 
     * Should return true as the Sprint should replace this null object with an
     * empty ArrayList @kuriakm
     */
    @Test
    void testConstructorWithNullTasksToComplete() {
        Sprint nullTasksToComplete = new Sprint(start, end, null);
        assertNotNull(nullTasksToComplete.getTasksToComplete());
    }

    /*
     * Test Sprint(String startDate, String endDate, ArrayList<Task>
     * tasksToComplete) with null Tasks in tasksToComplete
     * 
     * Should return true as the Sprint should remove the null objects and keep the
     * rest @kuriakm
     */
    @Test
    void testConstructorWithNullTasks() {
        Task nullTask = null;
        taskList.add(nullTask);
        taskList.add(nullTask);
        Sprint nullTasks = new Sprint(start, end, taskList);

        for (Task task : nullTasks.getTasksToComplete())
            assertNotEquals(nullTask, task);
    }

    /**
     * Test addNewTask(Task task): void
     */
    // Test removeTask(Task task): void
    // Test toString(): String
}
