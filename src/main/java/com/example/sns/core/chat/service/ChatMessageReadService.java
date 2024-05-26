package com.example.sns.core.chat.service;

import com.example.sns.core.chat.domain.ChatMessage;
import com.example.sns.core.chat.exception.MessageNotFoundException;
import com.example.sns.core.chat.service.output.ChatMessagesOutput;
import com.example.sns.core.chat.service.port.ChatMessageReadRepository;
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

