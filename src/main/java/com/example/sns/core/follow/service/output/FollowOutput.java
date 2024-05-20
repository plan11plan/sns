package com.example.sns.core.follow.service.output;

import com.example.sns.core.follow.domain.Follow;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowOutput {
    private final Long id;
    private final Long follower;
    private final Long following;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    public FollowOutput(Long id, Long follower, Long following, String status, LocalDateTime createdAt,
                        LocalDateTime modifiedAt) {
        this.id = id;
        this.follower = follower;
        this.following = following;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static FollowOutput from(Follow follow){
        return FollowOutput.builder()
                .id(follow.getId())
                .follower(follow.getFollowerValue())
                .following(follow.getFollowingValue())
                .status(follow.getStatus().name())
                .createdAt(follow.getCreatedAt())
                .modifiedAt(follow.getModifiedAt())
                .build();

    }
    public static List<FollowOutput> from(List<Follow> follows){
       return follows.stream().map(FollowOutput::from).collect(Collectors.toList());
    }
    public Long getFollowerId(){
        return this.getFollower();
    }
    public Long getFollowingId(){
        return this.getFollowing();
    }
}
