package com.example.sns.core.follow.service.port;

import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import java.util.List;
import java.util.Optional;

public interface FollowRepository {
    Optional<Follow> findFollowByFollowerIdAndFollowingIdAndStatus(Long followerId, Long followingId, FollowStatus status);
    //
    Optional<Follow> findFollowByFollowerIdAndFollowingId(Long followerId, Long followingId);


    // 팔로워 관계를 조회 (내가 팔로우하는 사람)
    Optional<Follow> findFollowingByUserIdAndFollowingId(Long userId, Long followingId, FollowStatus status);


    // 팔로잉 관계를 조회 (나를 팔로우하는 사람)
    Optional<Follow> findFollowerByUserIdAndFollowerId(Long userId, Long followerId, FollowStatus status);

    Follow save(Follow follow);


    Optional<List<Follow>> findAllByFollowerIdAndStatus(Long followerId, FollowStatus status);

    Optional<List<Follow>> findAllByFollowingIdAndStatus(Long followingId,FollowStatus status);

    void delete(Follow follow);
}
