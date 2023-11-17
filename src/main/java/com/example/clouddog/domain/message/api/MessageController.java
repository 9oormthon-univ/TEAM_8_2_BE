package com.example.clouddog.domain.message.api;

import com.example.clouddog.domain.message.api.dto.MessageReqDto;
import com.example.clouddog.domain.message.api.dto.MessageResDto;
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


    //메시지 불러오기
    @GetMapping("/{memberId}/messages")
    public ResponseEntity<List<MessageResDto>> findAllMessages(@PathVariable Long memberId){

        return new ResponseEntity<>(messageService.messageFind(memberId), HttpStatus.OK);
    }

    //메시지 작성
    @PostMapping("/messages")
    public ResponseEntity<String> addMessage(@RequestBody MessageReqDto messageDto){
        messageService.messageSave(messageDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //메시지 수정
    @PatchMapping("/messages/{msgId}")
    public ResponseEntity<String> updateMessage(@PathVariable Long msgId, @RequestBody MessageReqDto msgDto){
        messageService.messageUpdate(msgId, msgDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //메시지 삭제
    @DeleteMapping("/messages/{msgId}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long msgId){
        messageService.messageDelete(msgId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
