package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.dto.SendMessageCommand;
import com.example.sns.core.chat.service.ChatMessageWriteService;
import com.example.sns.core.chat.service.input.SendMessageInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMessageUsecase {
    private final ChatMessageWriteService chatMessageWriteService;

    public MessageResponse execute(SendMessageCommand command) {
        var input = new SendMessageInput(
                command.getChatRoomId(),
                command.getSenderId(),
                command.getContent()
        );

        var output = chatMessageWriteService.sendMessage(input);
        return new MessageResponse(
                output.getId(),
                output.getChatRoomId(),
                output.getSenderId(),
                output.getContent(),
                output.getSentAt(),
                output.isRead(),
                output.getStatus().name()
        );
    }
}
