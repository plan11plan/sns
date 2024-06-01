package com.example.sns.core.chat.domain.service;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.domain.service.output.ChatRoomOutput;
import com.example.sns.core.chat.domain.service.port.ChatRoomReadRepository;
import com.example.sns.core.chat.exception.ChatRoomNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomReadService {
    private final ChatRoomReadRepository chatRoomReadRepository;

    public List<ChatRoomOutput> getChatRoomsByUserId(Long userId) {
        List<ChatRoom> chatRooms = chatRoomReadRepository.findByUserId(userId)
                .orElseThrow(ChatRoomNotFoundException::new);
        return chatRooms.stream()
                .map(ChatRoomOutput::from)
                .collect(Collectors.toList());
    }

    public ChatRoomOutput getChatRoomByUserIds(Long userId1, Long userId2) {
        ChatRoom chatRoom = chatRoomReadRepository.findByUserIds(userId1, userId2)
                .orElseThrow(ChatRoomNotFoundException::new);
        return ChatRoomOutput.from(chatRoom);
    }
}
