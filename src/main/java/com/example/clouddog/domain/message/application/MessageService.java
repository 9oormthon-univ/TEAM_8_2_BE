package com.example.clouddog.domain.message.application;

import com.example.clouddog.domain.message.api.MessageDto;
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

//페이징x 확인용
    public List<Message> messageFind(){
        return new ArrayList<>(messageRepository.findAll());
    }

    //메세지 등록
    public void messageSave(MessageDto messageDto){
        Message message = new Message(messageDto.getMsgContent());
        messageRepository.save(message);
    }
    //메세지 수정
    public void messageUpdate(Long msgId, MessageDto updateMsg){
        Message message = messageRepository.findById(msgId).orElseThrow(NotFoundMessageException::new);
        message.update(updateMsg.getMsgContent());
    }
    //메세지 삭제
    public void messageDelete(Long msgId){
        messageRepository.deleteById(msgId);
    }
}
