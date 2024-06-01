package com.example.sns.core.chat.domain.service.port;

import com.example.sns.core.chat.domain.ChatMessage;

public interface ChatMessageWriteRepository {
    ChatMessage save(ChatMessage chatMessage);
    void deleteById(Long messageId);


}
