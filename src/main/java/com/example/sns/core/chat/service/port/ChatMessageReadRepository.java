package com.example.sns.core.chat.service.port;

import com.example.sns.core.chat.domain.ChatMessage;
import java.util.List;
import java.util.Optional;

public interface ChatMessageReadRepository {
    Optional<ChatMessage> findById(Long messageId);
    Optional<List<ChatMessage>> findByChatRoomId(Long chatRoomId);
}