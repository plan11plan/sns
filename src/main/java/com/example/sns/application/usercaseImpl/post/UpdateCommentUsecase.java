package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.service.CommentWriteService;
import com.example.sns.core.post.service.input.CommentUpdateInput;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.core.user.service.output.UserOutput;
import com.example.sns.presentation.post.controller.request.CommentUpdateRequest;
import com.example.sns.presentation.post.controller.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommentUsecase {
    private final CommentWriteService commentWriteService;

    public CommentResponse execute(Long id, CommentUpdateRequest request) {
        var commentUpdate = CommentUpdateInput.builder().content(request.toContent()).build();

        UserOutput userOutput = UserOutput.builder()
                .id(id)
                .build();
        CommentOutput comment = commentWriteService.update(commentUpdate,userOutput);
        return CommentResponse.from(comment);
    }
}