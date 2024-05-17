package com.example.sns.core.post.infrastructure.repository.queryDsl;

import static com.example.sns.core.post.infrastructure.repository.entity.QPostEntity.postEntity;
import static com.example.sns.core.post.infrastructure.repository.entity.QTimelineEntity.timelineEntity;

import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TimelineQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    public List<TimelineEntity> findByUserId(Long userId) {
        return queryFactory.selectFrom(timelineEntity)
                .where(writerIdEq(userId))
                .fetch();
    }
    public List<TimelineEntity> findLatestTimelinesByWriter(Long userId, int limit) {
        return queryFactory.selectFrom(timelineEntity)
                .where(writerIdEq(userId))
                .orderBy(timelineEntity.id.desc())
                .limit(limit)
                .fetch();
    }

    public List<TimelineEntity> findTimelinesByWriterBeforeId(Long userId, Long lastId, int limit) {
        return queryFactory.selectFrom(timelineEntity)
                .where(writerIdEq(userId).and(timelineEntity.id.lt(lastId)))
                .orderBy(timelineEntity.id.desc())
                .limit(limit)
                .fetch();
    }
    private BooleanExpression allEq(List<Long> userIds, PostStatus statusCon) {
        return idsIn(userIds).and(postStatusEq(statusCon));
    }


    private BooleanExpression idsIn(List<Long> ids) {
        return ids != null && !ids.isEmpty() ? timelineEntity.id.in(ids) : null;
    }

    private BooleanExpression postStatusEq(PostStatus statusCon) {
        return statusCon != null ? postEntity.status.eq(statusCon) : null;

    }

    private BooleanExpression writerIdEq(Long userId) {
        return userId != null ? timelineEntity.userId.eq(userId) : null;
    }


}
