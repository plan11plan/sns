package com.example.sns.core.follow.infrastructure.persistence;

import com.example.sns.core.follow.domain.FollowStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long> {
    Optional<List<FollowEntity>> findAllByFollowerIdAndStatus(Long followerId, FollowStatus status);

    Optional<List<FollowEntity>> findAllByFollowingIdAndStatus(Long followingId, FollowStatus status);

    @Query("SELECT f FROM FollowEntity f WHERE f.followerId = :followerId AND f.followingId = :followingId AND f.status = :status")
    Optional<FollowEntity> findByFollowerIdAndFollowingIdAndStatus(Long followerId, Long followingId, FollowStatus status);
    @Query("SELECT f FROM FollowEntity f WHERE f.followerId = :userId AND f.followingId = :followingId AND f.status = :status")
    Optional<FollowEntity> findFollowingByUserIdAndFollowingId(Long userId, Long followingId, FollowStatus status);

    @Query("SELECT f FROM FollowEntity f WHERE f.followingId = :userId AND f.followerId = :followerId AND f.status = :status")
    Optional<FollowEntity> findFollowerByUserIdAndFollowerId(Long userId, Long followerId, FollowStatus status);
}
