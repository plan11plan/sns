package com.example.sns.application.usercaseImpl.post;

import static com.example.sns.application.dto.post.GetPostsResponse.convertToGetPostsResponse;

import com.example.sns.application.dto.post.GetPostsByCursorCommand;
import com.example.sns.application.dto.post.GetPostsResponse;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.input.PostGeyByCursorInput;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.output.UserProfilesOutput;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostsByCursorUsecase {
    private final UserReadService userReadService;
    private final PostReadService postReadService;
    private final String POST_STATUS_PUBLISHED = "PUBLISHED";

    public CursorResponse<GetPostsResponse> execute(GetPostsByCursorCommand command) {
        ensureWriterExists(command.getWriterId());

        var postGeyByCursor = createPostGeyByCursor(command);
        var posts = postReadService.getPostsByCursor(postGeyByCursor);
        System.out.println("Posts from getPostsByCursor: " + posts.getData()); // 로그 추가
        var userProfiles = getUserProfiles(posts);
        System.out.println("User Profiles: " + userProfiles.getUserProfiles().entrySet()); // 로그 추가

        List<GetPostsResponse> postResponses = convertToGetPostsResponses(posts, userProfiles);
        System.out.println("Post Responses: " + postResponses.stream().map(GetPostsResponse::getTitle)); // 로그 추가

        return new CursorResponse<>(posts.getNextCursorRequest(), postResponses);
    }

    private void ensureWriterExists(Long writerId) {
        userReadService.ensureWriterExists(writerId);
    }

    private PostGeyByCursorInput createPostGeyByCursor(GetPostsByCursorCommand command) {
        return PostGeyByCursorInput.builder()
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
                .map(post -> convertToGetPostsResponse(post, userProfiles))
                .collect(Collectors.toList());
    }
}
