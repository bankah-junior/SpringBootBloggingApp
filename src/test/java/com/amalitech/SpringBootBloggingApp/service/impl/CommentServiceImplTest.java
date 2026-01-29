package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.Comment;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.CommentRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepositoryImpl commentRepository;

    @Mock
    private Cache<String, User> userCache;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment testComment;
    private Comment testComment2;
    private List<Comment> testComments;

    @BeforeEach
    void setUp() {
        testComment = new Comment("697349d17b196ad927aa89fa", "697349d17b196ad927aa89fc", "697349d17b196ad927aa89fe", "This is a test comment", 123456789L, null);
        testComment2 = new Comment("697349d17b196ad927aa89fb", "697349d17b196ad927aa89fd", "697349d17b196ad927aa89ff", "This is another test comment", 123456790L, null);
        testComments = List.of(testComment, testComment2);
    }

    @Test
    @DisplayName("Create Valid Comment Returns Comment")
    void create_ValidComment_ReturnsComment() {
        when(commentRepository.save(any(Comment.class))).thenReturn(testComment);

        Comment result = commentService.create(testComment);

        assertNotNull(result);
        assertEquals(testComment.getId(), result.getId());
        assertEquals(testComment.getPostId(), result.getPostId());
        assertEquals(testComment.getUserId(), result.getUserId());
        assertEquals(testComment.getContent(), result.getContent());
        verify(commentRepository).save(testComment);
    }

    @Test
    @DisplayName("Create Invalid Content Throws UserInputsException")
    void create_InvalidContent_ThrowsUserInputsException() {
        Comment invalidComment = new Comment("697349d17b196ad927aa89fa", "697349d17b196ad927aa89fc", "697349d17b196ad927aa89fe", "", 123456789L, null);

        assertThrows(UserInputsException.class, () -> commentService.create(invalidComment));
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    @DisplayName("Create Null Content Throws UserInputsException")
    void create_NullContent_ThrowsUserInputsException() {
        Comment invalidComment = new Comment("697349d17b196ad927aa89fa", "697349d17b196ad927aa89fc", "697349d17b196ad927aa89fe", null, 123456789L, null);

        assertThrows(UserInputsException.class, () -> commentService.create(invalidComment));
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    @DisplayName("Delete Valid Comment Id Returns True")
    void delete_ValidCommentId_ReturnsTrue() {
        when(commentRepository.deleteById(testComment.getId())).thenReturn(true);

        boolean result = commentService.delete(testComment.getId());

        assertTrue(result);
        verify(commentRepository).deleteById(testComment.getId());
    }

    @Test
    @DisplayName("Delete Invalid Comment Id Throws UserInputsException")
    void delete_InvalidCommentId_ThrowsUserInputsException() {
        assertThrows(UserInputsException.class, () -> commentService.delete("invalid-id"));
        verify(commentRepository, never()).deleteById(anyString());
    }

    @Test
    @DisplayName("Delete Failed Delete Returns False")
    void delete_FailedDelete_ReturnsFalse() {
        when(commentRepository.deleteById("697349d17b196ad927aa8a01")).thenReturn(false);

        boolean result = commentService.delete("697349d17b196ad927aa8a01");

        assertFalse(result);
        verify(commentRepository).deleteById("697349d17b196ad927aa8a01");
    }

    @Test
    @DisplayName("Get By Post Valid Post Id Returns Post Comments")
    void getByPost_ValidPostId_ReturnsPostComments() {
        List<Comment> expectedComments = List.of(testComment);
        when(commentRepository.findByPostId(testComment.getPostId())).thenReturn(expectedComments);

        List<Comment> result = commentService.getByPost(testComment.getPostId());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testComment.getPostId(), result.get(0).getPostId());
        verify(commentRepository).findByPostId(testComment.getPostId());
    }

    @Test
    @DisplayName("Get By Post Invalid Post Id Throws UserInputsException")
    void getByPost_InvalidPostId_ThrowsUserInputsException() {
        assertThrows(UserInputsException.class, () -> commentService.getByPost("invalid-id"));
        verify(commentRepository, never()).findByPostId(anyString());
    }

    @Test
    @DisplayName("Get By Post No Comments Returns Empty List")
    void getByPost_NoComments_ReturnsEmptyList() {
        when(commentRepository.findByPostId("697349d17b196ad927aa8a01")).thenReturn(List.of());

        List<Comment> result = commentService.getByPost("697349d17b196ad927aa8a01");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(commentRepository).findByPostId("697349d17b196ad927aa8a01");
    }

    @Test
    @DisplayName("Get By User Valid User Id Returns User Comments")
    void getByUser_ValidUserId_ReturnsUserComments() {
        List<Comment> expectedComments = List.of(testComment);
        when(commentRepository.findByUserId(testComment.getUserId())).thenReturn(expectedComments);

        List<Comment> result = commentService.getByUser(testComment.getUserId());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testComment.getUserId(), result.get(0).getUserId());
        verify(commentRepository).findByUserId(testComment.getUserId());
    }

    @Test
    @DisplayName("Get By User Invalid User Id Throws UserInputsException")
    void getByUser_InvalidUserId_ThrowsUserInputsException() {
        assertThrows(UserInputsException.class, () -> commentService.getByUser("invalid-id"));
        verify(commentRepository, never()).findByUserId(anyString());
    }

    @Test
    @DisplayName("Get By User No Comments Returns Empty List")
    void getByUser_NoComments_ReturnsEmptyList() {
        when(commentRepository.findByUserId(testComment.getUserId())).thenReturn(List.of());

        List<Comment> result = commentService.getByUser(testComment.getUserId());
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(commentRepository).findByUserId(testComment.getUserId());
    }

    @Test
    @DisplayName("Update Valid Comment Returns True")
    void update_ValidComment_ReturnsTrue() {
        when(commentRepository.update(testComment)).thenReturn(true);

        boolean result = commentService.update(testComment);

        assertTrue(result);
        verify(commentRepository).update(testComment);
    }

    @Test
    @DisplayName("Update Invalid Comment Id Throws UserInputsException")
    void update_InvalidCommentId_ThrowsUserInputsException() {
        Comment invalidComment = new Comment("invalid-id", "post1", "user1", "Valid content", 123456789L, null);

        assertThrows(UserInputsException.class, () -> commentService.update(invalidComment));
        verify(commentRepository, never()).update(any(Comment.class));
    }

    @Test
    @DisplayName("Update Invalid Content Throws UserInputsException")
    void update_InvalidContent_ThrowsUserInputsException() {
        Comment invalidComment = new Comment("1", "post1", "user1", "", 123456789L, null);

        assertThrows(UserInputsException.class, () -> commentService.update(invalidComment));
        verify(commentRepository, never()).update(any(Comment.class));
    }

    @Test
    @DisplayName("Update Failed Update Returns False")
    void update_FailedUpdate_ReturnsFalse() {
        when(commentRepository.update(testComment)).thenReturn(false);

        boolean result = commentService.update(testComment);

        assertFalse(result);
        verify(commentRepository).update(testComment);
    }

    @Test
    @DisplayName("Get All Returns List Of Comments")
    void getAll_ReturnsListOfComments() {
        when(commentRepository.findAll()).thenReturn(testComments);

        List<Comment> result = commentService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testComments.get(0).getId(), result.get(0).getId());
        assertEquals(testComments.get(1).getId(), result.get(1).getId());
        verify(commentRepository).findAll();
    }

    @Test
    @DisplayName("Get All Empty List Returns Empty List")
    void getAll_EmptyList_ReturnsEmptyList() {
        when(commentRepository.findAll()).thenReturn(List.of());

        List<Comment> result = commentService.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(commentRepository).findAll();
    }

    @Test
    @DisplayName("Find By Id Valid Comment Id Returns Comment")
    void findById_ValidCommentId_ReturnsComment() {
        when(commentRepository.findById(testComment.getId())).thenReturn(Optional.of(testComment));

        Comment result = commentService.findById(testComment.getId());
        assertNotNull(result);
        assertEquals(testComment.getId(), result.getId());
        assertEquals(testComment.getPostId(), result.getPostId());
        assertEquals(testComment.getUserId(), result.getUserId());
        verify(commentRepository).findById(testComment.getId());
    }

    @Test
    @DisplayName("Find By Id Invalid Comment Id Throws UserInputsException")
    void findById_InvalidCommentId_ThrowsUserInputsException() {
        assertThrows(UserInputsException.class, () -> commentService.findById("invalid-id"));
        verify(commentRepository, never()).findById(anyString());
    }

    @Test
    @DisplayName("Find By Id Comment Not Found Returns Null")
    void findById_CommentNotFound_ReturnsNull() {
        when(commentRepository.findById("697349d17b196ad927aa8a01")).thenReturn(Optional.empty());

        Comment result = commentService.findById("697349d17b196ad927aa8a01");

        assertNull(result);
        verify(commentRepository).findById("697349d17b196ad927aa8a01");
    }

    @Test
    @DisplayName("Create Comment With Valid Content Calls Repository Save")
    void create_CommentWithValidContent_CallsRepositorySave() {
        Comment validComment = new Comment("697349d17b196ad927aa8a01", "697349d17b196ad927aa8a01", "697349d17b196ad927aa8a02", "Valid comment content", 123456791L, null);
        when(commentRepository.save(validComment)).thenReturn(validComment);

        Comment result = commentService.create(validComment);

        assertNotNull(result);
        verify(commentRepository).save(validComment);
    }

    @Test
    @DisplayName("Update Comment With Valid Id And Content Calls Repository Update")
    void update_CommentWithValidIdAndContent_CallsRepositoryUpdate() {
        Comment updatedComment = new Comment(testComment.getId(), "697349d17b196ad927aa8a01", "697349d17b196ad927aa8a02", "Updated comment content", 123456792L, null);
        when(commentRepository.update(updatedComment)).thenReturn(true);

        boolean result = commentService.update(updatedComment);

        assertTrue(result);
        verify(commentRepository).update(updatedComment);
    }

    @Test
    @DisplayName("Delete Comment With Valid Id Calls Repository DeleteById")
    void delete_CommentWithValidId_CallsRepositoryDeleteById() {
        when(commentRepository.deleteById(testComment.getId())).thenReturn(true);

        boolean result = commentService.delete(testComment.getId());

        assertTrue(result);
        verify(commentRepository).deleteById(testComment.getId());
    }

    @Test
    @DisplayName("Get By Post Valid Post Id Returns List Of Comments")
    void getByPost_CommentWithValidPostId_CallsRepositoryFindByPostId() {
        when(commentRepository.findByPostId(testComment.getPostId())).thenReturn(List.of(testComment));

        List<Comment> result = commentService.getByPost(testComment.getPostId());

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(commentRepository).findByPostId(testComment.getPostId());
    }

    @Test
    @DisplayName("Get By User Valid User Id Returns List Of Comments")
    void getByUser_CommentWithValidUserId_CallsRepositoryFindByUserId() {
        when(commentRepository.findByUserId(testComment.getUserId())).thenReturn(List.of(testComment));

        List<Comment> result = commentService.getByUser(testComment.getUserId());

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(commentRepository).findByUserId(testComment.getUserId());
    }
}