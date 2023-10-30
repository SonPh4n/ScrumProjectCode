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
    /* public Comment(User user) {
        this.comment = "Test comment";
        this.user = userList.findUser(user.getUserUUID());
        this.commentUUID = generateUUID();
        this.moreComments = new ArrayList<>();
        Comment testMoreComments = new Comment("Test more comments", user);
        testMoreComments.moreComments.add(testMoreComments);
        moreComments.add(testMoreComments);
    } */

    /**
     * Constructor to create a Comment object.
     *
     * @param comment The comment text.
     * @param user    The user associated with the comment.
     */
    public Comment(String comment, User user) {
        // Constructor logic
        setComment(comment);
        setUser(user);
        generateUUID(); // Generate a unique UUID for the comment
        this.moreComments = new ArrayList<>(); // Initialize the list for additional comments
    }

    /**
     * Constructor to take in JSON file contents in Comment object
     * 
     * @param comment
     */
    public Comment(UUID commentID, UUID userUUID, String comment, ArrayList<Comment> moreComments) {
        setCommentUUID(commentID);
        setComment(comment);
        User findUser = userList.findUser(userUUID);
        setUser(findUser);
        setMoreComments(moreComments);
        ;
    }

    /**
     * Constructor to take in JSON file contents in Comment object without
     * moreComments
     * 
     * @param comment
     */
    public Comment(UUID commentID, UUID userUUID, String comment) {
        setCommentUUID(commentID);
        setComment(comment);
        User findUser = userList.findUser(userUUID);
        setUser(findUser);
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
        for (Comment comment : moreComments) { // TODO: use UserList to convert getUser to User.username
            commentsToString = printMoreComments(commentsToString, comment);
        }
        return "[Comment]: " + comment + "\n"
                + "[User]: " + this.user + "\n" + commentsToString; // TODO: Use UserList to access username instead of
                                                                    // userUUID
    }

    public String printMoreComments(String moreCommentsToString, Comment moreComment) {
        int commentIterator = 0;
        moreCommentsToString = moreCommentsToString + (commentIterator < 1 ? "\t[Comment]: " : "\n\t[Comment]: ")
                + moreComment.getComment() + "\n"
                + "\t[User]: " + moreComment.getUser();
        commentIterator++;
        for (Comment comment : moreComment.moreComments)
            moreCommentsToString = moreCommentsToString + printMoreComments(moreCommentsToString, comment);
        return moreCommentsToString;
    }

}
