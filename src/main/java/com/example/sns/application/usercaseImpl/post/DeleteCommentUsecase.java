package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.service.CommentWriteService;
import com.example.sns.core.post.service.input.CommentDeleteInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentUsecase {
    private final CommentWriteService commentWriteService;

    public void execute(Long id) {
        var commentDeleteInput = CommentDeleteInput.builder().id(id).build();
        commentWriteService.delete(commentDeleteInput);
    }
}