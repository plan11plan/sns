package com.example.sns.core.user.infrastructure.persistence;

import com.example.sns.core.user.domain.entity.OnlineStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.infrastructure.persistence.entity.UserEntity;
import com.example.sns.core.user.domain.service.port.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(Long  id) {
        return userJpaRepository.findById(id).map(UserEntity::toModel);
    }

    @Override
    public Optional<User> findByIdAndStatus(Long  id, String status) {
        return userJpaRepository.findByIdAndStatus(id,status).map(UserEntity::toModel);
    }

    @Override
    public Optional<User> findByEmailAndStatus(String email, String status) {
        return userJpaRepository.findByEmailAndStatus(email,status).map(UserEntity::toModel);
    }



    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }

    @Override
    public Optional<List<User>> findAllByIdIn(List<Long> ids) {
        List<UserEntity> userEntities = userJpaRepository.findAllByIdIn(ids).orElse(Collections.emptyList());
       return Optional.of(userEntities.stream().map(UserEntity::toModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<User>> findAllByOnlineStatus(String onlineStatus) {
        List<UserEntity> userEntities = userJpaRepository.findAllByOnlineStatus(onlineStatus)
                .orElse(Collections.emptyList());
        return Optional.of(userEntities.stream().map(UserEntity::toModel).collect(Collectors.toList()));
    }

}
