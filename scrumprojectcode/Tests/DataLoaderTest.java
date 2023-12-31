package scrumprojectcode.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrumprojectcode.DataLoader;
import scrumprojectcode.Project;
import scrumprojectcode.Task;
import scrumprojectcode.User;
import scrumprojectcode.Column;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

public class DataLoaderTest {
    private ArrayList<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = DataLoader.loadTasks();
    }

    @Test
    public void testLoadTasks() {
        assertNotNull(tasks, "Test 1: Failed - The task list is null");
        assertFalse(tasks.isEmpty(), "Test 1: Failed - The task list is empty");

        for (Task task : tasks) {
            assertNotNull(task, "Test 1: Failed - A task is null");
            assertNotNull(task.getTaskName(), "Test 1: Failed - A task name is null");
            assertNotNull(task.getTaskDescription(), "Test 1: Failed - A task description is null");
            assertNotNull(task.getTaskType(), "Test 1: Failed - A task type is null");
            assertNotNull(task.getDueDate(), "Test 1: Failed - A task due date is null");
            assertNotNull(task.getProjectUUID(), "Test 1: Failed - A task project UUID is null");
            assertNotNull(task.getColumnUUID(), "Test 1: Failed - A task column UUID is null");
        }

        System.out.println("Test 1: Passed - All task attributes are not null");
    }

    @Test
    public void testLoadUsers() {
        ArrayList<User> users = DataLoader.loadUsers();

        assertNotNull(users, "Test 2: Failed - The user list is null");
        assertFalse(users.isEmpty(), "Test 2: Failed - The user list is empty");

        for (User user : users) {
            assertNotNull(user, "Test 2: Failed - A user is null");
            assertNotNull(user.getUsername(), "Test 2: Failed - A user's username is null");
            assertNotNull(user.getPassword(), "Test 2: Failed - A user's password is null");
        }

        System.out.println("Test 2: Passed - All user attributes are not null");
    }

    @Test
    public void testLoadProjectsColumns() {
        ArrayList<Project> projects = DataLoader.loadProjects();

        assertNotNull(projects, "Test: Failed - The project list is null");
        assertFalse(projects.isEmpty(), "Test: Failed - The project list is empty");

        for (Project project : projects) {
            ArrayList<Column> columns = project.getListOfColumns();

            assertNotNull(columns, "Test: Failed - The column list of a project is null");
            assertFalse(columns.isEmpty(), "Test: Failed - The column list of a project is empty");

            for (Column column : columns) {
                assertNotNull(column, "Test: Failed - A column is null");
                assertNotNull(column.getColumnUUID(), "Test: Failed - A column ID is null");
                assertNotNull(column.getColumnName(), "Test: Failed - A column title is null");
                assertNotNull(column.getColumnTasks(), "Test: Failed - A column task list is null");
                assertFalse(column.getColumnTasks().isEmpty(), "Test: Failed - A column task list is empty");

                for (Task task : column.getColumnTasks()) {
                    assertNotNull(task, "Test: Failed - A task in a column is null");
                }
            }
        }

        System.out.println("Test: Passed - All project column attributes are not null");
    }

    @Test
    public void testLoadProjectsUsers() {
        ArrayList<Project> projects = DataLoader.loadProjects();

        assertNotNull(projects, "Test: Failed - The project list is null");
        assertFalse(projects.isEmpty(), "Test: Failed - The project list is empty");

        for (Project project : projects) {
            ArrayList<UUID> users = new ArrayList<UUID>();
            for (User user : project.getAssignedUsers()) {
                users.add(user.getUserUUID());
            }

            assertNotNull(users, "Test: Failed - The user list of a project is null");
            assertFalse(users.isEmpty(), "Test: Failed - The user list of a project is empty");

            for (UUID userUUID : users) {
                assertNotNull(userUUID, "Test: Failed - A user UUID in a project is null");
            }
        }

        System.out.println("Test: Passed - All project user attributes are not null");
    }
}
