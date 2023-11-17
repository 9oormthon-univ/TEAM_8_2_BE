package com.example.clouddog.domain.board.domain.repository;

import com.example.clouddog.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoardTag(int tagNumber);
}
