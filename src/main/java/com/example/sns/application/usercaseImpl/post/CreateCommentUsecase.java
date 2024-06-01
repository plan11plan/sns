package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.domain.service.CommentWriteService;
import com.example.sns.core.post.domain.service.PostGetService;
import com.example.sns.core.post.domain.service.input.CommentCreateInput;
import com.example.sns.core.post.domain.service.output.CommentOutput;
import com.example.sns.core.post.domain.service.output.PostOutput;
import com.example.sns.core.user.domain.service.UserReadService;
import com.example.sns.core.user.domain.service.output.UserOutput;
import com.example.sns.presentation.post.controller.request.CommentCreateRequest;
import com.example.sns.presentation.post.controller.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentUsecase {
    private final CommentWriteService commentService;
    private final UserReadService  userReadService;
    private final PostGetService postGetService;

    public CommentResponse execute(CommentCreateRequest request) {

        UserOutput writerOutput = userReadService.getById(request.getWriterId());
        PostOutput postOutput = postGetService.getById(request.getPostId());

        CommentCreateInput commentCreate = CommentCreateInput.builder()
                .postId(postOutput.getId())
                .writerId(writerOutput.getId())
                .parentId(request.getParentId())
                .content(request.getContent())
                .build();

        CommentOutput comment = commentService.create(commentCreate);
        return CommentResponse.from(comment);
    }
}