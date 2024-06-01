package com.example.sns.core.user.domain.service;

import static com.example.sns.core.user.domain.entity.UserStatus.ACTIVE;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.service.port.UserRepository;
import com.example.sns.core.user.exception.UserNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserReadService userService;
    private final UserOnlineService userOnlineService;
    private final TimeHolder timeHolder;

    @Transactional
    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmailAndStatus(email, ACTIVE.name()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(rawPassword, user.getPasswordValue())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        user = user.login(timeHolder.nowDateTime());
        userOnlineService.toOnline(user.getUserIdValue());
        return user;
    }

    @Transactional
    public void logout(Long userId) {
        userOnlineService.toOffline(userId);
    }
    @Transactional
    public void updateLastLoginAt(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user = user.login(LocalDateTime.now());
        userRepository.save(user);
    }
}