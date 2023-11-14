package com.example.clouddog.domain.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MessageController {
    /**
     * Message
     * 메세지 조회
     * 메세지 등록
     * 메세지 수정
     * 메시지 삭제
     */
    private MessageRepository messageRepository = new MessageRepository();
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages")
    public List<Message> messages(){
        return messageRepository.findAll();
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/messages")
    public Long addMessage(@RequestBody Message msgData){
        Message msg = messageRepository.save(msgData);

        log.info(msg.getMsgContent());
        return msg.getMsgId();
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/messages/{msgId}")
    public void updateMessage(@PathVariable Long msgId, @RequestBody MessageDto msgDto){
        messageRepository.update(msgId, msgDto);
        log.info(messageRepository.findById(msgId).getMsgContent());
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/{msgId}")
    public void deleteMessage(@PathVariable Long msgId){
        messageRepository.delete(msgId);
    }

}
