package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class represents a list of projects. It is a singleton class that can be
 * accessed through the getInstance() method.
 * It contains methods to get the list of projects, find a project by UUID or
 * project name, and save the list of projects.
 */
public class ProjectList {
    private static ProjectList projectList = null;
    private static ArrayList<Project> listOfProjects;

    private ProjectList() {
        listOfProjects = (DataLoader.loadProjects() == null ? new ArrayList<>() : DataLoader.loadProjects());
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

    public void addTask(Task newTask, UUID columnUUID) {
        for (Project project : listOfProjects)
            for (Column column : project.getListOfColumns())
                if (column.getColumnUUID().equals(columnUUID) && !column.getColumnTasks().contains(newTask))
                    column.getColumnTasks().add(newTask);
    }

    public void removeTask(Task taskToRemove, UUID columnUUID) {
        for (Project project : listOfProjects)
            for (Column column : project.getListOfColumns())
                if (column.getColumnUUID().equals(columnUUID) && column.getColumnTasks().contains(taskToRemove))
                    column.getColumnTasks().remove(taskToRemove);
    }

    public Task findTask(UUID taskUUID) {
        for (Project project : listOfProjects)
            for (Column column : project.getListOfColumns())
                for (Task task : column.getColumnTasks())
                    if (task.getTaskUUID().equals(taskUUID))
                        return task;
        return null;
    }

    public Task findTask(String taskName) {
        for (Project project : listOfProjects)
            for (Column column : project.getListOfColumns())
                for (Task task : column.getColumnTasks())
                    if (task.getTaskName().equalsIgnoreCase(taskName))
                        return task;
        return null;
    }

    public Project findProject(UUID uuid) {
        for (Project project : listOfProjects)
            if (project.getProjectUUID().equals(uuid))
                return project;
        return null;
    }

    public Project findProject(String projectName) {
        for (Project project : listOfProjects)
            if (project.getProjectName().equals(projectName))
                return project;
        return null;
    }

    public ArrayList<Project> getListOfProjects() {
        return listOfProjects;
    }

    public void saveProjects() {
        DataWriter.saveProjects(listOfProjects);
    }

    public String toString() {
        String toString = "";
        for (Project project : listOfProjects)
            toString = toString + project + "\n\n";
        return toString;
    }
}
