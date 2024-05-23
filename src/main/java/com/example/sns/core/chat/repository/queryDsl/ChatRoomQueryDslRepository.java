package com.example.sns.core.chat.repository.queryDsl;

import static com.example.sns.core.chat.repository.entity.QChatRoomEntity.chatRoomEntity;

import com.example.sns.core.chat.repository.entity.ChatRoomEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRoomQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<ChatRoomEntity> findByUserIds(Long userId1, Long userId2) {
        ChatRoomEntity entity = queryFactory.selectFrom(chatRoomEntity)
                .where((chatRoomEntity.userId1.eq(userId1).and(chatRoomEntity.userId2.eq(userId2)))
                        .or(chatRoomEntity.userId1.eq(userId2).and(chatRoomEntity.userId2.eq(userId1))))
                .fetchOne();
        return Optional.ofNullable(entity);
    }

    public Optional<ChatRoomEntity> findById(Long chatRoomId) {
        ChatRoomEntity entity = queryFactory.selectFrom(chatRoomEntity)
                .where(chatRoomEntity.id.eq(chatRoomId))
                .fetchOne();
        return Optional.ofNullable(entity);
    }

    public List<ChatRoomEntity> findByUserId(Long userId) {
        return queryFactory.selectFrom(chatRoomEntity)
                .where(chatRoomEntity.userId1.eq(userId).or(chatRoomEntity.userId2.eq(userId)))
                .fetch();
    }
}
