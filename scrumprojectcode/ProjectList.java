package scrumprojectcode;

import java.util.ArrayList;

public class ProjectList {
    private ArrayList<Project> listOfProjects;
    
    private ProjectList(ArrayList<Project> projects)
    {
        this.listOfProjects = projects;
    
    }

    public static ProjectList getInstance(ArrayList<Project> projects)
    {
        return new ProjectList(projects);
    }
}
