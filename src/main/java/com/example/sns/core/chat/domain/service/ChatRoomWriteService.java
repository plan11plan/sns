package com.example.sns.core.chat.domain.service;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.domain.request.ChatRoomCreate;
import com.example.sns.core.chat.domain.ChatUserId;
import com.example.sns.core.chat.domain.service.port.ChatRoomReadRepository;
import com.example.sns.core.chat.domain.service.output.ChatRoomOutput;
import com.example.sns.core.chat.domain.service.port.ChatRoomWriteRepository;
import com.example.sns.common.service.port.TimeHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomWriteService {
    private final ChatRoomReadRepository chatRoomReadRepository;
    private final ChatRoomWriteRepository chatRoomWriteRepository;
    private final TimeHolder timeHolder;

    public ChatRoomOutput createChatRoom(Long userId1, Long userId2) {
        ChatRoom chatRoom = ChatRoom.create(
                ChatRoomCreate.builder()
                        .userId1(new ChatUserId(userId1))
                        .userId2(new ChatUserId(userId2))
                        .build(),
                timeHolder.nowDateTime()
        );
        chatRoom = chatRoomWriteRepository.save(chatRoom);
        return ChatRoomOutput.from(chatRoom);
    }

    public Optional<ChatRoom> findExistingChatRoom(Long userId1, Long userId2) {
        return chatRoomReadRepository.findByUserIds(userId1, userId2);
    }

    public ChatRoomOutput getOrCreateChatRoom(Long userId1, Long userId2) {
        return findExistingChatRoom(userId1, userId2)
                .map(chatRoom -> ChatRoomOutput.from(chatRoom))
                .orElseGet(() -> createChatRoom(userId1, userId2));
    }

    public void deleteChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomReadRepository.findById(chatRoomId).orElseThrow();
        chatRoomWriteRepository.deleteById(chatRoom.getChatRoomIdValue());
    }
}