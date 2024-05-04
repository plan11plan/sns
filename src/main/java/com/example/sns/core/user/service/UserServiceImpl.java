package com.example.sns.core.user.service;

import com.example.sns.core.user.controller.port.UserService;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
       return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE).get();
    }
}
