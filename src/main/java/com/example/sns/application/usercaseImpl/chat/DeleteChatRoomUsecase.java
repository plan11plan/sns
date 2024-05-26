package com.example.sns.application.usercaseImpl.chat;

import com.example.sns.application.command.chat.DeleteChatRoomCommand;
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
