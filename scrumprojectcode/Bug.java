package scrumprojectcode;

import java.util.ArrayList;

public class Bug extends Task {

    public User tester;
    public Priority priority;
    public ArrayList<String> reproductionSteps;

    public Bug(String taskName, Priority priority, User tester){
        super(taskName, "Bug", "randomTime"); //TODO update Bug/Task constructors
        //TODO
    }

    public String toString(){
        //TODO
        return "";
    }

}