package com.example.sns.core.chat.infrastructure.queryDsl;

import static com.example.sns.core.chat.infrastructure.entity.QChatMessageEntity.chatMessageEntity;

import com.example.sns.core.chat.infrastructure.entity.ChatMessageEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatMessageQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<ChatMessageEntity> findById(Long messageId) {
        ChatMessageEntity entity = queryFactory.selectFrom(chatMessageEntity)
                .where(chatMessageEntity.id.eq(messageId))
                .fetchOne();
        return Optional.ofNullable(entity);
    }

    public Optional<List<ChatMessageEntity>> findByChatRoomId(Long chatRoomId) {
        List<ChatMessageEntity> chatMessageEntities = queryFactory.selectFrom(chatMessageEntity)
                .where(chatMessageEntity.chatRoomId.eq(chatRoomId))
                .fetch();
        return chatMessageEntities.isEmpty() ? Optional.empty() : Optional.of(chatMessageEntities);

    }

    public Optional<List<ChatMessageEntity>> findUnreadMessagesInChatRoom(Long chatRoomId, Long userId) {
        List<ChatMessageEntity> chatMessageEntities = queryFactory.selectFrom(chatMessageEntity)
                .where(chatMessageEntity.chatRoomId.eq(chatRoomId)
                        .and(chatMessageEntity.isRead.eq(false))
                        .and(chatMessageEntity.senderId.ne(userId)))
                .fetch();
        return chatMessageEntities.isEmpty() ? Optional.empty() : Optional.of(chatMessageEntities);
    }
}
