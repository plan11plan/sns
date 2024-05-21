package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.domain.entity.request.CommentCreate;
import com.example.sns.core.post.service.CommentWriteService;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.presentation.post.controller.request.CommentCreateRequest;
import com.example.sns.presentation.post.controller.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentUsecase {
    private final CommentWriteService commentService;

    public CommentResponse execute(CommentCreateRequest request) {
        CommentCreate commentCreate = CommentCreate.builder()
                .postId(request.toPostId())
                .writerId(request.toWriterId())
                .parentId(request.toParentId())
                .content(request.toContent())
                .build();

        CommentOutput comment = commentService.create(commentCreate);
        return CommentResponse.from(comment);
    }
}