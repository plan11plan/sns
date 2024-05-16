package com.example.sns.core.post.infrastructure.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimelineEntity is a Querydsl query type for TimelineEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimelineEntity extends EntityPathBase<TimelineEntity> {

    private static final long serialVersionUID = -890102576L;

    public static final QTimelineEntity timelineEntity = new QTimelineEntity("timelineEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QTimelineEntity(String variable) {
        super(TimelineEntity.class, forVariable(variable));
    }

    public QTimelineEntity(Path<? extends TimelineEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimelineEntity(PathMetadata metadata) {
        super(TimelineEntity.class, metadata);
    }

}

