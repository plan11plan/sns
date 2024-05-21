package com.example.sns.application.usercaseImpl.post;

import com.example.sns.core.post.service.CommentReadService;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.presentation.post.controller.response.CommentResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentsByPostUsecase {
    private final CommentReadService commentReadService;

    public List<CommentResponse> execute(Long postId) {
        List<CommentOutput> comments = commentReadService.getByPostId(postId);
        return comments.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }
}