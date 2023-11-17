package com.example.clouddog.domain.message.domain.repository;

import com.example.clouddog.domain.board.domain.Board;
import com.example.clouddog.domain.comment.domain.Comment;
import com.example.clouddog.domain.message.domain.Message;
import com.example.clouddog.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByMember(Member member);
}
