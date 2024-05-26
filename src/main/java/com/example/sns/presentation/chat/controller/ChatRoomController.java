package com.example.sns.presentation.chat.controller;

import com.example.sns.core.chat.dto.ChatRoomResponse;
import com.example.sns.application.command.chat.CreateChatRoomCommand;
import com.example.sns.application.command.chat.DeleteChatRoomCommand;
import com.example.sns.application.usercaseImpl.chat.CreateChatRoomUsecase;
import com.example.sns.application.usercaseImpl.chat.DeleteChatRoomUsecase;
import com.example.sns.application.usercaseImpl.chat.GetChatRoomsUsecase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ChatRoomController {
    private final CreateChatRoomUsecase createChatRoomUsecase;
    private final DeleteChatRoomUsecase deleteChatRoomUsecase;
    private final GetChatRoomsUsecase getChatRoomsUsecase;

    @PostMapping("/room")
    public ResponseEntity<ChatRoomResponse> createChatRoom(@RequestBody CreateChatRoomCommand command) {
        ChatRoomResponse response = createChatRoomUsecase.execute(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/rooms/{userId}")
    public ResponseEntity<List<ChatRoomResponse>> getChatRooms(@PathVariable Long userId) {
        List<ChatRoomResponse> responses = getChatRoomsUsecase.execute(userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/room/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long chatRoomId) {
        deleteChatRoomUsecase.execute(new DeleteChatRoomCommand(chatRoomId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
