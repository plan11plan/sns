package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.domain.Message;
import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository {
    Message save(Message message);
    Optional<Message> findById(Long messageId);
    void deleteById(Long messageId);
    List<Message> findByChatRoomId(Long chatRoomId);
}