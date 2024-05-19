package com.example.sns.core.post.infrastructure.repository.queryDsl;

import static com.example.sns.core.post.infrastructure.repository.entity.QPostLikeEntity.postLikeEntity;

import com.example.sns.core.post.domain.entity.PostLikeCount;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    public Long countByPostId(Long postId) {
        return queryFactory.select(postLikeEntity.count())
                .from(postLikeEntity)
                .where(postLikeEntity.postId.eq(postId))
                .fetchOne();
    }

    public Map<Long, Long> findLikesByPostIds(List<Long> postIds) {
        return queryFactory
                .select(Projections.fields(PostLikeCount.class, postLikeEntity.postId, postLikeEntity.count().as("likeCount")))
                .from(postLikeEntity)
                .where(postLikeEntity.postId.in(postIds))
                .groupBy(postLikeEntity.postId)
                .fetch()
                .stream()
                .collect(Collectors.toMap(PostLikeCount::getPostIdValue, PostLikeCount::getLikeCountValue));
    }


}
