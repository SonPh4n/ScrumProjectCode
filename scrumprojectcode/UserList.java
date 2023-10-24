package scrumprojectcode;

import java.util.ArrayList;

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

    public void saveUsers() {
        DataWriter.saveUsers(listOfUsers);
    }
}
