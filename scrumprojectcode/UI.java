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
        // scenario2();
    }

    public void scenario1() {
        System.out.println();

        if (!facade.login("a_madden", "O4r40Rs"))
            System.out.println("Sorry we couldn't log you in");
        else
            System.out.println("Atticus Madden has successfully logged in.");

        if (!facade.addProject("Electric Missile")) {
            System.out.println("Sorry we couldn't create your project.");
        } else {
            System.out.println("'Electric Missile' has been successfully created.");
        }

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

        if (!facade.addColumn("Electric Missile", "Back Log") && !facade.addColumn("Electric Missile", "Doing")) {
            System.out.println("Sorry we couln't create your column.");
        } else {
            System.out.println("'Back Log' and 'Doing' has been added to 'Electric Missile'");
        }

        if (!facade.addTask("Electric Missile", "Back Log", "task1",
                "Curve the metal to make a cylindrical shape", "task", "11/\\01/\\2023")) {
            System.out.println("Sorry we could not create your task.");
        } else {
            System.out.println("A task has been added to 'Back Log' in 'Electric Missile'.");
        }

        if (!facade.addComment("a_madden", "task1", "What's a cylinder"))
            System.out.println("Sorry, we couldn't add comments to the task");
        else
            System.out.println("Comments were added to task");

        if (!facade.addComment("gold_j", "task1", "Not cylindrical enough"))
            System.out.println("Sorry, we couldn't add comments to the task");
        else
            System.out.println("Comments were added to task");

        if (!facade.addTask("Electric Missile", "Back Log", "task2",
                "Initialize super algorithm to detonate at warp speed", "task", "11/\\01/\\2023")) {
            System.out.println("Sorry we could not create your task.");
        } else {
            System.out.println("A task has been added to 'Back Log' in 'Electric Missile'.");
        }

        if (!facade.addUserToProject("a_madden", "Electric Missile")) {
            System.out.println("Sorry we could not add you to 'Electric Missile'");
        } else {
            System.out.println("Atticus Madden was added to 'Electric Missile'");
        }
        if (!facade.addUserToProject("gold_j", "Electric Missile")) {
            System.out.println("Sorry we could not add you to 'Electric Missile'");
        } else {
            System.out.println("Jeff Goldblum was added to 'Electric Missile'");
        }

        if (!facade.addUserToTask("a_madden", "task1") && !facade.addUserToTask("a_madden", "task2")) {
            System.out.println("Sorry we could not add you to task");
        } else {
            System.out.println("Atticus Madden was added to task");
        }

        if (!facade.addUserToTask("gold_j", "task2")) {
            System.out.println("Sorry we could not add you to task");
        } else {
            System.out.println("Jeff Goldblum was added to task");
        }

        if (!facade.addComment("a_madden", "task2", "Avoid civilians Jeff!")) {
            System.out.println("Sorry we could not add your comment to task");
        } else {
            System.out.println("Atticus Madden added a comment to task");
        }

        if (!facade.addReplyComment("a_madden", "task1", "How about you do it jeff", "gold_j",
                "Not cylindrical enough")) {
            System.out.println("Sorry we could not add your comment to 'new task'");
        } else {
            System.out.println("Atticus added a comment to Jeff Goldblum's comment");
        }

        if (!facade.removeUserFromTask("a_madden", "task1")) {
            System.out.println("Sorry we could not remove you from the task");
        } else {
            System.out.println("Atticus Madden was removed from a task");
        }

        if (!facade.addUserToTask("gold_j", "task1")) {
            System.out.println("Sorry we could not add you to task");
        } else {
            System.out.println("Jeff Goldblum was added to task");
        }

        if (!facade.moveTask("Electric Missile", "Back Log", "Doing", "task1"))
            System.out.println("Sorry we could not move this task");
        else
            System.out.println("Task has been moved from 'Back Log' to 'Doing'");

        if (!facade.addTask("Electric Missile", "Back Log", "task3",
                "Make impossible burger possible", "task", "11/\\01/\\2023")) {
            System.out.println("Sorry we could not create your task.");
        } else {
            System.out.println("A task has been added to 'Back Log' in 'Electric Missile'.");
        }

        if (!facade.addColumn("Electric Missile", "Abandoned"))
            System.out.println("Sorry, we could not create this column");
        else
            System.out.println("'Abandoned' was created in 'Electric Missile'");

        if (!facade.moveTask("Electric Missile", "Back Log", "Abandoned", "task3"))
            System.out.println("Sorry we could not move this task");
        else
            System.out.println("Task has been moved from 'Back Log' to 'Abandoned'");

        facade.logout();

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
