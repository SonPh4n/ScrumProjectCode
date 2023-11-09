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
     * properly
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
     * "N/A"
     */
    @Test
    void testConstructorWithNullDates() {
        Sprint nullStartDate = new Sprint(null, end, taskList);
        assertEquals("N/A", nullStartDate.getStartDate());

        Sprint nullEndDate = new Sprint(start, null, taskList);
        assertEquals("N/A", nullEndDate.getEndDate());
    }

    /*
     * Test Sprint(String startDate, String endDate, ArrayList<Task>
     * tasksToComplete) with incorrect date formats
     * 
     * Should return true as the Sprint should set these dates to
     * "N/A"
     */
    @Test
    void testConstructorWithIncorrectDateFormats() { // TODO: Add pre-check in Sprint to set incorrect dates to "N/A" or
                                                     // the correct format @kuriakm
        Sprint incorrectStartFormat = new Sprint("2023/11/09", end, taskList);
        assertEquals("N/A", incorrectStartFormat.getStartDate());

        Sprint incorrectEndFormat = new Sprint(start, "21/11/2023", taskList);
        assertEquals("N/A", incorrectEndFormat.getEndDate());
    }

    /*
     * Test Sprint(String startDate, String endDate, ArrayList<Task>
     * tasksToComplete) with incorrect dates
     * 
     * Should return true as the Sprint should set these dates to
     * "N/A"
     */
    @Test
    void testConstructorWithIncorrectDates() { // TODO: Add pre-check in Sprint to set incorrect dates to "N/A" @kuriakm
        Sprint incorrectStartDate = new Sprint("11/42/2023", end, taskList);
        assertEquals("N/A", incorrectStartDate.getStartDate());

        Sprint incorrectEndDate = new Sprint(start, "13/21/2023", taskList);
        assertEquals("N/A", incorrectEndDate.getEndDate());
    }

    /*
     * Test Sprint(String startDate, String endDate, ArrayList<Task>
     * tasksToComplete) with null ArrayList<Task>
     * 
     * Should return true as the Sprint should replace this null object with an
     * empty ArrayList
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
     * rest
     */
    @Test
    void testConstructorWithNullTasks() {
        Task nullTask = null;
        taskList.add(nullTask);
        taskList.add(nullTask);
        Sprint nullTasks = new Sprint(start, end, taskList);

        for (Task task : nullTasks.getTasksToComplete()) // TODO: Add pre-check in Sprint to remove null values in
                                                         // tasksToComplete @kuriakm
            assertNotEquals(nullTask, task);
    }

    /**
     * Test addNewTask(Task task) with normal values
     * 
     * Should return true if the task was added to tasksToComplete
     */
    @Test
    void testAddNewTask() {
        Task newTask = new Task(UUID.fromString("96b912d1-d15e-4a36-8da6-8092757cc006"),
                UUID.fromString("dae69833-6749-4549-b2e6-4ad363328afa"), "Task added to Sprint",
                "This was a task added to Sprint", "feature", UUID.fromString("f78044ec-70eb-42d0-851e-ed498a4e5049"),
                "11/23/2023");
        sprint.addNewTask(newTask);

        assertTrue(sprint.getTasksToComplete().contains(newTask));
    }

    /**
     * Test addNewTask(Task task) with duplicate values
     * 
     * Should return true as the duplicate task should not be added to
     * tasksToComplete
     */
    @Test
    void testAddDuplicateTask() {
        Task newTask = new Task(UUID.fromString("96b912d1-d15e-4a36-8da6-8092757cc006"),
                UUID.fromString("dae69833-6749-4549-b2e6-4ad363328afa"), "Task added to Sprint",
                "This was a task added to Sprint", "feature", UUID.fromString("f78044ec-70eb-42d0-851e-ed498a4e5049"),
                "11/23/2023");
        Task newTaskTwo = new Task(UUID.fromString("96b912d1-d15e-4a36-8da6-8092757cc006"),
                UUID.fromString("dae69833-6749-4549-b2e6-4ad363328afa"), "Task added to Sprint",
                "This was a task added to Sprint", "feature", UUID.fromString("f78044ec-70eb-42d0-851e-ed498a4e5049"),
                "11/23/2023");
        sprint.addNewTask(newTask);

        Sprint sprintTwo = new Sprint(start, end, sprint.getTasksToComplete());
        sprintTwo.addNewTask(newTaskTwo);

        assertEquals(sprint.getTasksToComplete().size(), sprintTwo.getTasksToComplete().size());
    }

    /**
     * Test addNewTask(Task task) with null Tasks
     * 
     * Should return true as null tasks should not be added to tasksToComplete
     */
    @Test
    void testAddNewNullTask() { // TODO: Add pre-check in Sprint to make sure null tasks are not added to
                                // tasksToComplete @kuriakm
        Task newTask = null;
        sprint.addNewTask(newTask);

        assertTrue(!sprint.getTasksToComplete().contains(newTask));
    }

    /**
     * Test removeTask(Task task) with normal values
     * 
     * Should return true if the task was removed successfully
     */
    @Test
    void testRemoveTask() {
        Task newTask = new Task(UUID.fromString("96b912d1-d15e-4a36-8da6-8092757cc006"),
                UUID.fromString("dae69833-6749-4549-b2e6-4ad363328afa"), "Task added to Sprint",
                "This was a task added to Sprint", "feature", UUID.fromString("f78044ec-70eb-42d0-851e-ed498a4e5049"),
                "11/23/2023");
        sprint.addNewTask(newTask);

        assertTrue(sprint.getTasksToComplete().contains(newTask));

        sprint.removeTask(newTask);

        assertTrue(!sprint.getTasksToComplete().contains(newTask));
    }

    /**
     * Test removeTask(Task task) for a nonexistent Task
     * Should return true as no task was removed from tasksToComplete
     */
    @Test
    void testRemoveNonexistingTask() {
        Task newTask = new Task(UUID.fromString("96b912d1-d15e-4a36-8da6-8092757cc006"),
                UUID.fromString("dae69833-6749-4549-b2e6-4ad363328afa"), "Task added to Sprint",
                "This was a task added to Sprint", "feature", UUID.fromString("f78044ec-70eb-42d0-851e-ed498a4e5049"),
                "11/23/2023");
        sprint.removeTask(newTask);

        assertTrue(!sprint.getTasksToComplete().contains(newTask));
    }

    /**
     * Test toString()
     * 
     * Should return true if sprint.toString() is equal to expectedString
     */
    @Test
    void testToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sprint Start Date: ").append(start).append("\n");
        sb.append("Sprint End Date: ").append(end).append("\n");
        sb.append("Tasks to Complete:\n");

        for (Task task : taskList) {
            sb.append("Task Name: ").append(task.getTaskName()).append("\n");
            sb.append("Task Description: ").append(task.getTaskDescription()).append("\n");
            sb.append("Task Due Date: ").append(task.getDueDate()).append("\n");
            sb.append("Task Creation Date: ").append(task.getCreationDate()).append("\n\n");
        }

        String expectedString = sb.toString();
        assertEquals(expectedString, sprint.toString());

    }
}
