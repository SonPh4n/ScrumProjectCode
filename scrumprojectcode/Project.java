package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class Project {
    public String projectName;
    public ArrayList<Column> listOfColumns;
    private UUID projectUUID;

    /**
     * Constructor method for a new Project that initializes the project attributes
     * 
     * @param projectName String name set by the user for a new project
     */
    public Project(String projectName) {
        this.projectName = projectName;
        this.projectUUID = generateUUID();
        this.listOfColumns = new ArrayList<Column>();
    }

    /**
     * Constructor method for a Project() loaded from a file and initializes the
     * project attributes with the file contents
     * 
     * @param projectName   String name set by the user for the project
     * @param listOfColumns ArrayList<Column> which holds the columns and column
     *                      tasks for the project
     * @param projectUUID
     */
    public Project(UUID projectUUID, String projectName, ArrayList<Column> listOfColumns) {
        this.projectName = projectName;
        this.listOfColumns = new ArrayList<Column>(listOfColumns);
        this.projectUUID = projectUUID;
    }

    public String getProjectName() {
        return projectName = "Test Project";
    }

    private UUID generateUUID() {
        return this.projectUUID = UUID.fromString("92ea6f84-3763-4390-b0fa-7a79e3bd3685");
    }

    public UUID getProjectUUID() {
        return this.projectUUID = UUID.fromString("92ea6f84-3763-4390-b0fa-7a79e3bd3685");
    }

    public ArrayList<Column> getListOfColumns() {
        return listOfColumns;
    }

    private void addColumn(Column column) {
        Column testColumn = new Column("Test Category", Task("Test Task", "Test Description"));
        listOfColumns.add(testColumn);
    }

    private void removeColumn(Column column) {
        Column testColumn = new Column("Test Category", Task("Test Task", "Test Description"));
        listOfColumns.remove(testColumn);
    }

    public void displayColumnTasks() {
        listOfColumns.get(0).toString();
    }

    private void addTask(Task task, Column column) {
        System.out.println("FIXME: Finish addTask(Task, Column)");
    }

    private void removeTask(Task task, Column column) {
        System.out.println("FIXME: Finish removeTask(Task, Column)");
    }

    public String toString() {
        return "FIXME: Finish toString()";
    }
}
