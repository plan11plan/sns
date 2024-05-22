package com.example.sns.core.chat.controller;

import com.example.sns.core.chat.dto.DeleteMessageCommand;
import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.dto.SendMessageCommand;
import com.example.sns.core.chat.usecaseImpl.DeleteMessageUsecase;
import com.example.sns.core.chat.usecaseImpl.GetMessagesUsecase;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatMessageController {
    private final SendMessageUsecase sendMessageUsecase;
    private final GetMessagesUsecase getMessagesUsecase;
    private final DeleteMessageUsecase deleteMessageUsecase;
    //
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public MessageResponse sendMessageV2(SendMessageCommand command) {
        return sendMessageUsecase.execute(command);
    }
    //
    @PostMapping("/message")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody SendMessageCommand command) {
        MessageResponse response = sendMessageUsecase.execute(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/messages/{chatRoomId}")
    public ResponseEntity<List<MessageResponse>> getMessages(@PathVariable Long chatRoomId) {
        List<MessageResponse> responses = getMessagesUsecase.execute(chatRoomId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


    @DeleteMapping("/message/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long  messageId) {
        deleteMessageUsecase.execute(new DeleteMessageCommand(messageId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
