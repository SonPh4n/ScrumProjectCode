package scrumprojectcode.Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import scrumprojectcode.Project;
import scrumprojectcode.ProjectList;

import java.util.ArrayList;
import java.util.UUID;

public class ProjectListTest {

    private ProjectList projectList;
    private ArrayList<Project> projects;
    private Project project1;
    private Project project2;

    @Before
    public void setUp() {
        projects = new ArrayList<>();
        project1 = new Project("Project1");
        project2 = new Project("Project2");
        projects.add(project1);
        projects.add(project2);
        projectList = ProjectList.getInstance(projects);
    }

    @Test
    public void testGetInstance() {
        ProjectList instance = ProjectList.getInstance();
        assertNotNull(instance);
        assertSame(projectList, instance);
    }

    @Test
    public void testGetInstanceWithProjects() {
        ProjectList instance = ProjectList.getInstance(projects);
        assertNotNull(instance);
        assertSame(projectList, instance);
    }

    @Test
    public void testGetListOfProjects() {
        ArrayList<Project> projectListProjects = projectList.getListOfProjects();
        assertEquals(2, projectListProjects.size());
        assertTrue(projectListProjects.contains(project1));
        assertTrue(projectListProjects.contains(project2));
    }

    @Test
    public void testFindProjectByUUID() {
        Project foundProject = projectList.findProject(project1.getProjectUUID());
        assertNotNull(foundProject);
        assertEquals(project1.getProjectUUID(), foundProject.getProjectUUID());
    }

    @Test
    public void testFindProjectByUUIDNonExistent() {
        UUID nonExistentUUID = UUID.randomUUID();
        Project foundProject = projectList.findProject(nonExistentUUID);
        assertNull(foundProject);
    }

    @Test
    public void testFindProjectByName() {
        Project foundProject = projectList.findProject("Project2");
        assertNotNull(foundProject);
        assertEquals("Project2", foundProject.getProjectName());
    }

    @Test
    public void testFindProjectByNameNonExistent() {
        Project foundProject = projectList.findProject("NonExistentProject");
        assertNull(foundProject);
    }

    @Test
    public void testSaveProjects() {
        assertTrue(projectList.getListOfProjects().size() > 0);
        projectList.saveProjects();
    }

    @Test
    public void testGetInstanceWithNoProjects() {
        projectList = ProjectList.getInstance(new ArrayList<>());
        assertNotNull(projectList);
        assertTrue(projectList.getListOfProjects().isEmpty());
    }
    
    @Test
    public void testFindProjectWithEmptyList() {
        projectList = ProjectList.getInstance(new ArrayList<>());
        assertNull(projectList.findProject(project1.getProjectUUID()));
    }
    
    @Test
    public void testFindProjectByNameWithEmptyList() {
        projectList = ProjectList.getInstance(new ArrayList<>());
        assertNull(projectList.findProject("NonExistentProject"));
    }

}

