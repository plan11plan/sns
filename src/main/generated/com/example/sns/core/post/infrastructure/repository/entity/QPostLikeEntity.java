package com.example.sns.core.post.infrastructure.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostLikeEntity is a Querydsl query type for PostLikeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostLikeEntity extends EntityPathBase<PostLikeEntity> {

    private static final long serialVersionUID = 21744742L;

    public static final QPostLikeEntity postLikeEntity = new QPostLikeEntity("postLikeEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> writerId = createNumber("writerId", Long.class);

    public QPostLikeEntity(String variable) {
        super(PostLikeEntity.class, forVariable(variable));
    }

    public QPostLikeEntity(Path<? extends PostLikeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostLikeEntity(PathMetadata metadata) {
        super(PostLikeEntity.class, metadata);
    }

}

