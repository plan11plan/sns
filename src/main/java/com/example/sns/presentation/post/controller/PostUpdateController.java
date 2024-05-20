package com.example.sns.presentation.post.controller;

import com.example.sns.core.post.service.PostUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostUpdateController {
    private final PostUpdateService postUpdateService;


//    @PutMapping("/{id}")
//    public ResponseEntity<PostResponse> update(@PathVariable Long id, @RequestBody PostUpdateRequest request){
//        return ResponseEntity.ok(PostResponse.from(PostOutput.from(postUpdateService.update(id,request.toDomainRequest())),null));
//    }

}
