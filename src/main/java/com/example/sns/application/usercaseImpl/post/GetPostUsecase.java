package com.example.sns.application.usercaseImpl.post;


import com.example.sns.core.post.controller.response.PostResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.dto.PostDto;
import com.example.sns.core.user.controller.response.UserResponse;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostUsecase {
    private final PostReadService postReadService;
    private final UserReadService userReadService;

    public ResponseEntity<PostResponse> getById(Long id) {
        UserDto userDto = userReadService.getById(id);
        Post post = postReadService.getById(id);

        PostDto postDto = PostDto.from(post);
        return ResponseEntity.ok(PostResponse.from(postDto, UserResponse.from(userDto)));
    }
}
