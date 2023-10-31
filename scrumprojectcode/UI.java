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
        if (!facade.register("Atticus", "Madden", "AtticusM", "yellow", "AtticusMadden@", "1111111111", "user")) {
            System.out.println("Sorry we couldn't register you as a user.");
            return;
        }
        System.out.println("Atticus Madden is now registered");
        // System.out.println("Johnny Applesause is now logged in.");

        // make a project
        if (!facade.addProject("Electric Missile")) {
            System.out.println("Sorry we couldn't create your project.");
        } else {
            System.out.println("'Electric Missile' has been successfully created.");
        }
        // make a project
        if (!facade.addProject("Soap Free Washers")) {
            System.out.println("Sorry we couldn't create your project.");
        } else {
            System.out.println("'Soap Free Washers' has been successfully created.");
        }
        // make a project
        if (!facade.addProject("Air Computers")) {
            System.out.println("Sorry we couldn't create your project.");
        } else {
            System.out.println("'Air Computers' has been successfully created.");
        }


        // create column
        if (!facade.addColumn("Electric Missile", "back log")) {
            System.out.println("Sorry we couln't create your column.");
        } else {
            System.out.println("new column has been added to Electric Missile");
        }
        // create column
        if (!facade.addColumn("Electric Missile", "To Do")) {
            System.out.println("Sorry we couln't create your column.");
        } else {
            System.out.println("new column has been added to Electric Missile");
        }
        

        // create task
        if (!facade.addTask("Electric Missile", "back log", "task2", "Initialize super algorithm to detonate at warp speed", "task", "11/\01/\2023")) {
            System.out.println("Sorry we could not create your task.");
        } else {
            System.out.println("new task has been added to new column in new project.");
        }
        // create task
        if (!facade.addTask("Electric Missile", "back log", "task1", "Curve the metal to make a cylindrical shape", "task", "11/\01/\2023")) {
            System.out.println("Sorry we could not create your task.");
        } else {
            System.out.println("new task has been added to new column in new project.");
        }

        if(!facade.assignUser("Electric Missile", "back log","task2"))
        {
            System.out.println("Sorry we could not add an assigned user");
        } else {
            System.out.println("Assigned task to Jeff Goldblum");
        }

        if(!facade.addComment("new task", "Avoid civilians Jeff!"))
        {
            System.out.println("Sorry we could not add your comment to the task");
        } else {
            System.out.println("new comment has been added to the task");
        }

        if(!facade.moveTask("Electric Missile", "back log", "To Do", "task1"))
        {

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
