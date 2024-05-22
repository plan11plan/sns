package com.example.sns.core.chat.service;

import com.example.sns.core.chat.ChatRoom;
import com.example.sns.core.chat.ChatRoomEntity;
import com.example.sns.core.chat.dto.ChatRoomOutput;
import com.example.sns.core.chat.dto.CreateChatRoomInput;
import com.example.sns.core.chat.repository.ChatRoomJpaRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomWriteService {
    private final ChatRoomJpaRepository chatRoomJpaRepository;

    public ChatRoomOutput createChatRoom(CreateChatRoomInput input) {
        ChatRoom chatRoom = ChatRoom.create(input.getUserId1(), input.getUserId2(), LocalDateTime.now());
        ChatRoomEntity savedEntity = chatRoomJpaRepository.save(ChatRoomEntity.from(chatRoom));
        return ChatRoomOutput.from(savedEntity.toModel());
    }
    public void deleteChatRoom(UUID chatRoomId) {
        chatRoomJpaRepository.deleteById(chatRoomId);
    }
}
