package com.example.clouddog.domain.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController//여기에 있으니까 아래 @RequestBody 다 생략해도 되나
@RequestMapping("/api")
public class MessageController {
    /**
     * Message
     * 메세지 조회: GET '/messages'
     * 메세지 등록: POST '/messages/{msgText}/{msgTime}'메세지 텍스트/시간정보 ->messageId 생성필요 응답으로 줌
     * 메세지 수정?: PATCH '/messages/{msgId}/{msgText}'
     * 삭제,,,는?
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
