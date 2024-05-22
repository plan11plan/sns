package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.MessageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByChatRoomId(Long chatRoomId);
}