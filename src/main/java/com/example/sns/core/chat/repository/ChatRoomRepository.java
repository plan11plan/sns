package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.domain.ChatRoom;
import java.util.Optional;

public interface ChatRoomRepository {
    ChatRoom save(ChatRoom chatRoom);
    Optional<ChatRoom> findByUserIds(Long userId1, Long userId2);
    void deleteById(Long chatRoomId);
    Optional<ChatRoom> findById(Long chatRoomId);
}