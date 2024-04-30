package com.example.sns.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAge is a Querydsl query type for Age
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAge extends BeanPath<Age> {

    private static final long serialVersionUID = 326179874L;

    public static final QAge age1 = new QAge("age1");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public QAge(String variable) {
        super(Age.class, forVariable(variable));
    }

    public QAge(Path<? extends Age> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAge(PathMetadata metadata) {
        super(Age.class, metadata);
    }

}

