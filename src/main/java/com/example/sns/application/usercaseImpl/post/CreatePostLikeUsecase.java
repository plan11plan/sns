package com.example.sns.application.usercaseImpl.post;


import com.example.sns.core.post.domain.service.PostGetService;
import com.example.sns.core.post.domain.service.PostLikeWriteService;
import com.example.sns.core.post.domain.service.output.PostOutput;
import com.example.sns.core.user.domain.service.UserReadService;
import com.example.sns.core.user.domain.service.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostLikeUsecase {
    private final PostGetService postGetService;
    private final UserReadService userReadService;
    private final PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long writerId){
        UserOutput userOutput = userReadService.getById(writerId);
        PostOutput postOutput = postGetService.getById(postId);
        postLikeWriteService.create(postOutput, userOutput);
    }

}
