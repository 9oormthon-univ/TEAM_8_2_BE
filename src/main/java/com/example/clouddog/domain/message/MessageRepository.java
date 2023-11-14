package com.example.clouddog.domain.message;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MessageRepository {
    private static final Map<Long, Message> messages = new ConcurrentHashMap<>();
    private static long sequence = 0L;//static

    public Message save(Message msg){
        msg.setMsgId(++sequence);
        msg.setMsgTime(LocalDate.now());
        messages.put(msg.getMsgId(), msg);
        return msg;
    }
    public Message findById(Long id){
        return messages.get(id);
    }
    public List<Message> findAll(){
        return new ArrayList<>(messages.values());
    }
    public void update(Long msgId, MessageDto updateMsg){
        Message findMsg = findById(msgId);
        findMsg.setMsgContent(updateMsg.getMsgContent());
        findMsg.setMsgTime(LocalDate.now());
    }
    public void delete(Long msgId){
        messages.remove(msgId);
    }
}
