package com.example.sns.core.chat.repository.implement;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.repository.entity.ChatRoomEntity;
import com.example.sns.core.chat.repository.jpa.ChatRoomJpaRepository;
import com.example.sns.core.chat.service.port.ChatRoomWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRoomWriteRepositoryImpl implements ChatRoomWriteRepository {
    private final ChatRoomJpaRepository chatRoomJpaRepository;


    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        return  chatRoomJpaRepository.save(ChatRoomEntity.from(chatRoom)).toModel();
    }

    @Override
    public void deleteById(Long chatRoomId) {
        chatRoomJpaRepository.deleteById(chatRoomId);

    }
}
