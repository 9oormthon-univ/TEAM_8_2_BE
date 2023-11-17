package com.example.clouddog.domain.message.domain;


import com.example.clouddog.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
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

    @Temporal(TemporalType.DATE)
    private LocalDate messageTime;

    public Message(String messageContent) {
        this.messageContent=messageContent;
        this.messageTime=LocalDate.now();
    }
    public void update(String msgContent){
        this.messageContent=msgContent;
        this.messageTime=LocalDate.now();
    }
}
