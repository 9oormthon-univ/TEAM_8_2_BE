package com.example.clouddog.domain.message.domain;


import com.example.clouddog.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String messageContent;

    private String messageTime;

    public Message(Member member, String messageContent) {
        this.member=member;
        this.messageContent=messageContent;
        LocalDateTime msgTime = LocalDateTime.now();
        this.messageTime=msgTime.toString();
    }
    public void update(String msgContent){
        this.messageContent=msgContent;
        LocalDateTime msgTime = LocalDateTime.now();
        this.messageTime=msgTime.toString();
    }
}
