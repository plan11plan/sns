package com.example.sns.user.domain.entity.root;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1450986886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.example.sns.user.domain.entity.QAge age;

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final com.example.sns.user.domain.entity.QEmail email;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.sns.user.domain.entity.QName name;

    public final com.example.sns.user.domain.entity.QNickname nickname;

    public final com.example.sns.user.domain.entity.QPassword password;

    public final EnumPath<com.example.sns.user.domain.entity.Sex> sex = createEnum("sex", com.example.sns.user.domain.entity.Sex.class);

    public final EnumPath<com.example.sns.user.domain.entity.UserStatus> userStatus = createEnum("userStatus", com.example.sns.user.domain.entity.UserStatus.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.age = inits.isInitialized("age") ? new com.example.sns.user.domain.entity.QAge(forProperty("age")) : null;
        this.email = inits.isInitialized("email") ? new com.example.sns.user.domain.entity.QEmail(forProperty("email")) : null;
        this.name = inits.isInitialized("name") ? new com.example.sns.user.domain.entity.QName(forProperty("name")) : null;
        this.nickname = inits.isInitialized("nickname") ? new com.example.sns.user.domain.entity.QNickname(forProperty("nickname")) : null;
        this.password = inits.isInitialized("password") ? new com.example.sns.user.domain.entity.QPassword(forProperty("password")) : null;
    }

}

