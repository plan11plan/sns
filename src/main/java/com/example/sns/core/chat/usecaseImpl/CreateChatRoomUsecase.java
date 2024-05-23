package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.ChatRoomResponse;
import com.example.sns.core.chat.dto.CreateChatRoomCommand;
import com.example.sns.core.chat.service.ChatRoomWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateChatRoomUsecase {
    private final ChatRoomWriteService chatRoomWriteService;


    public ChatRoomResponse execute(CreateChatRoomCommand command) {
        var output = chatRoomWriteService.createChatRoom(command.getUserId1(), command.getUserId2());
        return ChatRoomResponse.from(output);
    }
}
