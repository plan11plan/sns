package com.example.sns.core.follow.infrastructure.persistence;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFollowEntity is a Querydsl query type for FollowEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollowEntity extends EntityPathBase<FollowEntity> {

    private static final long serialVersionUID = -1720485255L;

    public static final QFollowEntity followEntity = new QFollowEntity("followEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> followerId = createNumber("followerId", Long.class);

    public final NumberPath<Long> followingId = createNumber("followingId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final EnumPath<com.example.sns.core.follow.domain.FollowStatus> status = createEnum("status", com.example.sns.core.follow.domain.FollowStatus.class);

    public QFollowEntity(String variable) {
        super(FollowEntity.class, forVariable(variable));
    }

    public QFollowEntity(Path<? extends FollowEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFollowEntity(PathMetadata metadata) {
        super(FollowEntity.class, metadata);
    }

}

