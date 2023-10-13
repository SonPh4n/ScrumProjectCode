package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

public class Comment {

    // Attributes
    private String comment;
    private User user;
    private ArrayList<Comment> moreComments;
    private UUID commentUUID;

    // Constructors
    public Comment(String comment, User user) {
        // Constructor logic
    }

    // Private method to generate UUID
    private UUID generateUUID() {
        // UUID generation logic
        return null;  // Replace with actual logic
    }

    // Override toString method
    @Override
    public String toString() {
        // toString method logic
        return "";  // Replace with actual logic
    }

    // Other methods (if needed)
}
