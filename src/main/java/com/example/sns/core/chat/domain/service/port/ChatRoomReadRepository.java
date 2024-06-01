package com.example.sns.core.chat.domain.service.port;

import com.example.sns.core.chat.domain.ChatRoom;
import java.util.List;
import java.util.Optional;

public interface ChatRoomReadRepository {
    Optional<ChatRoom> findByUserIds(Long userId1, Long userId2);
    Optional<ChatRoom> findById(Long chatRoomId);
    Optional<List<ChatRoom>> findByUserId(Long userId);

}