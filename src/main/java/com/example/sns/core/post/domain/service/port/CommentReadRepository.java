package com.example.sns.core.post.domain.service.port;

import com.example.sns.core.post.domain.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentReadRepository {
    Optional<Comment> findById(Long id);
    Optional<List<Comment>> findByPostId(Long postId);
    Optional<List<Comment>> findByParentId(Long parentId);
}
