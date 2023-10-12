package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class Project {
    public String projectName;
    public ArrayList<Column> listOfColumns;
    private UUID projectUUID;

    public Project(String projectName) {

    }

    public Project(String projectName, ArrayList<Column> listOfColumns, UUID projectUUID) {

    }

    private void setProjectName() {

    }

    public String getProjectName() {
        return projectName;
    }

    private UUID generateUUID() {
        return projectUUID.randomUUID();
    }

    private void setProjectUUID(UUID projectUUID) {

    }

    public UUID getProjectUUID() {
        return projectUUID;
    }

    private void setListOfColumns(ArrayList<Column> listOfColumns) {

    }

    public ArrayList<Column> getListOfColumns() {
        return listOfColumns;
    }

    private void addColumn(Column newColumn) {

    }

    private void removeColumn(Column newColumn) {

    }

    public void displayColumnTasks() {

    }

    private void addTask(Task task, Column column) {

    }

    private void removeTask(Task task, Column column) {

    }

    public String toString() {
        return "";
    }
}
