package com.example.sns.core.follow.service.response;

import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.Follower;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowDto {
    private final java.lang.Long id;
    private final Follower follower;
    private final Following following;
    private final FollowStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public FollowDto(java.lang.Long id, Follower follower, Following following, FollowStatus status, LocalDateTime createdAt,
                     LocalDateTime modifiedAt) {
        this.id = id;
        this.follower = follower;
        this.following = following;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static FollowDto from(Follow follow){
        return FollowDto.builder()
                .id(follow.getId())
                .follower(follow.getFollower())
                .following(follow.getFollowing())
                .status(follow.getStatus())
                .createdAt(follow.getCreatedAt())
                .modifiedAt(follow.getModifiedAt())
                .build();

    }
    public static List<FollowDto> from(List<Follow> follows){
       return follows.stream().map(FollowDto::from).collect(Collectors.toList());
    }
    public java.lang.Long getFollowerId(){
        return this.getFollower().getId();
    }
    public java.lang.Long getFollowingId(){
        return this.getFollowing().getId();
    }
}
