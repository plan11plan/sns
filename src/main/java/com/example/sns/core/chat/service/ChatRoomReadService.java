package com.example.sns.core.chat.service;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.service.output.ChatRoomOutput;
import com.example.sns.core.chat.service.port.ChatRoomReadRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomReadService {
    private final ChatRoomReadRepository chatRoomReadRepository;

    public List<ChatRoomOutput> getChatRoomsByUserId(Long userId) {
        List<ChatRoom> chatRooms = chatRoomReadRepository.findByUserId(userId);
        return chatRooms.stream()
                .map(ChatRoomOutput::from)
                .collect(Collectors.toList());
    }

    public Optional<ChatRoomOutput> getChatRoomByUserIds(Long userId1, Long userId2) {
        return chatRoomReadRepository.findByUserIds(userId1, userId2)
                .map(ChatRoomOutput::from);
    }
}
