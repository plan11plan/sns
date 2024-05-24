package com.example.sns.application.usercaseImpl.post;


import com.example.sns.core.post.service.PostGetService;
import com.example.sns.core.post.service.PostLikeWriteService;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.output.UserOutput;
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
