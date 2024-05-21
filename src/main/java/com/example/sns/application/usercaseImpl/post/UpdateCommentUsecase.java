package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.domain.entity.request.CommentUpdate;
import com.example.sns.core.post.service.CommentWriteService;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.presentation.post.controller.request.CommentUpdateRequest;
import com.example.sns.presentation.post.controller.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommentUsecase {
    private final CommentWriteService commentWriteService;

    public CommentResponse execute(Long id, CommentUpdateRequest request) {
        CommentUpdate commentUpdate = CommentUpdate.builder().content(request.toContent()).build();

        CommentOutput comment = commentWriteService.update(id, commentUpdate);
        return CommentResponse.from(comment);
    }
}