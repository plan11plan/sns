package com.example.sns.core.chat.usecaseImpl;

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
        var outputs = chatRoomReadService.getChatRooms(userId);
        return outputs.stream().map(output -> new ChatRoomResponse(output.getId(), output.getUserId1(), output.getUserId2(), output.getCreatedAt())).collect(
                Collectors.toList());
    }
}