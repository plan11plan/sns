package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.domain.service.CommentReadService;
import com.example.sns.core.post.domain.service.output.CommentOutput;
import com.example.sns.presentation.post.controller.response.CommentResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentRepliesUsecase {
    private final CommentReadService commentReadService;

    public List<CommentResponse> execute(Long commentId) {
        List<CommentOutput> replies = commentReadService.getReplies(commentId);
        return replies.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}