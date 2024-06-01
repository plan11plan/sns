package com.example.sns.presentation.post.controller.response;


import com.example.sns.core.post.domain.service.output.CommentOutput;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long commentId;
    private final Long postId;
    private final Long writerId;
    private final Long parentId;
    private final Long likeCount;
    private final String content;
    @Builder
    public CommentResponse(Long commentId, Long postId, Long writerId, Long parentId, Long likeCount, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.writerId = writerId;
        this.parentId = parentId;
        this.likeCount = likeCount;
        this.content = content;
    }


    public static CommentResponse from(CommentOutput output) {
        return CommentResponse.builder()
                .commentId(output.getCommetId())
                .postId(output.getPostId())
                .writerId(output.getWriterId())
                .likeCount(output.getLikeCount())
                .parentId(output.getParentId())
                .content(output.getContent())
                .build();
    }
}
