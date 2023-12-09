package scrumprojectcode;

/**
 * Represents the history of a task in a scrum project.
 */
import java.util.UUID;

public class History {
    private String details;
    private String date;
    private UUID historyUUID;

    /**
     * Constructs a new History object with the given user ID, date, and details.
     * 
     * @param date    the date of the history entry
     * @param details the details of the history entry
     */
    public History(String date, String details) {
        setDate(date);
        setDetails(details);
        this.historyUUID = generateUUID();
    }

    /**
     * Constructs a History object from DataLoader
     * 
     * @param historyID the UUID of the object
     * @param date      the date of the history entry
     * @param details   the details of the history entry
     */
    public History(UUID historyID, String date, String details) {
        setHistoryUUID(historyID);
        setDate(date);
        setDetails(details);
    }

    /**
     * Generates a UUID for the history.
     *
     * @return The generated UUID for the history.
     */
    private UUID generateUUID() {
        return UUID.randomUUID();
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
                "[History Details]: " + details + "\n" +
                "[Recorded Date]: " + date + "\n";
    }
}