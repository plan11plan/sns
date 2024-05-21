package com.example.sns.core.post.infrastructure.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentId is a Querydsl query type for CommentId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCommentId extends BeanPath<CommentId> {

    private static final long serialVersionUID = -593539314L;

    public static final QCommentId commentId = new QCommentId("commentId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCommentId(String variable) {
        super(CommentId.class, forVariable(variable));
    }

    public QCommentId(Path<? extends CommentId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentId(PathMetadata metadata) {
        super(CommentId.class, metadata);
    }

}

