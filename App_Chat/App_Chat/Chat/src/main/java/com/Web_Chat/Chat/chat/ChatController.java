package com.Web_Chat.Chat.chat;

import com.Web_Chat.Chat.model.ChatMessageEntity;
import com.Web_Chat.Chat.model.UserEntity;
import com.Web_Chat.Chat.repository.ChatMessageRepository;
import com.Web_Chat.Chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        // Guardar usuario si no existe
        userRepository.findByUsername(chatMessage.getSender())
                .orElseGet(() -> userRepository.save(
                        UserEntity.builder()
                                .username(chatMessage.getSender())
                                .createdAt(LocalDateTime.now())
                                .build()
                ));

        saveMessage(chatMessage);
        return chatMessage;
    }

    private void saveMessage(ChatMessage chatMessage) {
        ChatMessageEntity entity = ChatMessageEntity.builder()
                .sender(chatMessage.getSender())
                .content(chatMessage.getContent())
                .type(chatMessage.getType().name())
                .timestamp(LocalDateTime.now())
                .build();
        chatMessageRepository.save(entity);
    }
}
