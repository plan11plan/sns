package com.example.sns.core.chat.repository;

import com.example.sns.core.chat.domain.Message;
import com.example.sns.core.chat.repository.entity.MessageEntity;
import com.example.sns.core.chat.repository.mapper.MessageMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatMessageJpaRepository implements ChatMessageRepository {
    private final ChatMessageJpaInterface jpaInterface;

    @Override
    public Message save(Message message) {
        MessageEntity entity = MessageMapper.toEntity(message);
        return MessageMapper.toDomain(jpaInterface.save(entity));
    }

    @Override
    public Optional<Message> findById(Long messageId) {
        return jpaInterface.findById(messageId)
                .map(MessageMapper::toDomain);
    }

    @Override
    public void deleteById(Long messageId) {
        jpaInterface.deleteById(messageId);
    }

    @Override
    public List<Message> findByChatRoomId(Long chatRoomId) {
        return jpaInterface.findByChatRoomId(chatRoomId).stream()
                .map(MessageMapper::toDomain)
                .collect(Collectors.toList());
    }
}
