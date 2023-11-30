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

    /**
     * Constructs a new History object with the given user ID, date, and details.
     * 
     * @param username the username of the user associated with this history
     * 
     * @param date     the date of this history entry
     * 
     * @param details  the details of this history entry
     */
    public History(User user, String date, String details) {
        setUser(user);
        setDate(date);
        setDetails(details);
        this.historyUUID = generateUUID();
    }

    public History(UUID historyID, User user, String date, String details) {
        setHistoryUUID(historyID);
        setUser(user);
        setDate(date);
        setDetails(details);
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    public User getUser() {
        return this.user;
    }

    private void setUser(User user) {
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
                "[User]: " + user.getUsername() + "\n" +
                "[History Details]: " + details + "\n" +
                "[Recorded Date]: " + date + "\n";
    }
}