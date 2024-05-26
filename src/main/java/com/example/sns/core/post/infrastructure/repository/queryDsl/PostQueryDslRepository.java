package com.example.sns.core.post.infrastructure.repository.queryDsl;


import static com.example.sns.core.post.infrastructure.repository.entity.QPostEntity.postEntity;

import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<List<PostEntity>> findPostsByWriterAndStatusBeforeId(Long writerId, String status, Long lastId,
                                                                         int limit) {
        List<PostEntity> posts = queryFactory.selectFrom(postEntity)
                .where(
                        postEntity.writerId.eq(writerId)
                                .and(postEntity.status.eq(status))
                                .and(postEntity.id.lt(lastId))
                )
                .orderBy(postEntity.id.desc())
                .limit(limit)
                .fetch();

        return posts.isEmpty() ? Optional.empty() : Optional.of(posts);
    }

    public Optional<List<PostEntity>> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit) {
        List<PostEntity> posts = queryFactory.selectFrom(postEntity)
                .where(
                        postEntity.writerId.eq(writerId)
                                .and(postEntity.status.eq(status))
                )
                .orderBy(postEntity.id.desc())
                .limit(limit)
                .fetch();
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts);

    }

    public Optional<List<PostEntity>> findAllByInIdOrderByIdDesc(List<Long> postIds) {
        List<PostEntity> posts = queryFactory.selectFrom(postEntity)
                .where(postEntity.id.in(postIds))
                .orderBy(postEntity.id.desc())
                .fetch();
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts);
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
