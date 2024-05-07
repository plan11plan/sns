package com.example.sns.core.post.controller;

import com.example.sns.core.post.controller.request.PostCreateRequest;
import com.example.sns.core.post.controller.response.PostResponse;
import com.example.sns.core.post.service.PostCreateService;
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

    private final PostCreateService postCreateService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PostResponse.from(postCreateService.create(request.toDomainRequest()),null));
    }
}