package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.service.ChatMessageReadService;
import com.example.sns.core.chat.service.output.ChatMessagesOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMessagesUsecase {
    private final ChatMessageReadService chatMessageReadService;

    public List<MessageResponse> execute(Long chatRoomId) {
        ChatMessagesOutput chatMessagesOutput = chatMessageReadService.getMessagesByChatRoomId(chatRoomId);
        return chatMessagesOutput.getChatMessages().stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }
}
