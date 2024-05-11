package com.example.sns.core.follow.infrastructure.persistence;

import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.Follower;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follows")
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long followerId;
    private Long followingId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FollowStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public FollowEntity(Long id, Follower follower, Following following, FollowStatus status, LocalDateTime createdAt,
                        LocalDateTime modifiedAt) {
        this.id = id;
        this.followerId = follower.getId();
        this.followingId = following.getId();
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static FollowEntity from(Follow follow) {
        return FollowEntity.builder()
                .id(follow.getId())
                .follower(follow.getFollower())
                .following(follow.getFollowing())
                .status(follow.getStatus())
                .createdAt(follow.getCreatedAt())
                .modifiedAt(follow.getModifiedAt())
                .build();
    }

    public Follow toModel() {
        return Follow.builder()
                .id(id)
                .follower(Follower.fromId(followerId))
                .following(Following.fromId(followingId))
                .status(status)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();

    }
}
