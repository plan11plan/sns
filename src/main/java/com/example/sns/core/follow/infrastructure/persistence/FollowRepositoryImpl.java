package com.example.sns.core.follow.infrastructure.persistence;

import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.service.port.FollowRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FollowRepositoryImpl implements FollowRepository {
    private final FollowJpaRepository followJpaRepository;

    @Override
    public Optional<Follow> findFollowByFollowerIdAndFollowingIdAndStatus(Long followerId, Long followingId, FollowStatus status) {
        return followJpaRepository.findByFollowerIdAndFollowingIdAndStatus(followerId, followingId, status)
                .map(FollowEntity::toModel);
    }

    @Override
    public Optional<Follow> findFollowByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        return followJpaRepository.findByFollowerIdAndFollowingId(followerId, followingId)
                .map(FollowEntity::toModel);    }

    @Override
    public Optional<Follow> findFollowingByUserIdAndFollowingId(Long userId, Long followingId, FollowStatus status) {
        return followJpaRepository.findFollowingByUserIdAndFollowingId(userId, followingId, status)
                .map(FollowEntity::toModel);
    }

    @Override
    public Optional<Follow> findFollowerByUserIdAndFollowerId(Long userId, Long followerId, FollowStatus status) {
        return followJpaRepository.findFollowerByUserIdAndFollowerId(userId, followerId, status)
                .map(FollowEntity::toModel);
    }



    @Override
    public Follow save(Follow follow) {
        return followJpaRepository.save(FollowEntity.from(follow)).toModel();
    }

    ///
    @Override
    public Optional<List<Follow>> findAllByFollowerIdAndStatus(Long followerId, FollowStatus status) {
        List<FollowEntity> followEntities = followJpaRepository.findAllByFollowerIdAndStatus(followerId, status)
                .orElse(Collections.emptyList());
        return Optional.of(followEntities.stream().map(FollowEntity::toModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Follow>> findAllByFollowingIdAndStatus(Long followingId, FollowStatus status) {
        List<FollowEntity> followEntities = followJpaRepository.findAllByFollowingIdAndStatus(followingId, status)
                .orElse(Collections.emptyList());
        return Optional.of(followEntities.stream().map(FollowEntity::toModel).collect(Collectors.toList()));
    }

    @Override
    public void delete(Follow follow) {
        followJpaRepository.delete(FollowEntity.from(follow));
    }


}
