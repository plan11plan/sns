package com.example.sns.core.post.infrastructure.repository.queryDsl;

import static com.example.sns.core.post.infrastructure.repository.entity.QCommentEntity.commentEntity;

import com.example.sns.core.post.infrastructure.repository.entity.CommentEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public List<CommentEntity> findByPostId(Long postId) {
        return queryFactory.selectFrom(commentEntity)
                .where(commentEntity.postId.eq(postId))
                .fetch();
    }

    public List<CommentEntity> findByParentId(Long parentId) {
        return queryFactory.selectFrom(commentEntity)
                .where(commentEntity.parentId.eq(parentId))
                .fetch();
    }
}