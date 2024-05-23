package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.ChatRoomResponse;
import com.example.sns.core.chat.service.ChatRoomReadService;
import com.example.sns.core.common.service.port.TimeHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetChatRoomsUsecase {
    private final ChatRoomReadService chatRoomReadService;
    private final TimeHolder timeHolder;

    public List<ChatRoomResponse> execute(Long userId) {
        return chatRoomReadService.getChatRoomsByUserId(userId).stream()
                .map(chatRoom -> new ChatRoomResponse(
                        chatRoom.getId(),
                        chatRoom.getUserId1(),
                        chatRoom.getUserId2(),
                        timeHolder.nowDateTime()))
                .collect(Collectors.toList());
    }
}
