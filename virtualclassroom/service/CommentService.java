package ksolve.virtualclassroom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Comment;

public interface CommentService {
	ResponseEntity<String> createComment(Comment comment);
	ResponseEntity<List<Comment>> getAllComments();  
    ResponseEntity<Comment> getCommentById(Long id);  
    ResponseEntity<String> updateComment(Long id, Comment comment);
    ResponseEntity<Void> deleteComment(Long id);
}
