package com.example.sns.core.post.infrastructure.repository.queryDsl;



import static com.example.sns.core.post.infrastructure.repository.entity.QPostEntity.postEntity;

import com.example.sns.core.post.domain.entity.PostStatus;
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

    public List<PostEntity> findLatestPostsByWriterAndStatus(Long writerId, PostStatus statusCon, int limit) {
        return queryFactory.selectFrom(postEntity)
                .where(allEq(writerId,statusCon))
                .orderBy(postEntity.id.desc())
                .limit(limit)
                .fetch();
    }

    public List<PostEntity> findPostsByWriterAndStatusBeforeId(Long writerId, PostStatus statusCon, Long lastId, int limit) {
        return queryFactory.selectFrom(postEntity)
                .where(allEq(writerId,statusCon).and(postEntity.id.lt(lastId)))
                .orderBy(postEntity.id.desc())
                .limit(limit)
                .fetch();
    }

    private BooleanExpression allEq(List<Long> writerIds, PostStatus statusCon) {
        return idsIn(writerIds).and(postStatusEq(statusCon));
    }

    private BooleanExpression allEq(Long writerId, PostStatus statusCon) {
        return writerIdEq(writerId).and(postStatusEq(statusCon));
    }

    private BooleanExpression idsIn(List<Long> ids) {
        return ids != null && !ids.isEmpty() ? postEntity.id.in(ids) : null;
    }

    private BooleanExpression postStatusEq(PostStatus statusCon) {
        return statusCon != null ? postEntity.status.eq(statusCon) : null;

    }

    private BooleanExpression writerIdEq(Long writerId) {
        return writerId != null ? postEntity.writerId.eq(writerId) : null;
    }

}
