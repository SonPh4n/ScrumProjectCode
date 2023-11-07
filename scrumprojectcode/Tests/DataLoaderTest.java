package scrumprojectcode.Tests;

import org.junit.jupiter.api.Test;

import scrumprojectcode.DataLoader;
import scrumprojectcode.Project;
import scrumprojectcode.Task;
import scrumprojectcode.User;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class DataLoaderTest {

    @Test
    public void testLoadTasks() {
        ArrayList<Task> tasks = DataLoader.loadTasks();

        assertNotNull(tasks, "Test 1: Failed - The task list is null");
        assertFalse(tasks.isEmpty(), "Test 1: Failed - The task list is empty");

        Task task = tasks.get(0);
        assertNotNull(task, "Test 1: Failed - The task is null");
        assertNotNull(task.getTaskName(), "Test 1: Failed - The task name is null");
        assertNotNull(task.getTaskDescription(), "Test 1: Failed - The task description is null");
        assertNotNull(task.getTaskType(), "Test 1: Failed - The task type is null");
        assertNotNull(task.getDueDate(), "Test 1: Failed - The task due date is null");
        assertNotNull(task.getProjectUUID(), "Test 1: Failed - The task project UUID is null");
        assertNotNull(task.getColumnUUID(), "Test 1: Failed - The task column UUID is null");
        
        System.out.println("Test 1: Passed - All task attributes are not null");
    }

    @Test
    public void testLoadUsers() {
        ArrayList<User> users = DataLoader.loadUsers();

        assertNotNull(users, "Test 2: Failed - The user list is null");
        assertFalse(users.isEmpty(), "Test 2: Failed - The user list is empty");

        User user = users.get(0);
        assertNotNull(user, "Test 2: Failed - The user is null");
        assertNotNull(user.getUsername(), "Test 2: Failed - The user's username is null");
        assertNotNull(user.getPassword(), "Test 2: Failed - The user's password is null");
        
        System.out.println("Test 2: Passed - All user attributes are not null");
    }

    @Test
    public void testLoadProjects() {
        ArrayList<Project> projects = DataLoader.loadProjects();

        assertNotNull(projects, "Test 3: Failed - The project list is null");
        assertFalse(projects.isEmpty(), "Test 3: Failed - The project list is empty");

        Project project = projects.get(0);
        assertNotNull(project, "Test 3: Failed - The project is null");
        assertNotNull(project.getProjectName(), "Test 3: Failed - The project name is null");
        assertNotNull(project.getListOfColumns(), "Test 3: Failed - The project column list is null");
        assertNotNull(project.getAssignedUsers(), "Test 3: Failed - The project user list is null");
        
        System.out.println("Test 3: Passed - All project attributes are not null");
    }
}
