package scrumprojectcode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a comment in a scrum project.
 */

public class Comment {

    private String comment;
    private User user;
    private ArrayList<Comment> moreComments;
    private UUID commentUUID;

    /**
     * Constructor to create a Comment object.
     *
     * @param comment The comment text.
     * @param user    The user associated with the comment.
     */
    public Comment(String comment, User user) {
        setComment(comment);
        setUser(user);
        setCommentUUID(generateUUID());
        this.moreComments = new ArrayList<>();
    }

    /**
     * Constructor to take in JSON file contents in Comment object
     * 
     * @param comment
     */
    public Comment(UUID commentID, User user, String comment, ArrayList<Comment> moreComments) {
        setCommentUUID(commentID);
        setComment(comment);
        setUser(user);
        setMoreComments(moreComments);
    }

    /**
     * Constructor to take in JSON file contents in Comment object without
     * moreComments
     * 
     * @param comment
     */
    public Comment(UUID commentID, User user, String comment) {
        setCommentUUID(commentID);
        setComment(comment);
        setUser(user);
        this.moreComments = new ArrayList<>();
    }

    /**
     * Generates a unique UUID for the comment.
     *
     * @return The generated UUID.
     */
    private UUID generateUUID() {
        return UUID.randomUUID();
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

    @Override
    public String toString() {
        if (this.comment == null)
            return "No comments for this task";
        String commentsToString = "";
        for (Comment comment : moreComments) {
            String username = comment.getUser().getUsername();
            if (username != null) {
                commentsToString = printMoreComments(commentsToString, comment);
            }
        }
        return "[Comment]: " + this.comment + "\n"
                + "[User]: " + this.user.getUsername() + "\n" + commentsToString;
    }

    /**
     * Returns a string representation of the Comment's ArrayList<Comment>
     * moreComments.
     *
     * @return A string representing the reply comments for a Comment
     */
    public String printMoreComments(String moreCommentsToString, Comment moreComment) {
        int commentIterator = 0;
        moreCommentsToString = moreCommentsToString + (commentIterator < 1 ? "\t[Comment]: " : "\n\t[Comment]: ")
                + moreComment.getComment() + "\n"
                + "\t[User]: " + moreComment.getUser().getUsername();
        commentIterator++;
        for (Comment comment : moreComment.moreComments)
            moreCommentsToString = moreCommentsToString + printMoreComments(moreCommentsToString, comment);
        return moreCommentsToString;
    }

}
