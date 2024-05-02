package com.example.sns.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.sns.core.user.domain.entity.Nickname;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNickname is a Querydsl query type for Nickname
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QNickname extends BeanPath<Nickname> {

    private static final long serialVersionUID = 533627691L;

    public static final QNickname nickname1 = new QNickname("nickname1");

    public final StringPath nickname = createString("nickname");

    public final StringPath nicknameToString = createString("nicknameToString");

    public QNickname(String variable) {
        super(Nickname.class, forVariable(variable));
    }

    public QNickname(Path<? extends Nickname> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNickname(PathMetadata metadata) {
        super(Nickname.class, metadata);
    }

}

