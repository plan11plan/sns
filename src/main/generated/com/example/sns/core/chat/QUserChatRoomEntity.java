package com.example.sns.core.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserChatRoomEntity is a Querydsl query type for UserChatRoomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserChatRoomEntity extends EntityPathBase<UserChatRoomEntity> {

    private static final long serialVersionUID = -1471176081L;

    public static final QUserChatRoomEntity userChatRoomEntity = new QUserChatRoomEntity("userChatRoomEntity");

    public final NumberPath<Long> chatRoomId = createNumber("chatRoomId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserChatRoomEntity(String variable) {
        super(UserChatRoomEntity.class, forVariable(variable));
    }

    public QUserChatRoomEntity(Path<? extends UserChatRoomEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserChatRoomEntity(PathMetadata metadata) {
        super(UserChatRoomEntity.class, metadata);
    }

}

