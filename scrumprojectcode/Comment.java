package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a comment in a scrum project.
 */
public class Comment {

    // Attributes
    private String comment; // Stores the comment text
    private User user; // Represents the user associated with the comment
    private ArrayList<Comment> moreComments; // Stores Array of comments
    private UUID commentUUID; // Provides a unique identifier for the comment

    /**
     * Constructor to create a Comment object.
     *
     * @param comment The comment text.
     * @param user    The user associated with the comment.
     */
    public Comment(String comment, User user) {
        // Constructor logic
        this.comment = comment;
        this.user = user;
        this.commentUUID = generateUUID(); // Generate a unique UUID for the comment
        this.moreComments = new ArrayList<>(); // Initialize the list for additional comments
    }

    /**
     * Generates a unique UUID for the comment.
     *
     * @return The generated UUID.
     */
    private UUID generateUUID() {
        // UUID generation logic (to be implemented)
        // This method should return a unique UUID for the comment
        return null; // Replace with actual logic
    }

    /**
     * Returns a string representation of the Comment object.
     *
     * @return A string representing the Comment object.
     */
    @Override
    public String toString() {
        // toString method logic (to be implemented)
        // This method should return a meaningful string representation of the Comment
        // object
        return ""; // Replace with actual logic
    }
}
