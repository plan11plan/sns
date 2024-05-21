package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Comment;

public interface CommentWriteRepository {
    Comment save(Comment comment);

    void delete(Comment comment);
}
