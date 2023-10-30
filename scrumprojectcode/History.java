package scrumprojectcode;

/**
 * Represents the history of a task in a scrum project.
 */
import java.util.UUID;

public class History {
    private User user;
    private String details;
    private String date;
    private UUID historyUUID;
    private static UserList userList = UserList.getInstance(); // TODO update UML

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
        return this.user;
    }

    private void setUser(UUID user) {
        this.user = userList.findUser(user);
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
                "[User]: " + user.getUsername() + "\n" +
                "[History Details]: " + details + "\n" +
                "[Recorded Date]: " + date + "\n";
    }
}