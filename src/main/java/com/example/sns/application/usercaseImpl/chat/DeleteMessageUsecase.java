package com.example.sns.application.usercaseImpl.chat;

import com.example.sns.application.command.chat.DeleteMessageCommand;
import com.example.sns.core.chat.domain.service.ChatMessageWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMessageUsecase {
    private final ChatMessageWriteService chatMessageWriteService;

    public void execute(DeleteMessageCommand command) {
        chatMessageWriteService.deleteMessage(command.getMessageId());
    }
}
