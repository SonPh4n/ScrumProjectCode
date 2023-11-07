package scrumprojectcode.Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import scrumprojectcode.User;
import scrumprojectcode.UserList;

import java.util.ArrayList;
import java.util.UUID;

public class UserListTest {

    private UserList userList;
    private ArrayList<User> users;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        users = new ArrayList<>();
        user1 = new User("user1", "password1", null, null, null, null, null);
        user2 = new User("user2", "password2", null, null, null, null, null);
        users.add(user1);
        users.add(user2);
        userList = UserList.getInstance(users);
    }

    @Test
    public void testGetInstance() {
        UserList instance = UserList.getInstance();
        assertNotNull(instance);
        assertSame(userList, instance);
    }

    @Test
    public void testGetInstanceWithUsers() {
        UserList instance = UserList.getInstance(users);
        assertNotNull(instance);
        assertSame(userList, instance);
    }

    @Test
    public void testGetListOfUsers() {
        ArrayList<User> userListUsers = userList.getListOfUsers();
        assertEquals(2, userListUsers.size());
        assertTrue(userListUsers.contains(user1));
        assertTrue(userListUsers.contains(user2));
    }

    @Test
    public void testFindUserByUUID() {
        User foundUser = userList.findUser(user1.getUserUUID());
        assertNotNull(foundUser);
        assertEquals(user1.getUserUUID(), foundUser.getUserUUID());
    }

    @Test
    public void testFindUserByUUIDNonExistent() {
        UUID nonExistentUUID = UUID.randomUUID();
        User foundUser = userList.findUser(nonExistentUUID);
        assertNull(foundUser);
    }

    @Test
    public void testFindUserByUsernameAndPassword() {
        User foundUser = userList.findUser("user2", "password2");
        assertNotNull(foundUser);
        assertEquals("user2", foundUser.getUsername());
        assertEquals("password2", foundUser.getPassword());
    }

    @Test
    public void testFindUserByUsernameAndPasswordNonExistent() {
        User foundUser = userList.findUser("user3", "password3");
        assertNull(foundUser);
    }

    @Test
    public void testFindUserByUsername() {
        User foundUser = userList.findUser("user1");
        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUsername());
    }

    @Test
    public void testFindUserByUsernameNonExistent() {
        User foundUser = userList.findUser("user3");
        assertNull(foundUser);
    }

    @Test
    public void testSaveUsers() {
        // Ensure there are users in the list
        assertTrue(userList.getListOfUsers().size() > 0);

        // Save the users
        userList.saveUsers();

        // You can add assertions here to check if the users were saved successfully
    }

    @Test
    public void testGetInstanceWithNoUsers() {
        userList = UserList.getInstance(new ArrayList<>());
        assertNotNull(userList);
        assertTrue(userList.getListOfUsers().isEmpty());
    }

    @Test
    public void testFindUserByUUIDWithEmptyList() {
        userList = UserList.getInstance(new ArrayList<>());
        assertNull(userList.findUser(user1.getUserUUID()));
    }

    @Test
    public void testFindUserByUsernameAndPasswordWithEmptyList() {
        userList = UserList.getInstance(new ArrayList<>());
        assertNull(userList.findUser("user1", "password1"));
    }

    @Test
    public void testFindUserByUsernameWithEmptyList() {
        userList = UserList.getInstance(new ArrayList<>());
        assertNull(userList.findUser("user1"));
    }

    @Test
    public void testToString() {
        String userListString = userList.toString();
        assertTrue(userListString.contains("user1"));
        assertTrue(userListString.contains("user2"));
    }

    @Test
    public void testSaveUsersWithEmptyList() {
        userList = UserList.getInstance(new ArrayList<>());
        userList.saveUsers();
    }

    @Test
    public void testGetInstanceWithSameInstance() {
        UserList instance1 = UserList.getInstance();
        UserList instance2 = UserList.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetInstanceWithSameInstanceAndUsers() {
        UserList instance1 = UserList.getInstance(users);
        UserList instance2 = UserList.getInstance(users);
        assertSame(instance1, instance2);
    }

    @Test
    public void testFindUserByUsernameAndPasswordCaseSensitive() {
        User foundUser = userList.findUser("user2", "Password2");
        assertNull(foundUser);
    }

    @Test
    public void testFindUserByUsernameCaseSensitive() {
        User foundUser = userList.findUser("User1");
        assertNull(foundUser);
    }

}

