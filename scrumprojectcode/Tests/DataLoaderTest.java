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

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());

        Task task = tasks.get(0);
        assertNotNull(task);
        assertNotNull(task.getTaskName());
        assertNotNull(task.getTaskDescription());
        assertNotNull(task.getTaskType());
        assertNotNull(task.getDueDate());
        assertNotNull(task.getProjectUUID());
        assertNotNull(task.getColumnUUID());
    }

    @Test
    public void testLoadUsers() {
        ArrayList<User> users = DataLoader.loadUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());

        User user = users.get(0);
        assertNotNull(user);
        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
    }

    @Test
    public void testLoadProjects() {
    ArrayList<Project> projects = DataLoader.loadProjects();

    assertNotNull(projects);
    assertFalse(projects.isEmpty());

    Project project = projects.get(0);
    assertNotNull(project);
    assertNotNull(project.getProjectName());
    assertNotNull(project.getListOfColumns()); // replaced getProjectColumns() with getListOfColumns()
    assertNotNull(project.getAssignedUsers());
    }
}