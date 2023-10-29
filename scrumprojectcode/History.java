package scrumprojectcode;

/**
 * Represents the history of a task in a scrum project.
 */
import java.util.UUID;

public class History {
    private UUID user; // Was unable to use User object when loading from task.json so I changed it
    // to UUID @kuriakm
    private String details;
    private String date;
    private UUID historyUUID;

    public History(UUID userID, String date, String details) {
        this.user = userID;
        this.date = date;
        this.details = details;
        this.historyUUID = generateUUID();
    }

    public History(UUID historyID, UUID userID, String date, String details) {
        this.historyUUID = historyID;
        this.user = userID;
        this.date = date;
        this.details = details;
    }

    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    public UUID getUser() {
        return this.user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UUID getHistoryUUID() {
        return this.historyUUID;
    }

    public void setHistoryUUID(UUID historyUUID) {
        this.historyUUID = historyUUID;
    }

    public String toString() {
        for ()
    }
}