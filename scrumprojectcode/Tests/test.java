package scrumprojectcode.Tests;

import java.util.ArrayList;

import scrumprojectcode.DataLoader;
import scrumprojectcode.Project;
import scrumprojectcode.Task;
import scrumprojectcode.User;

public class test {
    public static void main(String[] args) {
        ArrayList<User> test = DataLoader.loadUsers();
        for (User user : test)
            System.out.println(user);

        ArrayList<Task> testTask = DataLoader.loadTasks();
        for (Task task : testTask)
            System.out.println(task);

        ArrayList<Project> testProject = DataLoader.loadProjects();
        for (Project project : testProject)
            System.out.println(testProject);
    }
}
