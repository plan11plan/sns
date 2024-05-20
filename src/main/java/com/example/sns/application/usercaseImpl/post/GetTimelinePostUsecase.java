package com.example.sns.application.usercaseImpl.post;

import com.example.sns.application.dto.post.GetPostsResponse;
import com.example.sns.application.dto.post.GetTimelineByCursorCommand;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.TimelineReadService;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.output.UserProfilesOutput;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {
    private final UserReadService userReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

    public CursorResponse<GetPostsResponse> execute(GetTimelineByCursorCommand command) {
        ensureUserExists(command.getUserId());

        CursorResponse<Timeline> pagedTimelines = timelineReadService.getTimelines(command.getUserId(), command.getCursorRequest());
        List<Long> postIds = pagedTimelines.getData().stream().map(Timeline::getPostId).toList();
        List<PostOutput> posts = postReadService.getPosts(postIds);
        UserProfilesOutput userProfiles = getUserProfiles(posts);

        List<GetPostsResponse> postResponses = convertToGetTimelinesResponses(posts, userProfiles);

        return new CursorResponse<>(pagedTimelines.getNextCursorRequest(), postResponses);
    }

    private void ensureUserExists(Long writerId) {
        userReadService.ensureWriterExists(writerId);
    }

    private UserProfilesOutput getUserProfiles(List<PostOutput> posts) {
        List<Long> writerIds = posts.stream()
                .map(PostOutput::getWriterId)
                .distinct()
                .collect(Collectors.toList());
        return userReadService.getUserProfiles(writerIds);
    }

    private List<GetPostsResponse> convertToGetTimelinesResponses(List<PostOutput> posts, UserProfilesOutput userProfiles) {
        return posts.stream()
                .map(post -> GetPostsResponse.convertToGetPostsResponse(post, userProfiles))
                .collect(Collectors.toList());
    }
}
