package com.Web_Chat.Chat.repository;

import com.Web_Chat.Chat.model.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
}
