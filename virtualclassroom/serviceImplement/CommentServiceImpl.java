package ksolve.virtualclassroom.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ksolve.virtualclassroom.entity.Comment;
import ksolve.virtualclassroom.repository.CommentRepository;
import ksolve.virtualclassroom.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public ResponseEntity<String> createComment(Comment comment) {
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully.");
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(comments);
        }
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<Comment> getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return ResponseEntity.ok(comment.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<String> updateComment(Long id, Comment comment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment updatedComment = existingComment.get();
            updatedComment.setText(comment.getText());
            updatedComment.setUser(comment.getUser());
            commentRepository.save(updatedComment);
            return ResponseEntity.ok("Comment updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found.");
        }
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        commentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}