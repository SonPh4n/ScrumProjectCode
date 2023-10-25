package scrumprojectcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a leaderboard that displays user scores for a scrum project.
 */
public class Leaderboard {
    // Attributes
    private ArrayList<User> usersLeaderboard;  // Stores the list of users in the leaderboard
    private Map<User, Integer> userScores;     // Stores user scores

    /**
     * Constructor to create a Leaderboard object.
     *
     * @param users The list of users to initialize the leaderboard with.
     */
    public Leaderboard(ArrayList<User> users) {
        this.usersLeaderboard = new ArrayList<>(users);
        this.userScores = new HashMap<>();
        
        // Initialize user scores to zero for each user
        for (User user : usersLeaderboard) {
            userScores.put(user, 0);
        }
    }

    /**
     * Method to retrieve and display user scores.
     *
     * Implement logic to retrieve user scores and display or process them.
     * This method should be used to obtain and handle user scores.
     */
    public void setUserScore(User user, int score) {
        if (userScores.containsKey(user)) {
            userScores.put(user, score);
        } else {
            System.out.println("User not found in the leaderboard.");
        }
    }

    public void getUserScores() {
        for (User user : usersLeaderboard) {
            int score = userScores.get(user);
            System.out.println("User: " + user.getFirstName() + " Score: " + score);
        }
    }

    public void completeTask(User user, int taskPoints) {
        if (userScores.containsKey(user)) {
            int currentScore = userScores.get(user);
            int newScore = currentScore + taskPoints;
            userScores.put(user, newScore);
        } else {
            System.out.println("User not found in the leaderboard.");
        }
    }

    /**
     * Returns a string representation of the Leaderboard object.
     *
     * @return A string representing the Leaderboard object.
     */
    @Override
    public String toString() {
        String leaderboardString = "Leaderboard:\n";
        for (User user : usersLeaderboard) {
            int score = userScores.get(user);
            leaderboardString += "User: " + user.getFirstName() + " Score: " + score + "\n";
        }
    return leaderboardString;
    }
}

