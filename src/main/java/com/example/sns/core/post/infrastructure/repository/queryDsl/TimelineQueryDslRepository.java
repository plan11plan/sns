package com.example.sns.core.post.infrastructure.repository.queryDsl;

import static com.example.sns.core.post.infrastructure.repository.entity.QPostEntity.postEntity;
import static com.example.sns.core.post.infrastructure.repository.entity.QTimelineEntity.timelineEntity;

import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TimelineQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<List<TimelineEntity>> findLatestTimelinesByWriter(Long userId, int limit) {
        List<TimelineEntity> timelineEntities = queryFactory.selectFrom(timelineEntity)
                .where(writerIdEq(userId))
                .orderBy(timelineEntity.id.desc())
                .limit(limit)
                .fetch();
        return timelineEntities.isEmpty() ? Optional.empty() : Optional.of(timelineEntities);
    }

    public Optional<List<TimelineEntity>> findTimelinesByWriterBeforeId(Long userId, Long lastId, int limit) {
        List<TimelineEntity> timelineEntities = queryFactory.selectFrom(timelineEntity)
                .where(writerIdEq(userId).and(timelineEntity.id.lt(lastId)))
                .orderBy(timelineEntity.id.desc())
                .limit(limit)
                .fetch();
        return timelineEntities.isEmpty() ? Optional.empty() : Optional.of(timelineEntities);

    }
    private BooleanExpression allEq(List<Long> userIds, String statusCon) {
        return idsIn(userIds).and(postStatusEq(statusCon));
    }


    private BooleanExpression idsIn(List<Long> ids) {
        return ids != null && !ids.isEmpty() ? timelineEntity.id.in(ids) : null;
    }

    private BooleanExpression postStatusEq(String statusCon) {
        return statusCon != null ? postEntity.status.eq(statusCon) : null;

    }

    private BooleanExpression writerIdEq(Long userId) {
        return userId != null ? timelineEntity.userId.eq(userId) : null;
    }


}
