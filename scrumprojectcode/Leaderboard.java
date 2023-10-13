package scrumprojectcode;

import java.util.ArrayList;

public class Leaderboard {
    // Attributes
    private ArrayList<User> usersLeaderboard;

    // Constructor
    public Leaderboard(ArrayList<User> users) {
        // Initialize the leaderboard with the provided users
        this.usersLeaderboard = new ArrayList<>(users);
    }

    // Methods

    // Method to retrieve user scores
    public void getUserScores() {
        // Implement logic to retrieve user scores
        // This method should display or process user scores
    }

    // Override toString() method
    @Override
    public String toString() {
        // Implement logic to convert the leaderboard to a string representation
        return "Leaderboard{" +
                "usersLeaderboard=" + usersLeaderboard +
                '}';
    }
}
