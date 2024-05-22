package com.example.sns.core.chat.service;

import com.example.sns.core.chat.MessageEntity;
import com.example.sns.core.chat.dto.MessageOutput;
import com.example.sns.core.chat.repository.MessageJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageReadService {
    private final MessageJpaRepository messageRepository;

    public List<MessageOutput> getMessages(Long chatRoomId) {
        List<MessageEntity> messages = messageRepository.findByChatRoomId(chatRoomId);
        return messages.stream().map(entity -> MessageOutput.from(entity.toModel())).collect(Collectors.toList());
    }
}