package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.DeleteMessageCommand;
import com.example.sns.core.chat.dto.DeleteMessageInput;
import com.example.sns.core.chat.service.MessageWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMessageUsecase {
    private final MessageWriteService messageWriteService;

    public void execute(DeleteMessageCommand command) {
        messageWriteService.deleteMessage(new DeleteMessageInput(command.getMessageId()));
    }
}