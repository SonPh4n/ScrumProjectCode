package scrumprojectcode.Tests;
import scrumprojectcode.History;
import scrumprojectcode.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    private History history;
    private UUID userUUID;
    private String details;
    private String date;

    @BeforeEach
    void setUp() {
        userUUID = UUID.randomUUID();
        details = "This is a history detail";
        date = "2022-01-01";
        history = new History(userUUID, date, details);
    }

    @Test
    void testConstructor() {
        assertNotNull(history);
        assertEquals(details, history.getDetails());
        assertEquals(date, history.getDate());
        assertEquals(userUUID, history.getUserUUID());
    }

    @Test
    void testConstructorWithHistoryID() {
        UUID historyUUID = UUID.randomUUID();
        History historyWithID = new History(historyUUID, userUUID, date, details);
        assertEquals(historyUUID, historyWithID.getHistoryUUID());
    }

    @Test
    void testGetUser() {
        // This test assumes that the UserList class and its findUser method are working correctly.
        // If you want to isolate the behavior of the History class, you might need to use a mocking framework like Mockito.
        User user = history.getUser();
        assertEquals(userUUID, user.getUserUUID());
    }

    @Test
    void testToString() {
        // This test assumes that the UserList class and its findUser method are working correctly.
        // If you want to isolate the behavior of the History class, you might need to use a mocking framework like Mockito.
        String expectedString = "[Task History]: \n" +
                "[User]: " + history.getUser().getUsername() + "\n" +
                "[History Details]: " + details + "\n" +
                "[Recorded Date]: " + date + "\n";
        assertEquals(expectedString, history.toString());
    }
}