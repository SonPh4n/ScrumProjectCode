package scrumprojectcode;

import java.util.ArrayList;

public class Feature {
    private ArrayList<String> subTasks;
    
    public Feature(String taskName, ArrayList<String> subTasks){
        //TODO
    }

    public void addSubtask(String subtask){
        //TODO
    }

    public void removeSubtask(String subtask){
        //TODO
    }

    public String toString(){
        //TODO
        return "";
    }

    //Getter/Setter
    public ArrayList<String> getSubTasks(){
        return subTasks;
    }

    public void setSubTasks(ArrayList<String> subTasks){
        this.subTasks = subTasks;
    }

}