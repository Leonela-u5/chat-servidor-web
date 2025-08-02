package com.Web_Chat.Chat.chat;

import com.Web_Chat.Chat.model.ChatMessageEntity;
import com.Web_Chat.Chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatRestController {

    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/messages")
    public List<ChatMessageEntity> getAllMessages() {
        return chatMessageRepository.findAll();
    }
}
