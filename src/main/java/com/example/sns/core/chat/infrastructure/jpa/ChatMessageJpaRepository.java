package com.example.sns.core.chat.infrastructure.jpa;

import com.example.sns.core.chat.infrastructure.entity.ChatMessageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageJpaRepository extends JpaRepository<ChatMessageEntity, Long> {
    List<ChatMessageEntity> findByChatRoomId(Long chatRoomId);
}
