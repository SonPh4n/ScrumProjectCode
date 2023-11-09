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
    }

    public void scenario1() {
        System.out.println();

        if (!facade.login("a_madden", "O4r40Rs")) {
            System.out.println("Sorry we couldn't log you in.");
        } else {
            System.out.println("Atticus Madden is now logged in");
        }

        System.out.println("You have opened the project 'Electric Missile'");

        // create task
        if (!facade.addTask("Electric Missile", "Back Log", "task2",
                "Initialize super algorithm to detonate at warp speed", "task", "11/\01/\2023")) {
            System.out.println("Sorry, we could not create your task.");
        } else {
            System.out.println(
                    "Task: 'Initialize super algorithm to detonate at warp speed' has been added to the project.");
        }

        // assign jeff to task
        if (!facade.removeUserFromTask("a_madden", "task2") && !facade.addUserToTask("gold_j", "task2")) {
            System.out.println("Sorry, we could not add an assigned user");
        } else {
            System.out.println(
                    "Jeff Goldblum has been assigned to 'Initialize super algorition to detonate warp speed'.");
        }

        // user comments "avoid civilians jeff" to warp task

        if (!facade.addComment("a_madden", "task2", "Avoid civilians Jeff!")) {
            System.out.println("Sorry, we could not add your comment to the task");
        } else {
            System.out.println("'Avoid civilians Jeff!' comment has been added to task");
        }

        // cylindrical task moved to Doing
        if (!facade.moveTask("Electric Missile", "Back Log", "Doing", "task1")) {
            System.out.println("Sorry, we could not move your task to the column");
        } else {
            System.out.println("Task: 'Curve the metal to make a cylindrical shape' has been moved to 'Doing'");
        }

        ///// add comment to existing task
        if (!facade.addReplyComment("a_madden", "task1", "How about you do it Jeff?", "gold_j",
                "Not cylindrical enough")) {
            System.out.println("Sorry, we could not add your comment to the task");
        } else {
            System.out.println("'How about you do it Jeff?' has been added as a reply to gold_j");
        }

        // create abandoned column
        if (!facade.addColumn("Electric Missile", "Abandoned")) {
            System.out.println("Sorry we couln't create your column.");
        } else {
            System.out.println("'Abandoned' has been added to Electric Missile");
        }

        // move impossible burger task to abandoned
        if (!facade.moveTask("Electric Missile", "Back Log", "Abandoned", "task3")) {
            System.out.println("Sorry, we could not move your task to the column");
        } else {
            System.out.println("Task: 'Make impossible burger possible' has been moved to 'Abandoned'");
        }

        facade.logout();
        if (facade.getCurrentUser() == null) {
            System.out.println("Atticus Madden is now logged out.");
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
