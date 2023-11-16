package com.example.clouddog.domain.message.api;

import com.example.clouddog.domain.message.application.MessageService;
import com.example.clouddog.domain.message.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageService messageService;
    public MessageController(MessageService messageService){
        this.messageService=messageService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages")
    public List<Message> findAllMessages(){
        return messageService.messageFind();
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/messages")
    public ResponseEntity<String> addMessage(@RequestBody MessageDto messageDto){
        messageService.messageSave(messageDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/messages/{msgId}")
    public ResponseEntity<String> updateMessage(@PathVariable Long msgId, @RequestBody MessageDto msgDto){
        messageService.messageUpdate(msgId, msgDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/{msgId}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long msgId){
        messageService.messageDelete(msgId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
