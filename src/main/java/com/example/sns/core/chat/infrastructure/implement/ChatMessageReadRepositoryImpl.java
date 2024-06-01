package com.example.sns.core.chat.infrastructure.implement;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.infrastructure.entity.ChatMessageEntity;
import com.example.sns.core.chat.infrastructure.queryDsl.ChatMessageQueryDslRepository;
import com.example.sns.core.chat.domain.service.port.ChatMessageReadRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatMessageReadRepositoryImpl implements ChatMessageReadRepository {
    private final ChatMessageQueryDslRepository queryDslRepository;

    @Override
    public Optional<ChatMessage> findById(Long messageId) {
        return queryDslRepository.findById(messageId)
                .map(ChatMessageEntity::toModel);
    }

    @Override
    public Optional<List<ChatMessage>> findByChatRoomId(Long chatRoomId) {
        List<ChatMessageEntity> entities = queryDslRepository.findByChatRoomId(chatRoomId)
                .orElse(Collections.emptyList());
        return Optional.of(entities.stream().map(ChatMessageEntity::toModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<ChatMessage>> findUnreadMessagesInChatRoom(Long chatRoomId, Long userId) {
        List<ChatMessageEntity> entities = queryDslRepository.findUnreadMessagesInChatRoom(chatRoomId, userId)
                .orElse(Collections.emptyList());
        return Optional.of(entities.stream()
                .map(ChatMessageEntity::toModel)
                .collect(Collectors.toList()));
    }
}
