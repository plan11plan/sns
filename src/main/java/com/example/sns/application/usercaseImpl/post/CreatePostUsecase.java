package com.example.sns.application.usercaseImpl.post;


import com.example.sns.application.dto.post.CreatePostCommand;
import com.example.sns.core.follow.service.read.FollowReadService;
import com.example.sns.core.follow.service.request.FollowGetDto;
import com.example.sns.core.follow.service.response.FollowDto;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.service.PostCreateService;
import com.example.sns.core.post.service.TimelineWriteService;
import com.example.sns.core.user.service.UserReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostUsecase {
    private final UserReadService userReadService;
    private final FollowReadService followReadService;
    private final PostCreateService postCreateService;
    private final TimelineWriteService timelineWriteService;

    public void execute(CreatePostCommand command){
        Long writerId = command.getWriterId();
        PostCreate postCreate = PostCreate.builder()
                .writerId(command.getWriter())
                .title(command.getTitle())
                .status(PostStatus.PUBLISHED)
                .content(command.getContent())
                .build();
        Long postId = postCreateService.create(postCreate).getId();
        List<Long> followerIds = followReadService.getFollowers(FollowGetDto.fromId(writerId))
                .stream()
                .map(FollowDto::getFollowerId)
                .toList();
       timelineWriteService.deliveryToTimeline(postId,followerIds);

    }

}
