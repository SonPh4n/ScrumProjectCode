package scrumprojectcode;

public class ProjectSystemFACADE {
    private User user;
    private static ProjectSystemFACADE facade;

    public ProjectSystemFACADE(){
        //TODO
        user = new User();
    }

    public ProjectSystemFACADE getInstance(){
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

<<<<<<< HEAD
    public boolean addColumn(String columnName){
        //TODO
        return true; 
    }

    public boolean removeColumn(String columnName){
=======
    public boolean addColumn(String columnName, String projectName){
        //TODO
        return true;
    }

    public boolean removeColumn(String columnName, String projectName){
>>>>>>> projectsystemfacade-class
        //TODO
        return true;
    }

<<<<<<< HEAD
    public boolean addTask(String taskName){
=======
    public boolean addTask(String taskName, String columnName, String projectName){
>>>>>>> projectsystemfacade-class
        //TODO
        return true;
    }

<<<<<<< HEAD
    public boolean removeTask(String taskName){
=======
    public boolean removeTask(String taskName, String columnName, String projectName){
>>>>>>> projectsystemfacade-class
        //TODO
        return true;
    }

    public boolean moveTask(String taskName, String newColumn){
        //TODO
        return true;
    }
}