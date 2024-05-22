package com.example.sns.core.chat.service;

import com.example.sns.core.chat.Message;
import com.example.sns.core.chat.MessageEntity;
import com.example.sns.core.chat.dto.DeleteMessageInput;
import com.example.sns.core.chat.dto.MarkMessageAsReadInput;
import com.example.sns.core.chat.dto.MessageOutput;
import com.example.sns.core.chat.dto.SendMessageInput;
import com.example.sns.core.chat.repository.MessageJpaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageWriteService {
    private final MessageJpaRepository messageRepository;

    public MessageOutput sendMessage(SendMessageInput input) {
        Message message = Message.create(input.getChatRoomId(), input.getSenderId(), input.getContent(), LocalDateTime.now());
        MessageEntity savedEntity = messageRepository.save(MessageEntity.from(message));
        return MessageOutput.from(savedEntity.toModel());
    }

    public void markMessageAsRead(MarkMessageAsReadInput input) {
        MessageEntity messageEntity = messageRepository.findById(input.getMessageId())
                .orElseThrow(() -> new RuntimeException("Message not found"));
        Message updatedMessage = messageEntity.toModel().markAsRead();
        messageRepository.save(MessageEntity.from(updatedMessage));
    }


    public void deleteMessage(DeleteMessageInput input) {
        messageRepository.deleteById(input.getMessageId());
    }
}