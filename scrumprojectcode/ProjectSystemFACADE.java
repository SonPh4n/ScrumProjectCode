package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class ProjectSystemFACADE {
    private User user;
    private static ProjectSystemFACADE facade;

    private ProjectSystemFACADE(){
        facade = getFacadeInstance();
        user = null;
    }

    public static ProjectSystemFACADE getFacadeInstance(){
        if(facade == null){
            facade = new ProjectSystemFACADE();
        }
        return facade;
    }

    public boolean login(String username, String password){
        //TODO
        return true;
    }

    public boolean register(){
        //TODO
        return true;
    }

    public boolean addProject(String projectName){
        //TODO
        return true;
    }

    public boolean removeProject(String projectName){
        //TODO
        return true;
    }

    public boolean addColumn(String columnName, String projectName){
        //TODO
        return true;
    }

    public boolean removeColumn(String columnName, String projectName){
        //TODO
        return true;
    }

    public boolean addTask(String taskName, String columnName, String projectName){
        //TODO
        return true;
    }

    public boolean removeTask(String taskName, String columnName, String projectName){
        //TODO
        return true;
    }

    public boolean moveTask(String taskName, String newColumn){
        //TODO
        return true;
    }

    public boolean addComment(String comment, String projectName, String columnName, String taskName){
        //TODO
        return true;
    }
}