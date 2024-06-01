package com.example.sns.presentation.post.controller;

import com.example.sns.application.usercaseImpl.post.CreateCommentUsecase;
import com.example.sns.application.usercaseImpl.post.DeleteCommentUsecase;
import com.example.sns.application.usercaseImpl.post.GetCommentRepliesUsecase;
import com.example.sns.application.usercaseImpl.post.GetCommentsByPostUsecase;
import com.example.sns.application.usercaseImpl.post.UpdateCommentUsecase;
import com.example.sns.presentation.post.controller.request.CommentCreateRequest;
import com.example.sns.presentation.post.controller.request.CommentUpdateRequest;
import com.example.sns.presentation.post.controller.response.CommentResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CreateCommentUsecase createCommentUsecase;
    private final GetCommentsByPostUsecase getCommentsByPostUsecase;
    private final UpdateCommentUsecase updateCommentUsecase;
    private final DeleteCommentUsecase deleteCommentUsecase;
    private final GetCommentRepliesUsecase getCommentRepliesUsecase;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentCreateRequest request) {
        CommentResponse response = createCommentUsecase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentResponse> responses = getCommentsByPostUsecase.execute(postId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{commentId}/replies")
    public ResponseEntity<List<CommentResponse>> getCommentReplies(@PathVariable Long commentId) {
        List<CommentResponse> responses = getCommentRepliesUsecase.execute(commentId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentUpdateRequest request) {
        CommentResponse response = updateCommentUsecase.execute(commentId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("userId") Long userId,
            @PathVariable("commentId") Long commentId) {
        deleteCommentUsecase.execute(userId,commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
