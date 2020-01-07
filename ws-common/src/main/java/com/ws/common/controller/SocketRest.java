package com.ws.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.common.entity.ChatMessage;
import com.ws.common.entity.Object;
import com.ws.common.service.ChatService;
import com.ws.common.service.ObjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping(value = "/api/socket")
@CrossOrigin("*")
public class SocketRest {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatService chatService;


    @PostMapping
    @ApiOperation("Send Massage")
    public ChatMessage sendMessage(@RequestBody ChatMessage message){
        //if(message.equals("message")){ message.getReceiver().equals("receiver") &&
            if( message.getReceiver()!=null && !message.getReceiver().equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+message.getReceiver(),message);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+message.getSender(),message);
            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",message);
            }

            //return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
        return chatService.addChat(message);
        //}
        //return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/allChat")
    @ApiOperation("Gets all chat")
    public List<ChatMessage> findAll() {
        return chatService.getChats();
    }

    @MessageMapping("/send/message")
    public Map<String, String> broadcastNotification(String message){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted = null;
        try {
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            messageConverted = null;
        }
        if(messageConverted!=null){
            if(messageConverted.containsKey("toId") && messageConverted.get("toId")!=null && !messageConverted.get("toId").equals("")){
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("toId"),messageConverted);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/"+messageConverted.get("fromId"),message);
            }else{
                this.simpMessagingTemplate.convertAndSend("/socket-publisher",messageConverted);
            }
        }
        return messageConverted;
    }

}