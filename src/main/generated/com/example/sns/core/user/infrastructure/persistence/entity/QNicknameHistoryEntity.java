package com.example.sns.core.user.infrastructure.persistence.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNicknameHistoryEntity is a Querydsl query type for NicknameHistoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNicknameHistoryEntity extends EntityPathBase<NicknameHistoryEntity> {

    private static final long serialVersionUID = -1474140835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNicknameHistoryEntity nicknameHistoryEntity = new QNicknameHistoryEntity("nicknameHistoryEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.sns.core.user.domain.entity.QNickname nickname;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QNicknameHistoryEntity(String variable) {
        this(NicknameHistoryEntity.class, forVariable(variable), INITS);
    }

    public QNicknameHistoryEntity(Path<? extends NicknameHistoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNicknameHistoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNicknameHistoryEntity(PathMetadata metadata, PathInits inits) {
        this(NicknameHistoryEntity.class, metadata, inits);
    }

    public QNicknameHistoryEntity(Class<? extends NicknameHistoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nickname = inits.isInitialized("nickname") ? new com.example.sns.core.user.domain.entity.QNickname(forProperty("nickname")) : null;
    }

}

