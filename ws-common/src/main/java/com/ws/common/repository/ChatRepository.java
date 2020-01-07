package com.ws.common.repository;

import com.ws.common.entity.ChatMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Integer> {
}
