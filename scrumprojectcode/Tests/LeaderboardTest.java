package scrumprojectcode.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrumprojectcode.DataLoader;
import scrumprojectcode.Leaderboard;
import scrumprojectcode.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class holds the JUnit tests for Leaderboard.
 * 
 * Methods tested:
 * Leaderboard(ArrayList<User> users)
 * - normal values
 * - null value(s)
 * setUserScore(User user, int score)
 * - normal values
 * - null/negative values
 * - User does not exist
 * completeTask(User user, int taskPoints)
 * - normal values
 * - null/negative values
 * - User does not exist
 * toString()
 * 
 * @author kuriakm
 */

public class LeaderboardTest {
    private Leaderboard leaderboard;
    private ArrayList<User> users;
    private Map<User, Integer> userScores;

    @BeforeEach
    void setUp() {
        users = DataLoader.loadUsers();
        leaderboard = new Leaderboard(users);
        userScores = new HashMap<>();

        int scoreMod = 0;
        for (User user : users) {
            userScores.put(user, scoreMod);
            scoreMod++;
        }
    }

    /**
     * Test Leaderboard(ArrayList<User> users) with normal values
     * 
     * Everything should return true if the class methods are working properly
     */
    @Test
    void testConstructor() {
        assertNotNull(leaderboard);
        assertEquals(users, leaderboard.getUsersLeaderboard());
        assertNotNull(leaderboard.getUserScores());
    }

    /**
     * Test Leaderboard(ArrayList<User> users) with null ArrayList<User>
     * 
     * The expected result is that usersLeaderboard is instantiated as an empty
     * ArrayList<User> and userScores is instantiated as an empty HashMap<User,
     * int>()
     */
    @Test
    void testConstructorWithNullUsers() { // TODO: Add check in Leaderboard to see if ArrayList<User> is null
                                          // @kuriakm
        users = null;
        leaderboard = new Leaderboard(users);
        assertNotNull(leaderboard.getUsersLeaderboard());
        assertNotNull(leaderboard.getUserScores());
    }

    /**
     * Test Leaderboard(ArrayList<User> users) with null Users in ArrayList<User>
     * 
     * The expected result is that usersLeaderboard is instantiated with the valid
     * users in the list and userScores holds the scores of those remaining users
     */
    @Test
    void testConstructorWithNullUserInArrayList() { // TODO: Check for null/invalid users in ArrayList<User> before
                                                    // setting it to usersLeaderboard @kuriakm
        User nullUser = null;
        users.add(nullUser);
        leaderboard = new Leaderboard(users);
        assertTrue(!leaderboard.getUsersLeaderboard().contains(nullUser));
        assertEquals(0, leaderboard.getUserScores().get(nullUser));
    }

    /**
     * Test setUserScore(User user, int score) with normal values
     * 
     * Should return true if the scores were set correctly in Leaderboard
     */
    @Test
    void testSetUserScore() {
        int scoreMod = 0;
        for (User user : leaderboard.getUsersLeaderboard()) {
            leaderboard.setScores(user, scoreMod);
            scoreMod++;
        }

        assertEquals(userScores, leaderboard.getUserScores());
    }

    /**
     * Test setUserScore(User user, int score) with null user parameter
     *
     * Should return true if setScores() is set to ignore null Users
     */
    @Test
    void testSetUserScoreWithNullUser() {
        int scoreMod = 0;
        for (User user : leaderboard.getUsersLeaderboard()) {
            leaderboard.setScores(user, scoreMod);
            scoreMod++;
        }
        leaderboard.setScores(null, 100);

        assertEquals(userScores, leaderboard.getUserScores());
    }

    /**
     * Test setUserScore(User user, int score) with a user that does not exist in
     * the system
     *
     * Should return true if setScores() is set to ignore nonexistent Users
     */
    @Test
    void testSetUserScoreWithNonexistentUser() {
        int scoreMod = 0;
        for (User user : leaderboard.getUsersLeaderboard()) {
            leaderboard.setScores(user, scoreMod);
            scoreMod++;
        }
        leaderboard.setScores(new User("test first name", "test last name", "tester", "yuhf*@cr", "tester@gmail.com",
                "419-2l3-8923", "user"), 100);

        assertEquals(userScores, leaderboard.getUserScores());
    }

    /**
     * Test setUserScore(User user, int score) with a negative score
     *
     * Should return true if setScores() is set to ignore negative numbers
     */
    @Test
    void testSetUserScoreWithNegativeScore() { // TODO: Check for negative ints in setScores() and set the score to 0
                                               // instead @kuriakm
        int scoreMod = 0;
        for (User user : leaderboard.getUsersLeaderboard()) {
            leaderboard.setScores(user, scoreMod);
            scoreMod++;
        }

        leaderboard.setScores(leaderboard.getUsersLeaderboard().get(0), -1);

        assertEquals(userScores, leaderboard.getUserScores());
    }

    // Test toString()
    @Test
    void testToString() {
        String expectedString = "Leaderboard:\n";
        for (User user : users) {
            int score = userScores.get(user);
            expectedString += "User: " + user.getFirstName() + " Score: " + score + "\n";
        }
        testSetUserScore(); // Initializes leaderboard.userScores with non-zero scores
        assertEquals(expectedString, leaderboard.toString());
    }
}
