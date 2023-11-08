package scrumprojectcode.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
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

public class DataWriterTest {
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

        DataWriter.saveUsers(userList.getListOfUsers());
        DataWriter.saveProjects(projectList.getListOfProjects());
        DataWriter.saveTasks(taskList.getListOfTasks());
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

    //test writing users
    @Test
    void testWritingZeroUsers(){
        DataWriter.saveUsers(testUsers);
        testUsers = DataLoader.loadUsers();
        assertEquals(0, testUsers.size());
    }

    @Test
    void testWritingOneUser(){
        User nancy = new User("nancy", "m", "mnancy", "1234", "nancym@email", "1111111111", "user");
        testUsers.add(nancy);
        DataWriter.saveUsers(testUsers);
        assertEquals("mnancy", DataLoader.loadUsers().get(0).getUsername());
    }

    @Test 
    void testWritingThreeUsers(){
        User nancy = new User("nancy", "m", "mnancy", "1234", "nancym@email", "1111111111", "user");
        User mark = new User("mark", "l", "lmark", "4321", "markl@email", "2222222222", "user");
        User johnny = new User("johnny", "s", "sjohnny", "2314", "johnnys@email", "3333333333", "user");

        testUsers.add(nancy);
        testUsers.add(mark);
        testUsers.add(johnny);
        DataWriter.saveUsers(testUsers);
        assertEquals("sjohnny", DataLoader.loadUsers().get(2).getUsername());
    }

    @Test
    void testWritingEmptyUser(){
        User empty = new User("", "", "", "", "", "", "");
        testUsers.add(empty);
        DataWriter.saveUsers(testUsers);
        assertEquals("", DataLoader.loadUsers().get(0).getUsername());
    }
    
    @Test
    void testWritingNullUser(){
        User nullUser = null;
        testUsers.add(nullUser);
        DataWriter.saveUsers(testUsers);
        assertEquals(null, DataLoader.loadUsers().get(0).getUsername());
    }

    //test writing projects
    @Test
    void testWritingZeroProjects(){
        DataWriter.saveProjects(testProjects);
        testProjects = DataLoader.loadProjects();
        assertEquals(0, testProjects.size());
    }

    @Test
    void testWritingOneProject(){
        Project markProject = new Project("projectMark");
        testProjects.add(markProject);
        DataWriter.saveProjects(testProjects);
        assertEquals("projectMark", DataLoader.loadProjects().get(0).getProjectName());
    }

    @Test 
    void testWritingThreeProjects(){
        Project nancyProject = new Project("projectNancy");
        Project markProject = new Project("projectMark");
        Project johnnyProject = new Project("projectJohnny");

        testProjects.add(nancyProject);
        testProjects.add(markProject);
        testProjects.add(johnnyProject);
        DataWriter.saveProjects(testProjects);
        assertEquals("projectJohnny", DataLoader.loadProjects().get(2).getProjectName());
    }

    @Test
    void testWritingEmptyProject(){
        Project emptyProject = new Project("");
        testProjects.add(emptyProject);
        DataWriter.saveProjects(testProjects);
        assertEquals("", DataLoader.loadProjects().get(0).getProjectName());
    }
    
    @Test
    void testWritingNullProject(){
        Project nullProject = null;
        testProjects.add(nullProject);
        DataWriter.saveProjects(testProjects);
        assertEquals(null, DataLoader.loadProjects().get(0).getProjectName());
    }

    //test writing tasks
    @Test
    void testWritingZeroTasks(){
        DataWriter.saveTasks(testTasks);
        testTasks = DataLoader.loadTasks();
        assertEquals(0, testTasks.size());
    }

    @Test
    void testWritingOneTask(){
        Project nctProject = new Project("nct");
        Column nctColumn = new Column("nctColumn", nctProject.getProjectUUID());
        User mark = new User("mark", "l", "lmark", "4321", "markl@email", "2222222222", "user");
        testProjects.add(nctProject);
        testUsers.add(mark);
        Task handshakeTask = new Task(nctProject.getProjectUUID(), nctColumn.getColumnUUID(), "handshakeTask", "make a secret handshake", "task", mark.getUserUUID(), "110823");
        testTasks.add(handshakeTask);
        DataWriter.saveTasks(testTasks);
        assertEquals("handshakeTask", DataLoader.loadTasks().get(0).getTaskName());
    }

    @Test 
    void testWritingThreeTasks(){
        Project nctProject = new Project("nct");
        Column nctColumn = new Column("nctColumn", nctProject.getProjectUUID());
        User mark = new User("mark", "l", "lmark", "4321", "markl@email", "2222222222", "user");
        testProjects.add(nctProject);
        testUsers.add(mark);
        Task makeFilm = new Task(nctProject.getProjectUUID(), nctColumn.getColumnUUID(), "makeFilmTask", "make a film", "task", mark.getUserUUID(), "110823");
        Task handshakeTask = new Task(nctProject.getProjectUUID(), nctColumn.getColumnUUID(), "handshakeTask", "make a secret handshake", "task", mark.getUserUUID(), "110823");
        Task switchBodies = new Task(nctProject.getProjectUUID(), nctColumn.getColumnUUID(), "switchBodiesTask", "swap mind and body", "task", mark.getUserUUID(), "110823");

        testTasks.add(makeFilm);
        testTasks.add(handshakeTask);
        testTasks.add(switchBodies);
        DataWriter.saveTasks(testTasks);
        assertEquals("switchBodiesTask", DataLoader.loadTasks().get(2).getTaskName());
    }

    @Test
    void testWritingEmptyTask(){
        Project nctProject = new Project("nct");
        Column nctColumn = new Column("nctColumn", nctProject.getProjectUUID());
        User mark = new User("mark", "l", "lmark", "4321", "markl@email", "2222222222", "user");
        testProjects.add(nctProject);
        testUsers.add(mark);

        Task emptyTask = new Task(nctProject.getProjectUUID(), nctColumn.getColumnUUID(), "", "", "", mark.getUserUUID(), "");
        testTasks.add(emptyTask);
        DataWriter.saveTasks(testTasks);
        assertEquals("", DataLoader.loadTasks().get(0).getTaskName());
    }
    
    @Test
    void testWritingNullTask(){
        Task nullTask = null;
        testTasks.add(nullTask);
        DataWriter.saveTasks(testTasks);
        assertEquals(null, DataLoader.loadTasks().get(0).getTaskName());
    }
}
