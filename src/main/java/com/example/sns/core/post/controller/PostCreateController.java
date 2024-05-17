package com.example.sns.core.post.controller;

import com.example.sns.application.dto.post.CreatePostCommand;
import com.example.sns.application.usercaseImpl.post.CreatePostUsecase;
import com.example.sns.core.post.controller.request.PostCreateRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@Builder
@RequiredArgsConstructor
public class PostCreateController {


    private final CreatePostUsecase createPostUsecase;

    @PostMapping
    public ResponseEntity create(@RequestBody PostCreateRequest request) {
        CreatePostCommand command = CreatePostCommand.builder()
                .writerId(request.getWriterId())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        createPostUsecase.execute(command);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(request.getWriterId().getWriterId());
    }
}