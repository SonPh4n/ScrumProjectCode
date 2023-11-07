package scrumprojectcode.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrumprojectcode.Comment;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests for the Comment class.
 * It tests the constructor, setters, getters, and toString methods of the Comment class.
 * The tests include checking if the comment and user UUID are set correctly, 
 * if the comment can be set to null, if the user UUID can be set to null, 
 * if the comment can be updated, if the user UUID can be updated, 
 * if more comments can be added, if the comment UUID can be set, 
 * if the toString method returns the expected string, 
 * and if the printMoreComments method returns the expected string.
 */
class CommentTest {

    private Comment comment;
    private UUID userUUID;
    private String commentText;

    @BeforeEach
    void setUp() {
        userUUID = UUID.randomUUID();
        commentText = "This is a comment";
        comment = new Comment(commentText, userUUID);
    }

    @Test
    void testConstructor() {
        assertNotNull(comment);
        assertEquals(commentText, comment.getComment());
        assertEquals(userUUID, comment.getUserUUID());
    }

    @Test
    void testConstructorWithNullComment() {
        Comment nullComment = new Comment(null, userUUID);
        assertNull(nullComment.getComment());
    }

    @Test
    void testConstructorWithNullUser() {
        Comment nullUserComment = new Comment(commentText, null);
        assertNull(nullUserComment.getUserUUID());
    }

    @Test
    void testSetComment() {
        String newCommentText = "This is a new comment";
        comment.setComment(newCommentText);
        assertEquals(newCommentText, comment.getComment());
    }

    @Test
    void testSetUser() {
        UUID newUserUUID = UUID.randomUUID();
        comment.setUser(newUserUUID);
        assertEquals(newUserUUID, comment.getUserUUID());
    }

    @Test
    void testSetMoreComments() {
        ArrayList<Comment> moreComments = new ArrayList<>();
        moreComments.add(new Comment("Sub comment", UUID.randomUUID()));
        comment.setMoreComments(moreComments);
        assertEquals(moreComments, comment.getMoreComments());
    }

    @Test
    void testSetCommentUUID() {
        UUID newCommentUUID = UUID.randomUUID();
        comment.setCommentUUID(newCommentUUID);
        assertEquals(newCommentUUID, comment.getCommentUUID());
    }

    @Test
    void testToString() {
        String expectedString = "[Comment]: " + commentText + "\n"
                + "[User]: " + comment.getUser().getUsername() + "\n";
        assertEquals(expectedString, comment.toString());
    }

    @Test
    void testPrintMoreComments() {
        ArrayList<Comment> moreComments = new ArrayList<>();
        Comment subComment = new Comment("Sub comment", UUID.randomUUID());
        moreComments.add(subComment);
        comment.setMoreComments(moreComments);
        String expectedString = "\t[Comment]: " + subComment.getComment() + "\n"
                + "\t[User]: " + subComment.getUser().getUsername() + "\n";
        assertEquals(expectedString, comment.printMoreComments("", subComment));
    }
}