package com.example.sns.core.chat.repository.jpa;

import com.example.sns.core.chat.repository.entity.ChatRoomEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomEntity, Long> {
    Optional<ChatRoomEntity> findByUserId1AndUserId2(Long userId1, Long userId2);
}
