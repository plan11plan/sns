package com.example.sns.core.chat.service;

import com.example.sns.core.chat.domain.Message;
import com.example.sns.core.chat.repository.ChatMessageRepository;
import com.example.sns.core.chat.service.output.ChatMessageOutput;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageReadService {
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatMessageOutput> getMessagesByChatRoomId(Long chatRoomId) {
        return chatMessageRepository.findByChatRoomId(chatRoomId).stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private ChatMessageOutput toOutput(Message message) {
        return new ChatMessageOutput(
                message.getId().getValue(),
                message.getChatRoomId().getValue(),
                message.getSenderId().getValue(),
                message.getContent(),
                message.getSentAt(),
                message.isRead(),
                message.getStatus()
        );
    }
}
