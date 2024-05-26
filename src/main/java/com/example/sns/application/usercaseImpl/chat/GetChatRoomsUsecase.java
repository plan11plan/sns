package com.example.sns.application.usercaseImpl.chat;

import com.example.sns.core.chat.dto.ChatRoomResponse;
import com.example.sns.core.chat.service.ChatRoomReadService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetChatRoomsUsecase {
    private final ChatRoomReadService chatRoomReadService;

    public List<ChatRoomResponse> execute(Long userId) {
        return chatRoomReadService.getChatRoomsByUserId(userId).stream()
                .map(chatRoom -> ChatRoomResponse.from(chatRoom))
                .collect(Collectors.toList());
    }
}
