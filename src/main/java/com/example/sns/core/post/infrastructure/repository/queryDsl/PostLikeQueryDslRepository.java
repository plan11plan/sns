package com.example.sns.core.post.infrastructure.repository.queryDsl;

import static com.example.sns.core.post.infrastructure.repository.entity.QPostLikeEntity.postLikeEntity;

import com.example.sns.core.post.infrastructure.repository.entity.outputVo.PostLikeCountDaoResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    public PostLikeCountDaoResponse countByPostId(Long postId) {
        Long likeCount = queryFactory.select(postLikeEntity.count())
                .from(postLikeEntity)
                .where(postLikeEntity.postId.eq(postId))
                .fetchOne();
        return new PostLikeCountDaoResponse(postId, likeCount);
    }

    public List<PostLikeCountDaoResponse> findLikesByPostIds(List<Long> postIds) {
        return queryFactory
                .select(Projections.constructor(
                        PostLikeCountDaoResponse.class,
                        postLikeEntity.postId,
                        postLikeEntity.count().as("likeCount")))
                .from(postLikeEntity)
                .where(postLikeEntity.postId.in(postIds))
                .groupBy(postLikeEntity.postId)
                .fetch();
    }


}
