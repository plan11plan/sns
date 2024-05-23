package com.example.sns.core.chat.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessageEntity is a Querydsl query type for MessageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessageEntity extends EntityPathBase<MessageEntity> {

    private static final long serialVersionUID = -210668747L;

    public static final QMessageEntity messageEntity = new QMessageEntity("messageEntity");

    public final NumberPath<Long> chatRoomId = createNumber("chatRoomId", Long.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isRead = createBoolean("isRead");

    public final NumberPath<Long> senderId = createNumber("senderId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> sentAt = createDateTime("sentAt", java.time.LocalDateTime.class);

    public final EnumPath<com.example.sns.core.chat.domain.MessageStatus> status = createEnum("status", com.example.sns.core.chat.domain.MessageStatus.class);

    public QMessageEntity(String variable) {
        super(MessageEntity.class, forVariable(variable));
    }

    public QMessageEntity(Path<? extends MessageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessageEntity(PathMetadata metadata) {
        super(MessageEntity.class, metadata);
    }

}

