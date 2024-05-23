package com.example.sns.core.chat.service;

import com.example.sns.core.chat.domain.Message;
import com.example.sns.core.chat.repository.ChatMessageRepository;
import com.example.sns.core.chat.service.input.SendMessageInput;
import com.example.sns.core.chat.service.output.ChatMessageOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMessageWriteService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomWriteService chatRoomWriteService;

    public ChatMessageOutput sendMessage(SendMessageInput input) {
        // Check if chat room exists or create a new one
        var chatRoomOutput = chatRoomWriteService.getOrCreateChatRoom(input.getChatRoomId(), input.getSenderId());

        // Create a new message
        var message = Message.create(
                chatRoomOutput.getId(),
                input.getSenderId(),
                input.getContent(),
                LocalDateTime.now()
        );

        var savedMessage = chatMessageRepository.save(message);
        return new ChatMessageOutput(
                savedMessage.getMessageIdValue(),
                savedMessage.getChatRoomId().getValue(),
                savedMessage.getSenderId().getValue(),
                savedMessage.getContent(),
                savedMessage.getSentAt(),
                savedMessage.isRead(),
                savedMessage.getStatus()
        );
    }

    public ChatMessageOutput markMessageAsRead(Long messageId) {
        var message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        var updatedMessage = message.markAsRead();
        var savedMessage = chatMessageRepository.save(updatedMessage);

        return new ChatMessageOutput(
                savedMessage.getMessageIdValue(),
                savedMessage.getChatRoomId().getValue(),
                savedMessage.getSenderId().getValue(),
                savedMessage.getContent(),
                savedMessage.getSentAt(),
                savedMessage.isRead(),
                savedMessage.getStatus()
        );
    }

    public void deleteMessage(Long messageId) {
        chatMessageRepository.deleteById(messageId);
    }
}
