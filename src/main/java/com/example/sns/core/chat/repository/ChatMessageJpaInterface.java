package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.repository.entity.MessageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageJpaInterface extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByChatRoomId(Long chatRoomId);
}
