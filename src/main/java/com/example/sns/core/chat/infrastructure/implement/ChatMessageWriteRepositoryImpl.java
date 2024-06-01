package com.example.sns.core.chat.infrastructure.implement;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.infrastructure.entity.ChatMessageEntity;
import com.example.sns.core.chat.infrastructure.jpa.ChatMessageJpaRepository;
import com.example.sns.core.chat.domain.service.port.ChatMessageWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatMessageWriteRepositoryImpl implements ChatMessageWriteRepository {
    private final ChatMessageJpaRepository chatMessageJpaRepository;
    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageJpaRepository.save(ChatMessageEntity.from(chatMessage)).toModel();
    }

    public void deleteById(Long chatRoomId) {
        chatMessageJpaRepository.deleteById(chatRoomId);
    }


}
