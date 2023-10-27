package scrumprojectcode;

import java.util.ArrayList;

public class Documentation {

    // Attributes
    private ArrayList<String> documentation;

    // Constructor
    public Documentation(ArrayList<String> documentation) {
        // TODO: Constructor logic
        this.documentation = new ArrayList<>(documentation);
    }

    // Methods

    /**
     * Gets the documentation.
     *
     * @return The ArrayList of documentation
     */
    public ArrayList<String> getDocumentation() {
        // TODO: Method logic
        return new ArrayList<>(documentation);
    }

    /**
     * Sets the documentation.
     *
     * @param documentation The ArrayList of documentation to set
     */
    public void setDocumentation(ArrayList<String> documentation) {
        // TODO: Method logic
        this.documentation = new ArrayList<>(documentation);
    }
}