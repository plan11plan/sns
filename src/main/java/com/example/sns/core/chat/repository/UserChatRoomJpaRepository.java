package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.UserChatRoomEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRoomJpaRepository extends JpaRepository<UserChatRoomEntity, Long> {
    List<UserChatRoomEntity> findByUserId(Long userId);
}
