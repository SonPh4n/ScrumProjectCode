/**
 * @author Jane Dalton
 */

package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList = null;
    private static ArrayList<User> listOfUsers; // @kuriakm: Allows UserList to create
                                                // an instance without an ArrayList

    private UserList() {
        listOfUsers = DataLoader.loadUsers();
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

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    /**
     * finds user by uuid
     * 
     * @param uuid unique id for individual user
     * @return true if user is found by uuid, false if not
     */
    public boolean findUser(UUID uuid) {
        for (User user : listOfUsers) {
            if (user.getUserUUID().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public boolean findUser(String email) {
        for (User user : listOfUsers) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void saveUsers() {
        DataWriter.saveUsers(listOfUsers);
    }
}
