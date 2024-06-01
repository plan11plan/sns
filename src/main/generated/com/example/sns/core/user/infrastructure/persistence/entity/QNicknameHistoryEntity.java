package com.example.sns.core.user.infrastructure.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNicknameHistoryEntity is a Querydsl query type for NicknameHistoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNicknameHistoryEntity extends EntityPathBase<NicknameHistoryEntity> {

    private static final long serialVersionUID = -1474140835L;

    public static final QNicknameHistoryEntity nicknameHistoryEntity = new QNicknameHistoryEntity("nicknameHistoryEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QNicknameHistoryEntity(String variable) {
        super(NicknameHistoryEntity.class, forVariable(variable));
    }

    public QNicknameHistoryEntity(Path<? extends NicknameHistoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNicknameHistoryEntity(PathMetadata metadata) {
        super(NicknameHistoryEntity.class, metadata);
    }

}

