package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author jedalto
 */
public class UserList {
    private static UserList userList = null;
    private static ArrayList<User> listOfUsers;

    private UserList() {
        listOfUsers = (DataLoader.loadUsers() == null ? new ArrayList<>() : DataLoader.loadUsers());
    }

    private UserList(ArrayList<User> users) {
        listOfUsers = users;
    }

    public static UserList getInstance(ArrayList<User> users) {
        if (userList == null)
            userList = new UserList(users);
        return userList;
    }

    public static UserList getInstance() {
        if (userList == null)
            userList = new UserList();
        return userList;
    }

    /**
     * finds user by uuid
     * 
     * @param uuid unique id for individual user
     * 
     * @return true if user is found by uuid, false if not
     */
    public User findUser(UUID uuid) {
        for (User user : listOfUsers) {
            if (user.getUserUUID().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    public User findUser(String username, String password) {
        for (User user : listOfUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public User findUser(String username) {
        for (User user : listOfUsers) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public void saveUsers() {
        DataWriter.saveUsers(listOfUsers);
    }

    public String toString() {
        String toString = "";
        for (User user : listOfUsers)
            toString = toString + user + "\n\n";
        return toString;
    }
}
