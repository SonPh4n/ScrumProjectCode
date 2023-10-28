package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class ProjectList {
    private static ProjectList projectList = null;
    private static ArrayList<Project> listOfProjects;

    private ProjectList() {
        listOfProjects = DataLoader.loadProjects();
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

    public Project findProject(UUID uuid){
        for(Project project : listOfProjects){
            if(project.getProjectUUID().equals(uuid)){
                return project;
            }
        }
        return null;
    }

    public void saveProjects() { 
        DataWriter.saveProjects(listOfProjects);
    }
}
