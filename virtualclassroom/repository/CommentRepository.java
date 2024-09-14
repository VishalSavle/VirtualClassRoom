package ksolve.virtualclassroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ksolve.virtualclassroom.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

