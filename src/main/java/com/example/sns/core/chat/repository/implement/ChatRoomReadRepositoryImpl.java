package com.example.sns.core.chat.repository.implement;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.repository.entity.ChatRoomEntity;
import com.example.sns.core.chat.repository.queryDsl.ChatRoomQueryDslRepository;
import com.example.sns.core.chat.service.port.ChatRoomReadRepository;
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
    public List<ChatRoom> findByUserId(Long userId) {
        return queryDslRepository.findByUserId(userId).stream()
                .map(ChatRoomEntity::toModel)
                .collect(Collectors.toList());
    }
}
