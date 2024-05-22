package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.DeleteChatRoomCommand;
import com.example.sns.core.chat.service.ChatRoomWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteChatRoomUsecase {
    private final ChatRoomWriteService chatRoomWriteService;

    public void execute(DeleteChatRoomCommand command) {
        chatRoomWriteService.deleteChatRoom(command.getChatRoomId());
    }
}

