package com.ws.common.service;

import com.ws.common.entity.ChatMessage;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChatService {
    List<ChatMessage> getChats();

    void createChat(ChatMessage chatMessage);

    ChatMessage addChat(@ModelAttribute ChatMessage chatMessage);
}
