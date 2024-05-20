package com.example.sns.application.usercaseImpl.post;


import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.service.PostLikeWriteService;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostLikeUsecase {
    private final PostReadService postReadService;
    private final UserReadService userReadService;
    private final PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long writerId){
        UserOutput userOutput = userReadService.getById(writerId);
        Post post = postReadService.getById(postId);
        postLikeWriteService.create(post, userOutput);
    }

}
