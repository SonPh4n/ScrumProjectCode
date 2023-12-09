package scrumprojectcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a leaderboard that displays user scores for a scrum project.
 */
public class Leaderboard {
    private ArrayList<User> usersLeaderboard;
    private Map<User, Integer> userScores;

    /**
     * Constructor to create a Leaderboard object.
     *
     * @param users The list of users to initialize the leaderboard with.
     */
    public Leaderboard(ArrayList<User> users) {
        this.usersLeaderboard = new ArrayList<>(users);
        this.userScores = new HashMap<>();

        // Initialize user scores to zero for each user
        for (User user : usersLeaderboard)
            setScore(user, 0);
    }

    /**
     * Void method that sets the scores for each user in the leaderboard
     * 
     * @param user
     * @param score
     */
    public void setScore(User user, int score) {
        if (userScores.containsKey(user)) {
            userScores.put(user, score);
        } else
            System.out.println("User not found in the leaderboard.");
    }

    /**
     * Adds the given task points to the user's score in the leaderboard.
     * If the user is not found in the leaderboard, prints an error message.
     * 
     * @param user       the user whose score is to be updated
     * 
     * @param taskPoints the points to be added to the user's score
     */
    public void completeTask(User user, int taskPoints) {
        if (userScores.containsKey(user)) {
            int currentScore = userScores.get(user);
            int newScore = currentScore + taskPoints;
            userScores.put(user, newScore);
        } else
            System.out.println("User not found in the leaderboard.");
    }

    public ArrayList<User> getUsersLeaderboard() {
        return usersLeaderboard;
    }

    public void setUsersLeaderboard(ArrayList<User> usersLeaderboard) {
        this.usersLeaderboard = usersLeaderboard;
    }

    public Map<User, Integer> getUserScores() {
        return userScores;
    }

    public void setUserScores(Map<User, Integer> userScores) {
        this.userScores = userScores;
    }

    public void printScores() {
        for (User user : usersLeaderboard) {
            int score = userScores.get(user);
            System.out.println("User: " + user.getFirstName() + " Score: " + score);
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
