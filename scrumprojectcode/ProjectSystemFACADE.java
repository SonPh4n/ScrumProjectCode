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

    public User login(String username, String password){
        return null;
    }

    public User register(){
        return null;
    }

    public void addProject(Project project){
        //TODO
    }

    public void removeProject(Project projet){
        //TODO
    }

    public void addColumn(Column column){
        //TODO
    }

    public void removeColumn(Column column){
        //TDO
    }

    public void addTask(Task task){
        //TODO
    }

    public void removeTask(Task task){
        //TODO
    }

    public void moveTask(Task task, Column newColumn){
        //TODO
    }
}