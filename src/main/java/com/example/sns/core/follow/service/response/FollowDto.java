package com.example.sns.core.follow.service.response;

import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowDto {
    private final Long id;
    private final Long follower;
    private final Long following;
    private final FollowStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public FollowDto(Long id, Long follower, Long following, FollowStatus status, LocalDateTime createdAt,
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
                .follower(follow.getFollowerValue())
                .following(follow.getFollowingValue())
                .status(follow.getStatus())
                .createdAt(follow.getCreatedAt())
                .modifiedAt(follow.getModifiedAt())
                .build();

    }
    public static List<FollowDto> from(List<Follow> follows){
       return follows.stream().map(FollowDto::from).collect(Collectors.toList());
    }
    public Long getFollowerId(){
        return this.getFollower();
    }
    public Long getFollowingId(){
        return this.getFollowing();
    }
}
