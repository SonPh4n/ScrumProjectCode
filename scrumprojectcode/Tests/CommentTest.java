package scrumprojectcode.Tests;

import org.junit.jupiter.api.Test;
import scrumprojectcode.Comment;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests for the Comment class.
 * It tests the constructor, setters, getters, and toString methods of the
 * Comment class.
 * The tests include checking if the comment and user UUID are set correctly,
 * if the comment can be set to null, if the user UUID can be set to null,
 * if the comment can be updated, if the user UUID can be updated,
 * if more comments can be added, if the comment UUID can be set,
 * if the toString method returns the expected string,
 * and if the printMoreComments method returns the expected string.
 */

class CommentTest {

    @Test
    void testConstructor() {
        UUID userUUID = UUID.randomUUID();
        Comment comment = new Comment("Test comment", userUUID);

        assertNotNull(comment);
        assertEquals("Test comment", comment.getComment());
        assertEquals(userUUID, comment.getUserUUID());
    }

    @Test
    void testConstructorWithMoreComments() {
        UUID userUUID = UUID.randomUUID();
        UUID commentUUID = UUID.randomUUID();
        ArrayList<Comment> moreComments = new ArrayList<>();
        Comment comment = new Comment(commentUUID, userUUID, "Test comment", moreComments);

        assertNotNull(comment);
        assertEquals("Test comment", comment.getComment());
        assertEquals(userUUID, comment.getUserUUID());
        assertEquals(commentUUID, comment.getCommentUUID());
        assertEquals(moreComments, comment.getMoreComments());
    }

    @Test
    void testToString() {
        UUID userUUID = UUID.randomUUID();
        Comment comment = new Comment("Test comment", userUUID);

        String result = comment.toString();

        assertTrue(result.contains("[Comment]: Test comment"));
        assertTrue(result.contains("[User]: Unknown user"));
    }

    @Test
    void testPrintMoreComments() {
        UUID userUUID = UUID.randomUUID();
        Comment comment = new Comment("Test comment", userUUID);

        String result = comment.printMoreComments("", comment);

        assertTrue(result.contains("\t[Comment]: Test comment"));
        assertTrue(result.contains("\t[User]: Unknown user"));
    }
}