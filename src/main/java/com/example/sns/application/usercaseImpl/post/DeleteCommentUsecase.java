package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.domain.service.CommentWriteService;
import com.example.sns.core.post.domain.service.input.CommentDeleteInput;
import com.example.sns.core.user.domain.service.UserReadService;
import com.example.sns.core.user.domain.service.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentUsecase {
    private final CommentWriteService commentWriteService;
    private final UserReadService userReadService;

    public void execute(Long userId,Long id) {
        UserOutput user = userReadService.getById(userId);
        var commentDeleteInput = CommentDeleteInput.builder().id(id).build();
        commentWriteService.delete(commentDeleteInput);
    }
}