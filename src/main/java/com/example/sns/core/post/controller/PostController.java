package com.example.sns.core.post.controller;


import com.example.sns.core.post.controller.request.PostUpdateRequest;
import com.example.sns.core.post.controller.response.PostResponse;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.PostUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostUpdateService postUpdateService;
    private final PostReadService postRadService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable long id){
        return ResponseEntity.ok(PostResponse.from(postRadService.getById(id),null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable long id, @RequestBody PostUpdateRequest request){
        return ResponseEntity.ok(PostResponse.from(postUpdateService.update(id,request.toDomainRequest()),null));
    }
}
