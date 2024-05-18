package com.example.sns.application.dto.post;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.user.domain.entity.vo.UserProfile;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetPostsResponse {
    Long postId;
    String title;
    String content;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    UserProfile userProfile;

    @Builder
    public GetPostsResponse(Long postId, String title, String content, LocalDateTime createdAt,
                            LocalDateTime modifiedAt,
                            UserProfile userProfile) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userProfile = userProfile;
    }
    public static GetPostsResponse convertToGetPostsResponse(Post post, Map<Long, UserProfile> userProfiles) {
        UserProfile userProfile = userProfiles.get(post.getWriterId().getValue());
        return GetPostsResponse.builder()
                .postId(post.getId())
                .title(post.getTitle().getValue())
                .content(post.getContent().getValue())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .userProfile(userProfile)
                .build();
    }
}
