package scrumprojectcode;

import java.util.ArrayList;

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

    public boolean findProject(String projectName){
        for(Project project : listOfProjects){
            if(project.getProjectName().equals(projectName)){
                return true;
            }
        }
        return false;
    }

    public void saveProjects() { //is this all we have to do for this method?
        DataWriter.saveProjects(listOfProjects);
    }
}
