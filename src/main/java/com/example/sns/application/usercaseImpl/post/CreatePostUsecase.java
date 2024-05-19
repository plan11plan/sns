package com.example.sns.application.usercaseImpl.post;


import com.example.sns.application.dto.post.CreatePostCommand;
import com.example.sns.core.follow.service.read.FollowReadService;
import com.example.sns.core.follow.service.request.FollowGetDto;
import com.example.sns.core.follow.service.response.FollowDto;
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

    //TODO : 트랜잭션을 붙여야할까?  포스트 생성 후, delivery할 대상이 100만명이라면? 생성 속도가 늦다. 트랜잭션 동안 커넥션풀을 차지하고 있는 문제발생.
    public void execute(CreatePostCommand command){
        Long writerId = userReadService.getById(command.getWriterId()).getId();
        PostCreate postCreate = getPostCreate(command);
        Long postId = postCreateService.create(postCreate).getId();
        List<Long> followerIds = followReadService.getFollowers(FollowGetDto.fromId(writerId))
                .stream()
                .map(FollowDto::getFollowerId)
                .toList();
       timelineWriteService.deliveryToTimeline(postId,followerIds);

    }

    private static PostCreate getPostCreate(CreatePostCommand command) {
        PostCreate postCreate = PostCreate.builder()
                .writerId(command.getWriterId())
                .title(command.getTitle())
                .status("PUBLISHED")
                .content(command.getContent())
                .build();
        return postCreate;
    }

}
