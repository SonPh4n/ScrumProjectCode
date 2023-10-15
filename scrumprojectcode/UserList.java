package scrumprojectcode;

import java.util.ArrayList;

public class UserList {
    
    private ArrayList<User> listOfUsers;
    
    private UserList(ArrayList<User> users)
    {
        this.listOfUsers = users;
    }

    public static UserList getInstance(ArrayList<User> users)
    {
        return listOfUsers;
    }

}
