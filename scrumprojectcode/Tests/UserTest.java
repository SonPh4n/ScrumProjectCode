package scrumprojectcode.Tests;
import scrumprojectcode.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "johndoe", "password", "johndoe@example.com", "1234567890", "admin");
    }

    @Test
    void testConstructor() {
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhoneNumber());
        assertEquals("admin", user.getUserType());
        assertFalse(user.isLoggedIn());
    }

    @Test
    void testSettersAndGetters() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());

        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());

        user.setEmail("janesmith@example.com");
        assertEquals("janesmith@example.com", user.getEmail());

        user.setPhoneNumber("0987654321");
        assertEquals("0987654321", user.getPhoneNumber());

        user.setUserType("user");
        assertEquals("user", user.getUserType());

        UUID newUUID = UUID.randomUUID();
        user.setUserUUID(newUUID);
        assertEquals(newUUID, user.getUserUUID());
    }

    @Test
    void testLogin() {
        assertFalse(user.isLoggedIn());
        assertFalse(user.facadeLogin("wrongusername", "wrongpassword"));
        assertFalse(user.isLoggedIn());
        assertTrue(user.facadeLogin("johndoe", "password"));
        assertTrue(user.isLoggedIn());
    }

    @Test
    void testLogout() {
        user.facadeLogin("johndoe", "password");
        assertTrue(user.isLoggedIn());
        user.logout();
        assertFalse(user.isLoggedIn());
    }

    // Add more tests for other methods in the User class
}