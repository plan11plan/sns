package com.example.sns.application.usercaseImpl.post;

import com.example.sns.application.dto.post.GetPostsResponse;
import com.example.sns.application.dto.post.GetTimelineByCursorCommand;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.service.CursorPagingPostService;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.output.UserProfilesOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {
    private final UserReadService userReadService;
    private final CursorPagingPostService cursorPagingPostService;

    public CursorResponse<GetPostsResponse> execute(GetTimelineByCursorCommand command) {
        ensureUserExists(command.getUserId());

        CursorResponse<PostOutput> posts = cursorPagingPostService.getPostsByTimeline(command.getUserId(), command.getCursorRequest());
        UserProfilesOutput userProfiles = getUserProfiles(posts);

        List<GetPostsResponse> postResponses = convertToGetTimelinesResponses(posts, userProfiles);

        return new CursorResponse<>(posts.getNextCursorRequest(), postResponses);
    }

    private void ensureUserExists(Long writerId) {
        userReadService.ensureWriterExists(writerId);
    }

    private UserProfilesOutput getUserProfiles(CursorResponse<PostOutput> posts) {
        List<Long> writerIds = posts.getData().stream()
                .map(PostOutput::getWriterId)
                .distinct()
                .collect(Collectors.toList());
        return userReadService.getUserProfiles(writerIds);
    }

    private List<GetPostsResponse> convertToGetTimelinesResponses(CursorResponse<PostOutput> posts, UserProfilesOutput userProfiles) {
        return posts.getData().stream()
                .map(post -> GetPostsResponse.convertToGetPostsResponse(post, userProfiles))
                .collect(Collectors.toList());
    }
}
