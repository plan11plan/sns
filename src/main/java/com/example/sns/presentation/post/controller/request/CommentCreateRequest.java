package com.example.sns.presentation.post.controller.request;

import com.example.sns.core.post.domain.entity.CommentContent;
import com.example.sns.core.post.domain.entity.CommentId;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.WriterId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private final Long postId;
    private final Long writerId;
    private final Long parentId;
    private final String content;

    @Builder
    public CommentCreateRequest(Long postId, Long writerId, Long parentId, String content) {
        this.postId = postId;
        this.writerId = writerId;
        this.parentId = parentId;
        this.content = content;
    }

    public PostId toPostId() {
        return new PostId(postId);
    }

    public WriterId toWriterId() {
        return new WriterId(writerId);
    }

    public CommentId toParentId() {
        return parentId != null ? new CommentId(parentId) : null;
    }

    public CommentContent toContent() {
        return new CommentContent(content);
    }
}
