package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.service.ChatMessageReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMessagesUsecase {
    private final ChatMessageReadService chatMessageReadService;

    public List<MessageResponse> execute(Long chatRoomId) {
        return chatMessageReadService.getMessagesByChatRoomId(chatRoomId).stream()
                .map(message -> new MessageResponse(
                        message.getId(),
                        message.getChatRoomId(),
                        message.getSenderId(),
                        message.getContent(),
                        message.getSentAt(),
                        message.isRead(),
                        message.getStatus().name()
                ))
                .collect(Collectors.toList());
    }
}
