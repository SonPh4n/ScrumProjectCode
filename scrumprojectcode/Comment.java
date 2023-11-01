package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a comment in a scrum project.
 */
public class Comment {

    // Attributes
    private String comment; // Stores the comment text
    private UUID user; // Represents the user associated with the comment
    private ArrayList<Comment> moreComments; // Stores Array of comments
    private UUID commentUUID; // Provides a unique identifier for the comment
    private static UserList userList = UserList.getInstance();

    /**
     * Constructor to create a Comment object.
     *
     * @param comment The comment text.
     * @param user    The user associated with the comment.
     */
    public Comment(String comment, UUID user) {
        setComment(comment);
        setUser(user);
        generateUUID(); 
        this.moreComments = new ArrayList<>();
    }

    /**
     * Constructor to take in JSON file contents in Comment object
     * 
     * @param comment
     */
    public Comment(UUID commentID, UUID userUUID, String comment, ArrayList<Comment> moreComments) {
        setCommentUUID(commentID);
        setComment(comment);
        setUser(userUUID);
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
        setUser(userUUID);
        this.moreComments = new ArrayList<>();
    }

    /**
     * Generates a unique UUID for the comment.
     *
     * @return The generated UUID.
     */
    private void generateUUID() {
        this.commentUUID = UUID.randomUUID();
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return userList.findUser(this.user);
    }

    public UUID getUserUUID() {
        return this.user;
    }

    public void setUser(UUID user) {
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
        if (this.comment == null)
            return "No comments for this task";
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
