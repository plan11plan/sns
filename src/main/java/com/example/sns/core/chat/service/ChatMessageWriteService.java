package com.example.sns.core.chat.service;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.service.input.SendMessageInput;
import com.example.sns.core.chat.service.output.ChatMessageOutput;
import com.example.sns.core.chat.service.port.ChatMessageReadRepository;
import com.example.sns.core.chat.service.port.ChatMessageWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMessageWriteService {
    private final ChatMessageReadRepository chatMessageReadRepository;
    private final ChatRoomWriteService chatRoomWriteService;
    private final ChatMessageWriteRepository chatMessageWriteRepository;

    public ChatMessageOutput sendMessage(SendMessageInput input) {
        var chatRoomOutput = chatRoomWriteService.getOrCreateChatRoom(input.getChatRoomId(), input.getSenderId());

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

        var updatedMessage = message.markAsRead();
        var savedMessage = chatMessageWriteRepository.save(updatedMessage);

        return ChatMessageOutput.from(savedMessage);
    }

    public void deleteMessage(Long messageId) {
        chatMessageWriteRepository.deleteById(messageId);
    }
}
