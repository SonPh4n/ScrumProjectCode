package scrumprojectcode;

public class UI {
    private ProjectSystemFACADE facade;

    public UI() {
        facade = ProjectSystemFACADE.getFacadeInstance();
    }

    public void run() {
        //facade = ProjectSystemFACADE.getFacadeInstance();
        scenario1();
        scenario2();
    }

    public void scenario1() {
        System.out.println();

        // User logs in
        if (!facade.login("johnny", "8xw0CUeKk")) {
            System.out.println("Sorry we couldn't login.");
            return;
        }

        System.out.println("Johnny Applesause is now logged in.");

        // make a project
        if (!facade.addProject("new project")) {
            System.out.println("Sorry we couldn't create your project.");
        } else {
            System.out.println("'New Project' has been successfully created.");
        }

        // create column
        if (!facade.addColumn("new column", "new project")) {
            System.out.println("Sorry we couln't create your column.");
        } else {
            System.out.println("new column has been added to new project");
        }

        // create task
        if (!facade.addTask("new task", "new column", "new task", "this is a task", "11/01/2023", "10/29/2023")) {
            System.out.println("Sorry we could not create your task.");
        } else {
            System.out.println("new task has been added to new column in new project.");
        }

    }

    public void scenario2() {
        System.out.println();

        // User logs in
        if (!facade.login("bjones", "dvSGCtX0H")) {
            System.out.println("Sorry we couldn't login.");
            return;
        }

        System.out.println("Bobby Jones is now logged in.");

        // comments on a pre-existing task
        /* if (!facade.addComment("Good work", "new project", "new column", "new task")) {
            System.out.println("Sorry we could not add your comment.");
        } else {
            System.out.println("'good work' was was commented on new task in new column in new project.");
        } */
    }

}
