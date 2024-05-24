package com.example.sns.mock;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.user.domain.entity.UserId;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.service.port.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeUserRepository implements UserRepository {
    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<User> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public User getById(Long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    @Override
    public Optional<User> findById(Long id) {
        return data.stream().filter(item -> item.getUserIdValue().equals(id)).findAny();
    }

    @Override
    public Optional<User> findByIdAndStatus(Long id, String userStatus) {
        return data.stream().filter(item -> item.getUserIdValue().equals(id) && item.getStatusValue() == userStatus).findAny();
    }

    @Override
    public Optional<User> findByEmailAndStatus(String email,String userStatus) {
        return data.stream().filter(item -> item.getEmail().equals(email) && item.getStatusValue() == userStatus).findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        if (user.getUserIdValue() == null || user.getUserIdValue() == 0) {
            User newUser = User.builder()
                    .id(new UserId(autoGeneratedId.incrementAndGet()))
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .nickname(user.getNickname())
                    .birthday(user.getBirthday())
                    .createdAt(user.getCreatedAt())
                    .sex(user.getSex())
                    .certificationCode(user.getCertificationCode())
                    .status(user.getStatus())
                    .lastLoginAt(user.getLastLoginAt())
                    .build();
            data.add(newUser);
            return newUser;
        } else {
            data.removeIf(item -> Objects.equals(item.getUserIdValue(), user.getUserIdValue()));
            data.add(user);
            return user;
        }
    }

    @Override
    public Optional<List<User>> findAllByIdIn(List<Long> ids) {
        return Optional.empty();
    }
}
