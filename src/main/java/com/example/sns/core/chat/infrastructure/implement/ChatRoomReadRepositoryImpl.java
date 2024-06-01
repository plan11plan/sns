package com.example.sns.core.chat.infrastructure.implement;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.infrastructure.entity.ChatRoomEntity;
import com.example.sns.core.chat.infrastructure.queryDsl.ChatRoomQueryDslRepository;
import com.example.sns.core.chat.domain.service.port.ChatRoomReadRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRoomReadRepositoryImpl implements ChatRoomReadRepository {
    private final ChatRoomQueryDslRepository queryDslRepository;

    @Override
    public Optional<ChatRoom> findByUserIds(Long userId1, Long userId2) {
        return queryDslRepository.findByUserIds(userId1, userId2)
                .map(ChatRoomEntity::toModel);
    }

    @Override
    public Optional<ChatRoom> findById(Long chatRoomId) {
        return queryDslRepository.findById(chatRoomId)
                .map(ChatRoomEntity::toModel);
    }


    @Override
    public Optional<List<ChatRoom>> findByUserId(Long userId) {
        List<ChatRoomEntity> chatRoomEntities = queryDslRepository.findByUserId(userId).orElse(
                Collections.emptyList());
        return Optional.of(chatRoomEntities.stream()
                .map(ChatRoomEntity::toModel)
                .collect(Collectors.toList()));
    }
}
