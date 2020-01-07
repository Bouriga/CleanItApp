package com.ws.common.service.impl;


import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.ws.common.entity.ChatMessage;
import com.ws.common.entity.Object;
import com.ws.common.repository.ChatRepository;
import com.ws.common.repository.ObjectRepository;
import com.ws.common.service.ChatService;
import com.ws.common.service.ObjectService;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatrepository;


    @Autowired
    private ChatService chatService;




    @Autowired
    public ChatServiceImpl(ChatRepository chatrepository) {
        this.chatrepository = chatrepository;
    }

    @Override
    public List<ChatMessage> getChats() {
        return chatrepository.findAll();
    }

    @Override
    public void createChat(ChatMessage chatMessage) {
        chatrepository.save(chatMessage);
    }


    @Override
    public ChatMessage addChat(ChatMessage chatMessage) {

        chatService.createChat(chatMessage);

        return chatMessage;

    }


}
