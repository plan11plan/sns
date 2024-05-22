package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.dto.SendMessageCommand;
import com.example.sns.core.chat.dto.SendMessageInput;
import com.example.sns.core.chat.service.MessageWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMessageUsecase {
    private final MessageWriteService messageWriteService;

    public MessageResponse execute(SendMessageCommand command) {
        var output = messageWriteService.sendMessage(new SendMessageInput(command.getChatRoomId(), command.getSenderId(), command.getContent()));
        return new MessageResponse(output.getId(), output.getChatRoomId(), output.getSenderId(), output.getContent(), output.getSentAt(), output.isRead());
    }
}