package scrumprojectcode.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrumprojectcode.Column;
import scrumprojectcode.DataLoader;
import scrumprojectcode.DataWriter;
import scrumprojectcode.Project;
import scrumprojectcode.ProjectList;
import scrumprojectcode.Task;
import scrumprojectcode.TaskList;
import scrumprojectcode.User;
import scrumprojectcode.UserList;

public class TaskListTest {

    private static UserList userList = UserList.getInstance();
    private static ProjectList projectList = ProjectList.getInstance();
    private static TaskList taskList = TaskList.getInstance();

    private ArrayList<User> testUsers = userList.getListOfUsers();
    private ArrayList<Project> testProjects = projectList.getListOfProjects();
    private ArrayList<Task> testTasks = taskList.getListOfTasks();

    @BeforeEach
    public void setup() {
        Project nctProject = new Project("nct");
        Column nctColumn = new Column("nctColumn", nctProject.getProjectUUID());
        User mark = new User("mark", "l", "lmark", "4321", "markl@email", "2222222222", "user");

        testProjects.add(nctProject);
        testUsers.add(mark);

        Task emptyTask = new Task(nctProject.getProjectUUID(), nctColumn.getColumnUUID(), "", "", "",
                mark.getUserUUID(), "");
        Task handshakeTask = new Task(nctProject.getProjectUUID(), nctColumn.getColumnUUID(), "handshakeTask",
                "make a secret handshake", "task", mark.getUserUUID(), "110823");

        testTasks.add(emptyTask);
        testTasks.add(handshakeTask);

        DataWriter.saveUsers(testUsers);
        DataWriter.saveProjects(testProjects);
        DataWriter.saveTasks(testTasks);
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getListOfUsers().clear();
        ProjectList.getInstance().getListOfProjects().clear();
        TaskList.getInstance().getListOfTasks().clear();

        DataWriter.saveUsers(userList.getListOfUsers());
        DataWriter.saveProjects(projectList.getListOfProjects());
        DataWriter.saveTasks(taskList.getListOfTasks());
    }

    // test findTask by UUID
    @Test
    public void testfindingOneTaskbyUUID() {
        assertEquals("make a secret handshake", taskList.findTask(testTasks.get(1).getTaskUUID()).getTaskDescription());
    }

    @Test
    public void testfindingemptyTaskbyUUID() {
        assertEquals("", taskList.findTask(testTasks.get(0).getTaskUUID()).getTaskDescription());
    }

    @Test
    public void testfindingNonexistentTaskbyUUID() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, taskList.findTask(uuid));
    }

    @Test
    public void testfindingNullTaskbyUUID() {
        Task nullTask = null;
        UUID uuid = null;
        assertEquals(null, taskList.findTask(uuid));
    }

    // test findTask by taskName
    @Test
    public void testfindingOneTaskbyName() {
        assertEquals("make a secret handshake", taskList.findTask(testTasks.get(1).getTaskName()).getTaskDescription());
    }

    @Test
    public void testfindingemptyTaskbyName() {
        assertEquals("", taskList.findTask(testTasks.get(0).getTaskName()).getTaskDescription());
    }

    @Test
    public void testfindingNonexistentTaskbyName() {
        String taskName = "nonexistent task";
        assertEquals(null, taskList.findTask(taskName));
    }

    @Test
    public void testfindingNullTaskbyName() {
        Task nullTask = null;
        String taskName = null;
        assertEquals(null, taskList.findTask(taskName));
    }
}
