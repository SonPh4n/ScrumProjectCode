package scrumprojectcode;

public class UI {
    private ProjectSystemFACADE facade;

    public UI() {
        facade = ProjectSystemFACADE.getFacadeInstance();
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }

    public void run() {
        scenario1();
        scenario2();
    }

    public void scenario1() {
        System.out.println();

        // User logs in
        /*
         * if (!facade.login("johnny", "8xw0CUeKk")) {
         * System.out.println("Sorry we couldn't login.");
         * return;
         * }
         */
        if (!facade.register("alex", "m", "alexm", "yellow", "alex.com", "1111111111", "user")) {
            System.out.println("Sorry we couldn't register you as a user.");
            return;
        }
        System.out.println("Alex M is now registered");
        // System.out.println("Johnny Applesause is now logged in.");

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
        if (!facade.addTask("new task", "new column", "new task", "this is a task", "11/01/2023")) {
            System.out.println("Sorry we could not create your task.");
        } else {
            System.out.println("new task has been added to new column in new project.");
        }

    }

    public void scenario2() {
        System.out.println();

        // User logs in
        if (!facade.login("co_hanna", "123abc")) { // Modified this since Bobby Jones was removed from user.json during
                                                   // debugging @kuriakm
            System.out.println("Sorry we couldn't login.");
            return;
        }

        System.out.println("Johanna Cole is now logged in.");

        // comments on a pre-existing task
        /*
         * if (!facade.addComment("Good work", "new project", "new column", "new task"))
         * {
         * System.out.println("Sorry we could not add your comment.");
         * } else {
         * System.out.
         * println("'good work' was was commented on new task in new column in new project."
         * );
         * }
         */
    }

}
