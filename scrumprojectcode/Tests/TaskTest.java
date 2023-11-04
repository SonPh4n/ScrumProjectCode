package scrumprojectcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

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

        assertNotNull(task);
        assertEquals(taskTitle, task.getTaskName());
        assertEquals(taskDesc, task.getTaskDescription());
        assertEquals(taskType, task.getTaskType());
        assertTrue(task.getAssignedUsers().contains(userUUID));
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