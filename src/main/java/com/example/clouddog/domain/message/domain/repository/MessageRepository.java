package com.example.clouddog.domain.message.domain.repository;

import com.example.clouddog.domain.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
