package com.example.sns.application.usercaseImpl.post;


import com.example.sns.application.dto.post.GetPostsByCursorCommand;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.dto.PostGeyByCursor;
import com.example.sns.core.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostsByCursorUsecase {
    private final UserReadService userReadService;
    private final PostReadService postReadService;


    //TODO
    public CursorResponse<Post> execute(GetPostsByCursorCommand command) {
        Long getWriterId = userReadService.getById(command.getWriterId()).getId();

        PostGeyByCursor postGeyByCursor = PostGeyByCursor.builder()
                .writerId(getWriterId)
                .status(PostStatus.PUBLISHED)
                .cursorRequest(command.getCursorRequest())
                .build();

        CursorResponse<Post> posts = postReadService.getPostsByCursor(postGeyByCursor);

        return posts;
    }


}
