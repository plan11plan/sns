package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.DeleteMessageCommand;
import com.example.sns.core.chat.service.ChatMessageWriteService;
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
