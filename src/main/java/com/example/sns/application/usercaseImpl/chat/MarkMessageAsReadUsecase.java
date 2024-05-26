package com.example.sns.application.usercaseImpl.chat;

import com.example.sns.application.command.chat.MarkMessageAsReadCommand;
import com.example.sns.core.chat.service.ChatMessageWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkMessageAsReadUsecase {
    private final ChatMessageWriteService chatMessageWriteService;

    public void execute(MarkMessageAsReadCommand command) {
        chatMessageWriteService.markMessageAsRead(command.getMessageId());
    }
}