package com.example.sns.core.user.domain.repository;

import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.infrastructure.UserJpaRepository;
import com.example.sns.core.user.service.port.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;


    @Override
    public Optional<User> findByIdAndStatus(long id, UserStatus userStatus) {
        return userJpaRepository.findByIdAndUserStatus(id,userStatus);
    }

    @Override
    public Optional<User> findByEmailAndStatus(String email, UserStatus userStatus) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
