package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.repository.entity.ChatMessageEntity;
import com.example.sns.core.chat.repository.queryDsl.ChatMessageQueryDslRepository;
import com.example.sns.core.chat.service.port.ChatMessageReadRepository;
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
        List<ChatMessageEntity> entities = queryDslRepository.findByChatRoomId(chatRoomId);
        List<ChatMessage> messages = entities.stream().map(ChatMessageEntity::toModel).collect(Collectors.toList());
        return Optional.ofNullable(messages);
    }

    @Override
    public List<ChatMessage> findUnreadMessagesInChatRoom(Long chatRoomId, Long userId) {
        return queryDslRepository.findUnreadMessagesInChatRoom(chatRoomId, userId).stream()
                .map(ChatMessageEntity::toModel)
                .collect(Collectors.toList());
    }
}
