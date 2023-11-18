package com.example.clouddog.message.application;

import com.example.clouddog.board.exception.NotFoundMemberException;
import com.example.clouddog.member.domain.Member;
import com.example.clouddog.member.domain.repository.MemberRepository;
import com.example.clouddog.message.api.dto.MessageReqDto;
import com.example.clouddog.message.api.dto.MessageResDto;
import com.example.clouddog.message.domain.Message;
import com.example.clouddog.message.domain.repository.MessageRepository;
import com.example.clouddog.message.exception.NotFoundMessageException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public MessageService(MessageRepository messageRepository, MemberRepository memberRepository) {
        this.messageRepository = messageRepository;
        this.memberRepository = memberRepository;
    }

    //메시지 전부 불러오기
    public List<MessageResDto> messageFind(Long memberId) {
        List<MessageResDto> returnList = new ArrayList<>();
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);

        for (Message message : messageRepository.findByMember(member)) {
            MessageResDto messageDto = new MessageResDto(
                    message.getMessageId(),
                    message.getMessageContent(),
                    message.getMessageTime()
            );
            returnList.add(messageDto);
        }
        
        return returnList;
    }

    //메세지 등록
    @Transactional
    public void messageSave(MessageReqDto messageDto) {
        Member member = memberRepository.findById(messageDto.getMemberId()).orElseThrow(NotFoundMessageException::new);
        Message message = new Message(member, messageDto.getMsgContent());
        messageRepository.save(message);
    }

    //메세지 수정
    @Transactional
    public void messageUpdate(Long msgId, MessageReqDto updateMsg) {
        Message message = messageRepository.findById(msgId).orElseThrow(NotFoundMessageException::new);
        message.update(updateMsg);
    }

    //메세지 삭제
    @Transactional
    public void messageDelete(Long msgId) {
        messageRepository.deleteById(msgId);
    }
}
