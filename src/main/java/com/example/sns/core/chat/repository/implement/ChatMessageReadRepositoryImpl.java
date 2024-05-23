package com.example.sns.core.chat.repository.implement;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.repository.entity.ChatMessageEntity;
import com.example.sns.core.chat.repository.jpa.ChatMessageJpaRepository;
import com.example.sns.core.chat.service.port.ChatMessageReadRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatMessageReadRepositoryImpl implements ChatMessageReadRepository {
    private final ChatMessageJpaRepository chatMessageJpaRepository;


    @Override
    public Optional<ChatMessage> findById(Long messageId) {
        return chatMessageJpaRepository.findById(messageId).map(ChatMessageEntity::toModel);
    }

    @Override
    public Optional<List<ChatMessage>> findByChatRoomId(Long chatRoomId) {
        List<ChatMessage> chatMessages = chatMessageJpaRepository.findByChatRoomId(chatRoomId).stream()
                .map(ChatMessageEntity::toModel)
                .collect(Collectors.toList());
        return chatMessages.isEmpty() ? Optional.empty() : Optional.of(chatMessages);
    }

}
