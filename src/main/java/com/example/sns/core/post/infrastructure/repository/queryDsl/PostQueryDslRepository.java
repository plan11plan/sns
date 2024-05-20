package com.example.sns.core.post.infrastructure.repository.queryDsl;


import static com.example.sns.core.post.infrastructure.repository.entity.QPostEntity.postEntity;

import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public List<PostEntity> findPostsByWriterAndStatusBeforeId(Long writerId, String status, Long lastId, int limit) {
        return queryFactory.selectFrom(postEntity)
                .where(
                        postEntity.writerId.eq(writerId)
                                .and(postEntity.status.eq(status))
                                .and(postEntity.id.lt(lastId))
                )
                .orderBy(postEntity.id.desc())
                .limit(limit)
                .fetch();
    }

    public List<PostEntity> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit) {
        return queryFactory.selectFrom(postEntity)
                .where(
                        postEntity.writerId.eq(writerId)
                                .and(postEntity.status.eq(status))
                )
                .orderBy(postEntity.id.desc())
                .limit(limit)
                .fetch();
    }

    public List<PostEntity> findAllByInIdOrderByIdDesc(List<Long> postIds) {
        return queryFactory.selectFrom(postEntity)
                .where(postEntity.id.in(postIds))
                .orderBy(postEntity.id.desc())
                .fetch();
    }

    private BooleanExpression allEq(List<Long> writerIds, String statusCon) {
        return idsIn(writerIds).and(postStatusEq(statusCon));
    }

    private BooleanExpression allEq(Long writerId, String statusCon) {
        return writerIdEq(writerId).and(postStatusEq(statusCon));
    }

    private BooleanExpression idsIn(List<Long> ids) {
        return ids != null && !ids.isEmpty() ? postEntity.id.in(ids) : null;
    }

    private BooleanExpression postStatusEq(String statusCon) {
        return statusCon != null ? postEntity.status.eq(statusCon) : null;

    }

    private BooleanExpression writerIdEq(Long writerId) {
        return writerId != null ? postEntity.writerId.eq(writerId) : null;
    }

}
