package com.example.sns.core.chat.service;

import com.example.sns.core.chat.ChatRoomEntity;
import com.example.sns.core.chat.dto.ChatRoomOutput;
import com.example.sns.core.chat.repository.ChatRoomJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomReadService {
    private final ChatRoomJpaRepository chatRoomRepository;

    public List<ChatRoomOutput> getChatRooms(Long userId) {
        List<ChatRoomEntity> chatRooms = chatRoomRepository.findByUserId1OrUserId2(userId, userId);
        return chatRooms.stream().map(entity -> ChatRoomOutput.from(entity.toModel())).collect(Collectors.toList());
    }
}
