package scrumprojectcode;

import java.util.ArrayList;

/**
 * Represents a leaderboard that displays user scores for a scrum project.
 */
public class Leaderboard {
    // Attributes
    private ArrayList<User> usersLeaderboard;  // Stores the list of users in the leaderboard

    /**
     * Constructor to create a Leaderboard object.
     *
     * @param users The list of users to initialize the leaderboard with.
     */
    public Leaderboard(ArrayList<User> users) {
        // Initialize the leaderboard with the provided users
        this.usersLeaderboard = new ArrayList<>(users);
    }

    /**
     * Method to retrieve and display user scores.
     *
     * Implement logic to retrieve user scores and display or process them.
     * This method should be used to obtain and handle user scores.
     */
    public void getUserScores() {
        // Implement logic to retrieve user scores
        // This method should display or process user scores
    }

    /**
     * Returns a string representation of the Leaderboard object.
     *
     * @return A string representing the Leaderboard object.
     */
    @Override
    public String toString() {
        // Implement logic to convert the leaderboard to a string representation
        return "Leaderboard{" +
                "usersLeaderboard=" + usersLeaderboard +
                '}';
    }
}

