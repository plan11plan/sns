package com.example.sns.application.usercaseImpl.post;


import com.example.sns.core.post.service.PostGetService;
import com.example.sns.core.post.service.PostLikeReadService;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.output.UserOutput;
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
