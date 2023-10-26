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
     * finds user by email
     * @param email String representation of user's email
     * @return true if user is found, false if not
     */
    public boolean findUser(UUID uuid){
        for(User user : listOfUsers){
            if(user.getUserUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public void saveUsers() { //is this all we have to do for this method?
        DataWriter.saveUsers(listOfUsers);
    }
}
