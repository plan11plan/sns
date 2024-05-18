package com.example.sns.application.usercaseImpl.post;

import com.example.sns.application.dto.post.GetTimelineByCursorCommand;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.TimelineReadService;
import com.example.sns.core.post.service.dto.PostDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

    //TODO 1. Timeline 조회, timeline에 해당하는 게시물을 조회한다.
    public CursorResponse execute(GetTimelineByCursorCommand command){
        CursorResponse<Timeline> pagedTimelines = timelineReadService.getTimelines(command.getUserId(),
                command.getCursorRequest());
        List<Long> postIds = pagedTimelines.getData().stream().map(Timeline::getPostId).toList();
        List<PostDto> posts = postReadService.getPosts(postIds);
        return new CursorResponse<>(pagedTimelines.getNextCursorRequest(),posts);
    }
}
