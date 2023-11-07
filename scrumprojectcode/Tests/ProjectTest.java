package scrumprojectcode.Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import scrumprojectcode.Column;
import scrumprojectcode.Project;
import scrumprojectcode.User;

import java.util.ArrayList;
import java.util.UUID;

public class ProjectTest {

    private Project project;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        project = new Project("Test Project");
        user1 = new User("User1", null, null, null, null, null, null);
        user2 = new User("User2", null, null, null, null, null, null);
    }

    @Test
    public void testAddColumn() {
        assertTrue(project.facadeAddColumn("Column1"));
        assertFalse(project.facadeAddColumn("Column1")); // Should return false for adding the same column
    }

    @Test
    public void testRemoveColumn() {
        project.facadeAddColumn("Column1");
        assertTrue(project.facadeRemoveColumn("Column1"));
        assertFalse(project.facadeRemoveColumn("NonExistentColumn")); // Should return false for removing non-existent column
    }

    @Test
    public void testMoveTask() {
        project.facadeAddColumn("Column1");
        project.facadeAddColumn("Column2");
        assertTrue(project.facadeMoveTask("Column1", "Column2", "Task1"));
        assertFalse(project.facadeMoveTask("Column1", "NonExistentColumn", "Task1")); // Should return false for non-existent target column
        assertFalse(project.facadeMoveTask("NonExistentColumn", "Column2", "Task1")); // Should return false for non-existent source column
    }

    @Test
    public void testFindColumn() {
        project.facadeAddColumn("Column1");
        project.facadeAddColumn("Column2");
        Column column = project.findColumn("Column1");
        assertNotNull(column);
        assertEquals("Column1", column.getColumnName());
        assertNull(project.findColumn("NonExistentColumn")); // Should return null for a non-existent column
    }

    @Test
    public void testAddUser() {
        assertTrue(project.addUser(user1));
        assertTrue(project.addUser(user2));
        assertFalse(project.addUser(null)); // Should return false for adding null user
    }

    @Test
    public void testRemoveUser() {
        project.addUser(user1);
        project.addUser(user2);
        assertTrue(project.removeUser(user1));
        assertFalse(project.removeUser(null)); // Should return false for removing null user
    }

    @Test
    public void testGetAssignedUsers() {
        project.addUser(user1);
        project.addUser(user2);
        ArrayList<User> assignedUsers = project.getAssignedUsers();
        assertEquals(2, assignedUsers.size());
        assertTrue(assignedUsers.contains(user1));
        assertTrue(assignedUsers.contains(user2));
    }

    @Test
    public void testGetProjectUUID() {
        UUID projectUUID = project.getProjectUUID();
        assertNotNull(projectUUID);
    }

    @Test
    public void testGetListOfColumns() {
        project.facadeAddColumn("Column1");
        project.facadeAddColumn("Column2");
        ArrayList<Column> listOfColumns = project.getListOfColumns();
        assertEquals(2, listOfColumns.size());
        assertEquals("Column1", listOfColumns.get(0).getColumnName());
        assertEquals("Column2", listOfColumns.get(1).getColumnName());
    }

    @Test
    public void testToString() {
        project.facadeAddColumn("Column1");
        project.facadeAddColumn("Column2");
        String projectString = project.toString();
        assertTrue(projectString.contains("[Test Project]:"));
        assertTrue(projectString.contains("--- Column1 ---"));
        assertTrue(projectString.contains("--- Column2 ---"));
    }

    @Test
    public void testGetProjectName() {
        assertEquals("Test Project", project.getProjectName());
    }

    @Test
    public void testSetProjectName() {
        project.setProjectName("Updated Project Name");
        assertEquals("Updated Project Name", project.getProjectName());
    }

    @Test
    public void testDisplayColumnTasks() {
        Column column1 = project.findColumn("Column1");
        assertNotNull(column1);
        column1.addTask(null, null, null, "Task1", null, null, null);
        column1.addTask(null, null, null, "Task2", null, null, null);
        String columnTasksString = project.displayColumnTasks(column1);
        assertTrue(columnTasksString.contains("- Task1"));
        assertTrue(columnTasksString.contains("- Task2"));
    }

    @Test
    public void testAddAssignedUsers() {
        project.addUser(user1);
        project.addUser(user2);
        ArrayList<UUID> userUUIDs = project.getAssignedUsersUUID();
        assertEquals(2, userUUIDs.size());
        assertTrue(userUUIDs.contains(user1.getUserUUID()));
        assertTrue(userUUIDs.contains(user2.getUserUUID()));
    }

    @Test
    public void testGetAssignedUsersUUID() {
        project.addUser(user1);
        project.addUser(user2);
        ArrayList<UUID> userUUIDs = project.getAssignedUsersUUID();
        assertEquals(2, userUUIDs.size());
        assertTrue(userUUIDs.contains(user1.getUserUUID()));
        assertTrue(userUUIDs.contains(user2.getUserUUID()));
    }

    @Test
    public void testGetAssignedUsersUUIDEmpty() {
        ArrayList<UUID> userUUIDs = project.getAssignedUsersUUID();
        assertTrue(userUUIDs.isEmpty());
    }

    @Test
    public void testGenerateUUID() {
        UUID uuid = project.generateUUID();
        assertNotNull(uuid);
    }

    @Test
    public void testFacadePrintProject() {
        project.facadeAddColumn("Column1");
        project.facadeAddColumn("Column2");
        String projectString = project.facadePrintProject();
        assertTrue(projectString.contains("[Test Project]:"));
        assertTrue(projectString.contains("--- Column1 ---"));
        assertTrue(projectString.contains("--- Column2 ---"));
    }

}

