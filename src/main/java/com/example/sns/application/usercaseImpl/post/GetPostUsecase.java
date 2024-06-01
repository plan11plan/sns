package com.example.sns.application.usercaseImpl.post;


import com.example.sns.core.post.domain.service.PostGetService;
import com.example.sns.core.post.domain.service.PostLikeReadService;
import com.example.sns.core.post.domain.service.output.PostOutput;
import com.example.sns.core.user.domain.service.UserReadService;
import com.example.sns.core.user.domain.service.output.UserOutput;
import com.example.sns.presentation.post.controller.response.PostResponse;
import com.example.sns.presentation.user.controller.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostUsecase {
    private final PostGetService postGetService;
    private final UserReadService userReadService;
    private final PostLikeReadService postLikeReadService;

    public ResponseEntity<PostResponse> getById(Long id) {
        UserOutput userOutput = userReadService.getById(id);
        PostOutput postOutput = postGetService.getById(id);
        Long postLike = postLikeReadService.getPostLike(postOutput.getId()).getLikeCount();
        postOutput = postOutput.setLikeCount(postLike);

        return ResponseEntity.ok(PostResponse.from(postOutput, UserResponse.from(userOutput)));
    }
}
