package com.example.sns.core.chat.domain.service;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.domain.service.output.ChatMessageOutput;
import com.example.sns.core.chat.domain.service.port.ChatMessageReadRepository;
import com.example.sns.core.chat.domain.service.port.ChatMessageWriteRepository;
import com.example.sns.core.chat.exception.ChatRoomNotFoundException;
import com.example.sns.core.chat.domain.service.input.SendMessageInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageWriteService {
    private final ChatMessageReadRepository chatMessageReadRepository;
    private final ChatRoomWriteService chatRoomWriteService;
    private final ChatMessageWriteRepository chatMessageWriteRepository;

    public ChatMessageOutput sendMessage(SendMessageInput input) {
        var chatRoomOutput = chatRoomWriteService.getOrCreateChatRoom(input.getSenderId(), input.getReceiverId());

        var message = ChatMessage.create(
                chatRoomOutput.getId(),
                input.getSenderId(),
                input.getContent(),
                LocalDateTime.now()
        );

        var savedMessage = chatMessageWriteRepository.save(message);
        return ChatMessageOutput.from(savedMessage);
    }

    public ChatMessageOutput markMessageAsRead(Long messageId) {
        var message = chatMessageReadRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("ChatMessage not found"));

        if (!message.isRead()) {  // Only mark as read if it is not already read
            var updatedMessage = message.markAsRead();
            var savedMessage = chatMessageWriteRepository.save(updatedMessage);
            return ChatMessageOutput.from(savedMessage);
        }

        return ChatMessageOutput.from(message);  // Return the original message if already read
    }

    public void markMessagesAsReadInChatRoom(Long chatRoomId, Long userId) {
        List<ChatMessage> messages = chatMessageReadRepository.findUnreadMessagesInChatRoom(chatRoomId, userId)
                .orElseThrow(ChatRoomNotFoundException::new);
        messages.forEach(message -> {
            if (!message.isRead()) {  // Only save if the message was unread
                var updatedMessage = message.markAsRead();
                chatMessageWriteRepository.save(updatedMessage);
            }
        });
    }

    public void deleteMessage(Long messageId) {
        chatMessageWriteRepository.deleteById(messageId);
    }
}
