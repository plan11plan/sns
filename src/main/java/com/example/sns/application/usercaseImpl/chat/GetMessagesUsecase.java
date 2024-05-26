package com.example.sns.application.usercaseImpl.chat;

import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.service.ChatMessageReadService;
import com.example.sns.core.chat.service.ChatMessageWriteService;
import com.example.sns.core.chat.service.output.ChatMessagesOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMessagesUsecase {
    private final ChatMessageReadService chatMessageReadService;
    private final ChatMessageWriteService chatMessageWriteService;

    public List<MessageResponse> execute(Long chatRoomId, Long userId) {
        // 채팅방에 들어올 때 메시지를 읽음으로 표시
        chatMessageWriteService.markMessagesAsReadInChatRoom(chatRoomId, userId);

        // 메시지 가져오기
        ChatMessagesOutput chatMessagesOutput = chatMessageReadService.getMessagesByChatRoomId(chatRoomId);
        return chatMessagesOutput.getChatMessages().stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }
}
