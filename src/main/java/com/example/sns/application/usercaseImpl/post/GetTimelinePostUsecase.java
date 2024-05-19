package com.example.sns.application.usercaseImpl.post;

import static com.example.sns.application.dto.post.GetPostsResponse.convertToGetPostsResponse;

import com.example.sns.application.dto.post.GetPostsResponse;
import com.example.sns.application.dto.post.GetTimelineByCursorCommand;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.service.PostLikeReadService;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.TimelineReadService;
import com.example.sns.core.post.service.dto.PostDto;
import com.example.sns.core.user.domain.entity.vo.UserProfile;
import com.example.sns.core.user.service.UserReadService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {
    private final UserReadService userReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;
    private final PostLikeReadService postLikeReadService;

    //TODO 1. Timeline 조회, timeline에 해당하는 게시물을 조회한다.
    public CursorResponse execute(GetTimelineByCursorCommand command){
        ensureUserExists(command.getUserId());

        // 커서화된 타임라인 가져옴. 궁금한게 커서화된 타임라인을 가져와야할까?
        CursorResponse<Timeline> pagedTimelines = timelineReadService.getTimelines(command.getUserId(),command.getCursorRequest());
        List<Long> postIds = pagedTimelines.getData().stream().map(Timeline::getPostId).toList();
        List<PostDto> posts = postReadService.getPosts(postIds);
        Map<Long, UserProfile> userProfiles = getUserProfiles(posts);
        Map<Long, Long> postLikes = postLikeReadService.getPostLikes(postIds);
        List<GetPostsResponse> postResponses = convertToGetTimelinesResponses(posts, userProfiles,postLikes);


        return new CursorResponse<>(pagedTimelines.getNextCursorRequest(),postResponses);
    }

    private void ensureUserExists(Long writerId) {
        userReadService.ensureWriterExists(writerId);
    }
    private Map<Long, UserProfile> getUserProfiles(List<PostDto> posts) {
        List<Long> writerIds = posts.stream()
                .map(post -> post.getWriterId().getValue())
                .distinct()
                .collect(Collectors.toList());
        return userReadService.getUserProfiles(writerIds);
    }

    private List<GetPostsResponse> convertToGetTimelinesResponses(List<PostDto> posts,
                                                              Map<Long, UserProfile> userProfiles,
                                                                  Map<Long, Long> postLikes) {
        return posts.stream()
                .map(post -> convertToGetPostsResponse(post, userProfiles,postLikes))
                .collect(Collectors.toList());
    }
}
