package com.example.sns.core.chat.service;

import com.example.sns.core.chat.domain.ChatRoom;
import com.example.sns.core.chat.dto.ChatRoomResponse;
import com.example.sns.core.chat.repository.ChatRoomRepository;
import com.example.sns.core.chat.repository.entity.ChatRoomEntity;
import com.example.sns.core.chat.repository.entity.QChatRoomEntity;
import com.example.sns.core.chat.repository.mapper.ChatRoomMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomReadService {
    private final ChatRoomRepository chatRoomRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<ChatRoomResponse> getChatRoomsByUserId(Long userId) {
        QChatRoomEntity chatRoomEntity = QChatRoomEntity.chatRoomEntity;

        List<ChatRoomEntity> chatRoomEntities = jpaQueryFactory.selectFrom(chatRoomEntity)
                .where(chatRoomEntity.userId1.eq(userId).or(chatRoomEntity.userId2.eq(userId)))
                .fetch();

        List<ChatRoom> chatRooms = chatRoomEntities.stream()
                .map(i->ChatRoomMapper.toDomain(i))
                .collect(Collectors.toList());

        return chatRooms.stream()
                .map(chatRoom -> new ChatRoomResponse(
                        chatRoom.getId().getValue(),
                        chatRoom.getUserId1().getValue(),
                        chatRoom.getUserId2().getValue(),
                        chatRoom.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
