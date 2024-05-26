package com.example.sns.core.chat.infrastructure.queryDsl;

import static com.example.sns.core.chat.infrastructure.entity.QChatRoomEntity.chatRoomEntity;

import com.example.sns.core.chat.infrastructure.entity.ChatRoomEntity;
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
                .fetchFirst();  // 중복 결과가 있을 경우 첫 번째 결과만 반환
        return Optional.ofNullable(entity);
    }
    public Optional<ChatRoomEntity> findById(Long chatRoomId) {
        ChatRoomEntity entity = queryFactory.selectFrom(chatRoomEntity)
                .where(chatRoomEntity.id.eq(chatRoomId))
                .fetchOne();
        return Optional.ofNullable(entity);
    }

    public Optional<List<ChatRoomEntity>> findByUserId(Long userId) {
        List<ChatRoomEntity> chatRoomEntities = queryFactory.selectFrom(chatRoomEntity)
                .where(chatRoomEntity.userId1.eq(userId).or(chatRoomEntity.userId2.eq(userId)))
                .fetch();
        return chatRoomEntities.isEmpty() ? Optional.empty() : Optional.of(chatRoomEntities);

    }
}
