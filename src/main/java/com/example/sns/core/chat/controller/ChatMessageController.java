package com.example.sns.core.chat.controller;

import com.example.sns.core.chat.dto.DeleteMessageCommand;
import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.dto.SendMessageCommand;
import com.example.sns.core.chat.usecaseImpl.DeleteMessageUsecase;
import com.example.sns.core.chat.usecaseImpl.GetMessagesUsecase;
import com.example.sns.core.chat.usecaseImpl.MarkMessageAsReadCommand;
import com.example.sns.core.chat.usecaseImpl.MarkMessageAsReadUsecase;
import com.example.sns.core.chat.usecaseImpl.SendMessageUsecase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatMessageController {
    private final SendMessageUsecase sendMessageUsecase;
    private final GetMessagesUsecase getMessagesUsecase;
    private final DeleteMessageUsecase deleteMessageUsecase;
    private final MarkMessageAsReadUsecase markMessageAsReadUsecase;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public MessageResponse sendMessageV2(SendMessageCommand command) {
        return sendMessageUsecase.execute(command);
    }
    @MessageMapping("/markAsRead")
    public void markMessageAsRead(MarkMessageAsReadCommand command) {
        markMessageAsReadUsecase.execute(command);
    }
    @GetMapping("/messages/{chatRoomId}/{userId}")
    public ResponseEntity<List<MessageResponse>> getMessages(@PathVariable Long chatRoomId, @PathVariable Long userId) {
        List<MessageResponse> responses = getMessagesUsecase.execute(chatRoomId, userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/message/{messageId}/{userId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId, @PathVariable Long userId) {
        deleteMessageUsecase.execute(new DeleteMessageCommand(messageId, userId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
