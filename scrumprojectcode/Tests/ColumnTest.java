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
import scrumprojectcode.DataWriter;
import scrumprojectcode.Project;
import scrumprojectcode.ProjectList;
import scrumprojectcode.Task;
import scrumprojectcode.TaskList;
import scrumprojectcode.User;
import scrumprojectcode.UserList;

public class ColumnTest {

    private static UserList userList = UserList.getInstance();
    private static ProjectList projectList = ProjectList.getInstance();
    private static TaskList taskList = TaskList.getInstance();

    private ArrayList<User> testUsers = userList.getListOfUsers();
    private ArrayList<Project> testProjects = projectList.getListOfProjects();
    private ArrayList<Task> testTasks = taskList.getListOfTasks();

    @BeforeEach
    public void setup() {
        UserList.getInstance().getListOfUsers().clear();
        ProjectList.getInstance().getListOfProjects().clear();
        TaskList.getInstance().getListOfTasks().clear();

        Project project = new Project("project");
        testProjects.add(project);

        User testUser = new User("tester", "user", "testUser", "password", "tester@email", "0000000000", "user");
        testUsers.add(testUser);

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

    //test addTask: boolean
    @Test
    public void testAddOneTask(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        assertEquals(true, column.facadeAddTask(projectUuid, columnUuid, userUuid, "testTask", "test adding tasks", "test", "110823"));
        }

    @Test
    public void testAddEmptyTask(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        assertEquals(true, column.facadeAddTask(projectUuid, columnUuid, userUuid, "", "", "", ""));
    }

    @Test
    public void testAddNullTask(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        assertEquals(false, column.facadeAddTask(null, null, null, null, null, null, null));
    }

    @Test
    public void testAddIdenticalTaskAsTaskInSameColumn(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        //Column otherColumn = new Column("otherColumn", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        //UUID otherColumnUuid = otherColumn.getColumnUUID();
        Task task = new Task(projectUuid, columnUuid, "task", "desc", "task", userUuid, "110823");
        testTasks.add(task);
        DataWriter.saveTasks(testTasks);
        //Task taskInOtherColumn = new Task(projectUuid, otherColumnUuid, "task", "desc", "task", userUuid, "110823");
        assertEquals(false, column.facadeAddTask(projectUuid, columnUuid, userUuid, "task", "desc", "task", "110823"));
    }
    
    @Test
    public void testAddIdenticalTaskAsTaskInOtherColumn(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        Column otherColumn = new Column("otherColumn", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        UUID otherColumnUuid = otherColumn.getColumnUUID();
        Task taskInOtherColumn = new Task(projectUuid, otherColumnUuid, "task", "desc", "task", userUuid, "110823");
        testTasks.add(taskInOtherColumn);
        assertEquals(true, column.facadeAddTask(projectUuid, columnUuid, userUuid, "task", "desc", "task", "110823"));
    }

    //test removeTask
    @Test
    public void testRemoveExistingTask(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        Task task = new Task(projectUuid, columnUuid, "task", "desc", "task", userUuid, "110823");
        testTasks.add(task);
        DataWriter.saveTasks(testTasks);
        assertEquals(true, column.facadeRemoveTask(task.getTaskName()));
    }

    @Test
    public void testRemoveDneTask(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        Task task = new Task(projectUuid, columnUuid, "task", "desc", "task", userUuid, "110823");
        testTasks.add(task);
        DataWriter.saveTasks(testTasks);
        assertEquals(false, column.facadeRemoveTask("random task"));
    }

    @Test
    public void testRemoveNullTask(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        Task task = new Task(projectUuid, columnUuid, "task", "desc", "task", userUuid, "110823");
        testTasks.add(task);
        DataWriter.saveTasks(testTasks);
        assertEquals(false, column.facadeRemoveTask(null));
    }

    @Test
    public void testRemoveOneOfTwoIDTasks(){
        UUID projectUuid = testProjects.get(0).getProjectUUID();
        UUID userUuid = testUsers.get(0).getUserUUID();
        Column column = new Column("column", projectUuid);
        UUID columnUuid = column.getColumnUUID();
        Task task = new Task(projectUuid, columnUuid, "task", "desc", "task", userUuid, "110823");
        Task task2 = new Task(projectUuid, columnUuid, "task", "desc", "task", userUuid, "110823");
        testTasks.add(task);
        testTasks.add(task2);
        DataWriter.saveTasks(testTasks);
        assertEquals(true, column.facadeRemoveTask("task"));
    }
}
