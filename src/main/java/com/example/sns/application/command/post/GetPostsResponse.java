package com.example.sns.application.command.post;

import com.example.sns.core.post.domain.service.output.PostOutput;
import com.example.sns.core.user.domain.service.output.UserProfileOutput;
import com.example.sns.core.user.domain.service.output.UserProfilesOutput;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetPostsResponse {
    Long postId;
    String title;
    String content;
    Long likeCount;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    UserProfileOutput userProfile;

    @Builder
    public GetPostsResponse(Long postId, String title, String content, LocalDateTime createdAt,
                            LocalDateTime modifiedAt, Long likeCount, UserProfileOutput userProfile) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
        this.modifiedAt = modifiedAt;
        this.userProfile = userProfile;
    }

    public static GetPostsResponse convertToGetPostsResponse(PostOutput post, UserProfilesOutput userProfiles) {
        UserProfileOutput userProfile = userProfiles.getUserProfile(post.getWriterId());
        return GetPostsResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .userProfile(userProfile)
                .likeCount(post.getLikeCount())
                .build();
    }
}
