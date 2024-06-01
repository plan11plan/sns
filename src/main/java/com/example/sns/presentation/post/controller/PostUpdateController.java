package com.example.sns.presentation.post.controller;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.service.PostUpdateService;
import com.example.sns.presentation.post.controller.request.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostUpdateController {
    private final PostUpdateService postUpdateService;


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') && hasPermission(#id,'POST','PUT')")
    public Post update(@PathVariable Long id, @RequestBody PostUpdateRequest request){
        Post update = postUpdateService.update(id, request.toDomainRequest());
        return update;
    }

}
