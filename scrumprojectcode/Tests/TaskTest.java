package scrumprojectcode.Tests;

import org.junit.jupiter.api.Test;

import scrumprojectcode.Task;
import scrumprojectcode.User;
import scrumprojectcode.UserList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

/**
 * This class contains JUnit tests for the Task class.
 * It tests the constructor and the getTaskName method.
 * The constructor is tested for creating a new task with the correct attributes.
 * The getTaskName method is tested for returning the correct task name.
 */

public class TaskTest {

    @Test
    public void testConstructor() {
        UUID projectID = UUID.randomUUID();
        UUID columnID = UUID.randomUUID();
        UUID userUUID = UUID.randomUUID();
        String taskTitle = "Test Task";
        String taskDesc = "This is a test task";
        String taskType = "Test Type";
        String taskDueDate = "2022-12-31";

        Task task = new Task(projectID, columnID, taskTitle, taskDesc, taskType, userUUID, taskDueDate);

        assertEquals(taskTitle, task.getTaskName());
        assertEquals(taskType, task.getTaskType());
        assertEquals(userUUID, task.getAssignedUsersUUID());
        assertEquals(taskDueDate, task.getDueDate());
        assertEquals(projectID, task.getProjectUUID());
        assertEquals(columnID, task.getColumnUUID());
    }

    @Test
    public void testGetTaskName() {
        UUID projectID = UUID.randomUUID();
        UUID columnID = UUID.randomUUID();
        UUID userUUID = UUID.randomUUID();
        String taskTitle = "Test Task";
        String taskDesc = "This is a test task";
        String taskType = "Test Type";
        String taskDueDate = "2022-12-31";

        Task task = new Task(projectID, columnID, taskTitle, taskDesc, taskType, userUUID, taskDueDate);

        assertEquals(taskTitle, task.getTaskName());
    }
}
// Path: scrumprojectcode/Tests/UserListTest.java