package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class represents a list of projects. It is a singleton class that can be accessed through the getInstance() method.
 * It contains methods to get the list of projects, find a project by UUID or project name, and save the list of projects.
 */
public class ProjectList {
    private static ProjectList projectList = null;
    private static ArrayList<Project> listOfProjects;
    private static DataLoader dl = DataLoader.getInstance();

    private ProjectList() {
        listOfProjects = dl.loadProjects();
    }

    private ProjectList(ArrayList<Project> projects) {
        listOfProjects = projects;
    }

    public static ProjectList getInstance(ArrayList<Project> projects) {
        if (projectList == null)
            projectList = new ProjectList();
        return projectList;
    }

    public static ProjectList getInstance() {
        if (projectList == null)
            projectList = new ProjectList();
        return projectList;
    }

    public ArrayList<Project> getListOfProjects() {
        return listOfProjects;
    }

    public Project findProject(UUID uuid) {
        for (Project project : listOfProjects) {
            if (project.getProjectUUID().equals(uuid)) {
                return project;
            }
        }
        return null;
    }

    public Project findProject(String projectName) {
        for (Project project : listOfProjects) {
            if (project.getProjectName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }

    public void saveProjects() {
        DataWriter.saveProjects(listOfProjects);
    }
}
