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
    // Was unable to use User object when loading from task.json so I changed it
    // to UUID @kuriakm
    private ArrayList<Comment> moreComments; // Stores Array of comments
    private UUID commentUUID; // Provides a unique identifier for the comment
    private static UserList userList = UserList.getInstance();

    /**
     * Debugger method, remove in final submission
     */
    public Comment(User user) {
        this.comment = "Test comment";
        this.user = userList.findUser(user);
        this.commentUUID = generateUUID();
        this.moreComments = new ArrayList<>();
        Comment testMoreComments = new Comment("Test more comments", user);
        testMoreComments.moreComments.add(testMoreComments);
        moreComments.add(testMoreComments);
    }

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
     * Constructor to take in JSON file contents in Comment object
     * 
     * @param comment
     */
    public Comment(UUID commentID, String comment, User user, ArrayList<Comment> moreComments) {
        this.commentUUID = commentID;
        this.comment = comment;
        this.user = user;
        this.moreComments = moreComments;
    }

    /**
     * Constructor to take in JSON file contents in Comment object without
     * moreComments
     * 
     * @param comment
     */
    public Comment(UUID commentID, String comment, User user) {
        this.commentUUID = commentID;
        this.comment = comment;
        this.user = user;
        this.moreComments = new ArrayList<>();
    }

    /**
     * Generates a unique UUID for the comment.
     *
     * @return The generated UUID.
     */
    private UUID generateUUID() {
        // UUID generation logic (to be implemented)
        // This method should return a unique UUID for the comment
        return UUID.randomUUID(); // Replace with actual logic
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Comment> getMoreComments() {
        return this.moreComments;
    }

    public void setMoreComments(ArrayList<Comment> moreComments) {
        this.moreComments = moreComments;
    }

    public UUID getCommentUUID() {
        return this.commentUUID;
    }

    public void setCommentUUID(UUID commentUUID) {
        this.commentUUID = commentUUID;
    }

    /**
     * Returns a string representation of the Comment object.
     *
     * @return A string representing the Comment object.
     */
    @Override // TODO: Include sub-comments within moreComments Comments
    public String toString() {
        String commentsToString = "";
        int commentIterator = 0;
        for (Comment comment : moreComments) { 
            commentsToString = commentsToString + (commentIterator < 1 ? "\t[Comment]: " : "\n\t[Comment]: ")
                    + comment.getComment() + "\n" 
                    + "\t[User]: " + comment.getUser().getUsername();
            commentIterator++;
        }
        return "[Comment]: " + comment + "\n"
                + "[User]: " + this.user + "\n" + commentsToString; // TODO: Use UserList to access username instead of
                                                                    // userUUID
    }
}
