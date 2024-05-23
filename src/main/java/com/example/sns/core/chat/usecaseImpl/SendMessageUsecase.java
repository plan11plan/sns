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
        var input = toSendMessageInput(command);
        var chatMessageOutput = chatMessageWriteService.sendMessage(input);

        return MessageResponse.from(chatMessageOutput);
    }

    private SendMessageInput toSendMessageInput(SendMessageCommand command) {
        return SendMessageInput.builder()
                .chatRoomId(command.getChatRoomId())
                .senderId(command.getSenderId())
                .content(command.getContent())
                .build();
    }
}
