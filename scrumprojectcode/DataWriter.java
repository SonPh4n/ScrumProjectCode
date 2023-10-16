package scrumprojectcode;

public class DataWriter extends DataConstants{
    // Attributes
    private boolean saveTasks;
    private boolean saveUsers;
    private boolean saveProjects;

    // Constructor
    public DataWriter(boolean saveTasks, boolean saveUsers, boolean saveProjects) {
        this.saveTasks = saveTasks;
        this.saveUsers = saveUsers;
        this.saveProjects = saveProjects;
    }

    // Getters and Setters

    /**
     * Check if saving tasks is enabled.
     *
     * @return True if saving tasks is enabled, false otherwise.
     */
    public boolean isSaveTasks() {
        return saveTasks;
    }

    /**
     * Set whether to enable saving tasks.
     *
     * @param saveTasks True to enable saving tasks, false to disable.
     */
    public void setSaveTasks(boolean saveTasks) {
        this.saveTasks = saveTasks;
    }

    /**
     * Check if saving users is enabled.
     *
     * @return True if saving users is enabled, false otherwise.
     */
    public boolean isSaveUsers() {
        return saveUsers;
    }

    /**
     * Set whether to enable saving users.
     *
     * @param saveUsers True to enable saving users, false to disable.
     */
    public void setSaveUsers(boolean saveUsers) {
        this.saveUsers = saveUsers;
    }

    /**
     * Check if saving projects is enabled.
     *
     * @return True if saving projects is enabled, false otherwise.
     */
    public boolean isSaveProjects() {
        return saveProjects;
    }

    /**
     * Set whether to enable saving projects.
     *
     * @param saveProjects True to enable saving projects, false to disable.
     */
    public void setSaveProjects(boolean saveProjects) {
        this.saveProjects = saveProjects;
    }
}
