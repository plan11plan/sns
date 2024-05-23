package com.example.sns.core.chat.service;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.repository.ChatRoomRepository;
import com.example.sns.core.chat.service.output.ChatRoomOutput;
import com.example.sns.core.common.service.port.TimeHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomWriteService {
    private final ChatRoomRepository chatRoomRepository;
    private final TimeHolder timeHolder;

    public ChatRoomOutput createChatRoom(Long userId1, Long userId2) {
        var chatRoom = ChatRoom.create(userId1, userId2, timeHolder.nowDateTime());
        var savedChatRoom = chatRoomRepository.save(chatRoom);
        return new ChatRoomOutput(
                savedChatRoom.getId().getValue(),
                savedChatRoom.getUserId1().getValue(),
                savedChatRoom.getUserId2().getValue(),
                timeHolder.nowDateTime()
        );
    }

    public Optional<ChatRoom> findExistingChatRoom(Long userId1, Long userId2) {
        return chatRoomRepository.findByUserIds(userId1, userId2);
    }

    public ChatRoomOutput getOrCreateChatRoom(Long userId1, Long userId2) {
        return findExistingChatRoom(userId1, userId2)
                .map(chatRoom -> new ChatRoomOutput(
                        chatRoom.getId().getValue(),
                        chatRoom.getUserId1().getValue(),
                        chatRoom.getUserId2().getValue(),
                        timeHolder.nowDateTime()
                ))
                .orElseGet(() -> createChatRoom(userId1, userId2));
    }

    public void deleteChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();
        chatRoomRepository.deleteById(chatRoom.getChatRoomIdValue());
    }
}