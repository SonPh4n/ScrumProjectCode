package scrumprojectcode;

import java.util.ArrayList; // Remove after testing @kuriakm
import java.util.UUID;

/**
 * Represents the history of a task in a scrum project.
 */
public class History {
    private UUID user;
    private String details;
    private String date;
    private UUID historyUUID;
    private ArrayList<User> userList = DataLoader.loadUsers(); // Temp attribute to bypass list classes @kuriakm

    // TODO: Reimplement attribute after testing @kuriakm
    // private static UserList userList = UserList.getInstance();

    /**
     * Constructs a new History object with the given user ID, date, and details.
     * @param userID the UUID of the user associated with this history
     * @param date the date of this history entry
     * @param details the details of this history entry
     */
    public History(UUID userID, String date, String details) {
        setUser(userID);
        setDate(date);
        setDetails(details);
        this.historyUUID = generateUUID();
    }

    public History(UUID historyID, UUID userID, String date, String details) {
        setHistoryUUID(historyID);
        setUser(userID);
        setDate(date);
        setDetails(details);
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    public User getUser() {
        return findUser(this.user);
    }

    public UUID getUserUUID() {
        return this.user;
    }

    private void setUser(UUID user) {
        this.user = user;
    }

    public String getDetails() {
        return this.details;
    }

    private void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return this.date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public UUID getHistoryUUID() {
        return this.historyUUID;
    }

    private void setHistoryUUID(UUID historyUUID) {
        this.historyUUID = historyUUID;
    }

    // TODO: Modify method after testing @kuriakm
    public User findUser(UUID userUUID) {
        userList = DataLoader.loadUsers();
        for (User user : userList) {
            if (user.getUserUUID().equals(userUUID)) {
                return user;
            }
        }
        return null;
    }

    // TODO: Remove method after testing @kuriakm
    public void setUserList(ArrayList<User> userList) {
        if (userList.isEmpty() || userList == null)
            return;
        else
            this.userList = userList;
    }

    // TODO: Remove method after testing @kuriakm
    public ArrayList<User> getUserList() {
        return userList;
    }

    public String toString() {
        return "[Task History]: \n" +
                "[User]: " + findUser(user).getUsername() + "\n" +
                "[History Details]: " + details + "\n" +
                "[Recorded Date]: " + date + "\n";
    }
}