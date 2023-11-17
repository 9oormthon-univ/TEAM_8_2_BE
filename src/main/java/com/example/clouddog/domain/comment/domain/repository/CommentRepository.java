package com.example.clouddog.domain.comment.domain.repository;

import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}
