package com.example.sns.core.chat.repository.queryDsl;

import static com.example.sns.core.chat.repository.entity.QChatMessageEntity.chatMessageEntity;

import com.example.sns.core.chat.repository.entity.ChatMessageEntity;
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

    public List<ChatMessageEntity> findByChatRoomId(Long chatRoomId) {
        return queryFactory.selectFrom(chatMessageEntity)
                .where(chatMessageEntity.chatRoomId.eq(chatRoomId))
                .fetch();
    }

    public List<ChatMessageEntity> findUnreadMessagesInChatRoom(Long chatRoomId, Long userId) {
        return queryFactory.selectFrom(chatMessageEntity)
                .where(chatMessageEntity.chatRoomId.eq(chatRoomId)
                        .and(chatMessageEntity.isRead.eq(false))
                        .and(chatMessageEntity.senderId.ne(userId)))
                .fetch();
    }
}
