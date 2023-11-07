package scrumprojectcode;

/**
 * Represents the history of a task in a scrum project.
 */
import java.util.UUID;

public class History {
    private UUID user;
    private String details;
    private String date;
    private UUID historyUUID;
    private static UserList userList = UserList.getInstance(); // TODO update UML

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
        return userList.findUser(this.user);
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

    public String toString() {
        return "[Task History]: \n" +
                "[User]: " + userList.findUser(user).getUsername() + "\n" +
                "[History Details]: " + details + "\n" +
                "[Recorded Date]: " + date + "\n";
    }
}