package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.UserChatRoomEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRoomJpaRepository extends JpaRepository<UserChatRoomEntity, UUID> {
    List<UserChatRoomEntity> findByUserId(Long userId);
}
