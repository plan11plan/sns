package com.example.sns.application.usercaseImpl.post;

import static com.example.sns.application.dto.post.GetPostsResponse.convertToGetPostsResponse;

import com.example.sns.application.dto.post.GetPostsByCursorCommand;
import com.example.sns.application.dto.post.GetPostsResponse;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.dto.PostGeyByCursor;
import com.example.sns.core.user.domain.entity.vo.UserProfile;
import com.example.sns.core.user.service.UserReadService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostsByCursorUsecase {
    private final UserReadService userReadService;
    private final PostReadService postReadService;

    public CursorResponse<GetPostsResponse> execute(GetPostsByCursorCommand command) {
        ensureWriterExists(command.getWriterId());

        var postGeyByCursor = createPostGeyByCursor(command);
        var posts = postReadService.getPostsByCursor(postGeyByCursor);
        var userProfiles = getUserProfiles(posts);
        List<GetPostsResponse> postResponses = convertToGetPostsResponses(posts, userProfiles);

        return new CursorResponse<>(posts.getNextCursorRequest(), postResponses);
    }

    private void ensureWriterExists(Long writerId) {
        userReadService.ensureWriterExists(writerId);
    }
    private PostGeyByCursor createPostGeyByCursor(GetPostsByCursorCommand command) {
        return PostGeyByCursor.builder()
                .writerId(command.getWriterId())
                .status(PostStatus.PUBLISHED)
                .cursorRequest(command.getCursorRequest())
                .build();
    }

    private Map<Long, UserProfile> getUserProfiles(CursorResponse<Post> posts) {
        List<Long> writerIds = posts.getData().stream()
                .map(post -> post.getWriterId().getValue())
                .distinct()
                .collect(Collectors.toList());
        return userReadService.getUserProfiles(writerIds);
    }

    private List<GetPostsResponse> convertToGetPostsResponses(CursorResponse<Post> posts,
                                                              Map<Long, UserProfile> userProfiles) {
        return posts.getData().stream()
                .map(post -> convertToGetPostsResponse(post, userProfiles))
                .collect(Collectors.toList());
    }


}
