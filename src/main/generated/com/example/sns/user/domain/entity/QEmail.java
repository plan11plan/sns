package com.example.sns.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.sns.core.user.domain.entity.Email;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmail is a Querydsl query type for Email
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QEmail extends BeanPath<Email> {

    private static final long serialVersionUID = -69881345L;

    public static final QEmail email1 = new QEmail("email1");

    public final StringPath email = createString("email");

    public final StringPath emailToString = createString("emailToString");

    public QEmail(String variable) {
        super(Email.class, forVariable(variable));
    }

    public QEmail(Path<? extends Email> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmail(PathMetadata metadata) {
        super(Email.class, metadata);
    }

}

