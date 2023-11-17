package com.example.clouddog.domain.message.application;

import com.example.clouddog.domain.message.api.dto.MessageReqDto;
import com.example.clouddog.domain.message.api.dto.MessageResDto;
import com.example.clouddog.domain.message.domain.Message;
import com.example.clouddog.domain.message.domain.repository.MessageRepository;
import com.example.clouddog.domain.message.exception.NotFoundMessageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository=messageRepository;
    }

    //메시지 전부 불러오기
    public List<MessageResDto> messageFind(){
        List<MessageResDto> returnList = new ArrayList<>();
        for (Message message: messageRepository.findAll()) {
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
    public void messageSave(MessageReqDto messageDto){
        Message message = new Message(messageDto.getMsgContent());
        messageRepository.save(message);
    }
    //메세지 수정
    public void messageUpdate(Long msgId, MessageReqDto updateMsg){
        messageRepository.findById(msgId).orElseThrow(NotFoundMessageException::new).update(updateMsg.getMsgContent());
    }
    //메세지 삭제
    public void messageDelete(Long msgId){
        messageRepository.deleteById(msgId);
    }
}
