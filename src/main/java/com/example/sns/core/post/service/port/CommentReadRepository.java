package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentReadRepository {
    Optional<Comment> findById(Long id);
    List<Comment> findByPostId(Long postId);
    List<Comment> findByParentId(Long parentId);
}
