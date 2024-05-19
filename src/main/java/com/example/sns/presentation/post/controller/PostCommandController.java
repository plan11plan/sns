package com.example.sns.presentation.post.controller;


import com.example.sns.application.usercaseImpl.post.CreatePostLikeUsecase;
import com.example.sns.core.post.service.port.PostLikeReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostCommandController {

    private final CreatePostLikeUsecase createPostLikeUsecase;
    private final PostLikeReadRepository postLikeReadRepository;

    //TODO 좋아요 스케쥴러
    @PostMapping("/{postId}/like")
    public void likePost(@PathVariable("postId") Long postId, @RequestParam("userId") Long userId) {
        createPostLikeUsecase.execute(postId, userId);
    }

    @GetMapping("/{postId}/like")
    public Long getPosLike(@PathVariable("postId") Long postId) {
        return postLikeReadRepository.getCount(postId);
    }
}
