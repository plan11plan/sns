package com.example.sns.core.chat.domain.service;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.domain.service.output.ChatMessagesOutput;
import com.example.sns.core.chat.domain.service.port.ChatMessageReadRepository;
import com.example.sns.core.chat.exception.MessageNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageReadService {
    private final ChatMessageReadRepository chatMessageReadRepository;

    public ChatMessagesOutput getMessagesByChatRoomId(Long chatRoomId) {
        List<ChatMessage> chatMessages = chatMessageReadRepository.findByChatRoomId(chatRoomId)
                .orElseThrow(MessageNotFoundException::new);
        return ChatMessagesOutput.from(chatMessages);
    }
}

