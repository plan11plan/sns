package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.ChatRoomEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomEntity, UUID> {
    List<ChatRoomEntity> findByUserId1OrUserId2(Long userId1, Long userId2);
}