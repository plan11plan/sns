package com.example.sns.core.user.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.NicknameHistory;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.service.port.NicknameHistoryRepository;
import com.example.sns.core.user.service.port.UserRepository;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final NicknameHistoryRepository nicknameHistoryRepository;
    private final TimeHolder timeHolder;

    @Transactional
    public void login(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        user = user.login(LocalDateTime.now());
        userRepository.save(user);

    }

    @Transactional
    public void verifyEmail(long id, String certificationCode) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        user = user.certificate(certificationCode);
        NicknameHistory nicknameHistory = NicknameHistory.builder()
                .userId(user.getUserIdValue())
                .nickname(user.getNickname())
                .createdAt(timeHolder.nowDateTime())
                .build();
        nicknameHistoryRepository.save(nicknameHistory);
        userRepository.save(user);
    }
}
