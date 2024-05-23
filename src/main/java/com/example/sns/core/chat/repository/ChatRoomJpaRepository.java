package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.repository.entity.ChatRoomEntity;
import com.example.sns.core.chat.repository.mapper.ChatRoomMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRoomJpaRepository implements ChatRoomRepository {
    private final ChatRoomJpaInterface jpaInterface;

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        ChatRoomEntity entity = ChatRoomMapper.toEntity(chatRoom);
        return ChatRoomMapper.toDomain(jpaInterface.save(entity));
    }

    @Override
    public Optional<ChatRoom> findByUserIds(Long userId1, Long userId2) {
        return jpaInterface.findByUserId1AndUserId2(userId1, userId2)
                .map(ChatRoomMapper::toDomain);
    }

    @Override
    public void deleteById(Long chatRoomId) {
        jpaInterface.deleteById(chatRoomId);
    }

    @Override
    public Optional<ChatRoom> findById(Long chatRoomId) {
        return jpaInterface.findById(chatRoomId)
                .map(ChatRoomMapper::toDomain);
    }
}