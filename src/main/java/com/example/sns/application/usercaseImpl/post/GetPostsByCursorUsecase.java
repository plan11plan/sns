package com.example.sns.application.usercaseImpl.post;

import com.example.sns.application.command.post.GetPostsByCursorCommand;
import com.example.sns.application.command.post.GetPostsResponse;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.service.CursorPagingPostService;
import com.example.sns.core.post.domain.service.input.PostGetByCursorInput;
import com.example.sns.core.post.domain.service.output.PostOutput;
import com.example.sns.core.user.domain.service.UserReadService;
import com.example.sns.core.user.domain.service.output.UserProfilesOutput;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostsByCursorUsecase {
    private final UserReadService userReadService;
    private final CursorPagingPostService cursorPagingPostService;
    private final String POST_STATUS_PUBLISHED = "PUBLISHED";

    public CursorResponse<GetPostsResponse> execute(GetPostsByCursorCommand command) {
        ensureWriterExists(command.getWriterId());

        PostGetByCursorInput postGetByCursor = createPostGetByCursor(command);
        var posts = cursorPagingPostService.getPostsByCursor(postGetByCursor.getWriterId(),
                postGetByCursor.getStatus(),
                postGetByCursor.getCursorRequest());
        var userProfiles = getUserProfiles(posts);

        List<GetPostsResponse> postResponses = convertToGetPostsResponses(posts, userProfiles);

        return new CursorResponse<>(posts.getNextCursorRequest(), postResponses);
    }

    private void ensureWriterExists(Long writerId) {
        userReadService.ensureWriterExists(writerId);
    }

    private PostGetByCursorInput createPostGetByCursor(GetPostsByCursorCommand command) {
        return PostGetByCursorInput.builder()
                .writerId(command.getWriterId())
                .status(POST_STATUS_PUBLISHED)
                .cursorRequest(command.getCursorRequest())
                .build();
    }

    private UserProfilesOutput getUserProfiles(CursorResponse<PostOutput> posts) {
        List<Long> writerIds = posts.getData().stream()
                .map(PostOutput::getWriterId)
                .distinct()
                .collect(Collectors.toList());
        return userReadService.getUserProfiles(writerIds);
    }

    private List<GetPostsResponse> convertToGetPostsResponses(CursorResponse<PostOutput> posts, UserProfilesOutput userProfiles) {
        return posts.getData().stream()
                .map(post -> GetPostsResponse.convertToGetPostsResponse(post, userProfiles))
                .collect(Collectors.toList());
    }
}
