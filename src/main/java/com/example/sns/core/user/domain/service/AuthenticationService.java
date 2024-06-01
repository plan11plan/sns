package com.example.sns.core.user.domain.service;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.NicknameHistory;
import com.example.sns.core.user.domain.entity.UserId;
import com.example.sns.core.user.domain.entity.request.NicknameHistoryCreate;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.service.port.NicknameHistoryRepository;
import com.example.sns.core.user.exception.UserNotFoundException;
import com.example.sns.core.user.domain.service.port.UserRepository;
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
    public void login(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user = user.login(LocalDateTime.now());
        userRepository.save(user);

    }

    @Transactional
    public void verifyEmail(Long id, String certificationCode) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user = user.certificate(certificationCode);
        user = userRepository.save(user);
        var nicknameHistoryCreate = getCreate(user);
        NicknameHistory nicknameHistory = NicknameHistory.from(nicknameHistoryCreate, user.getCreatedAt());
        nicknameHistoryRepository.save(nicknameHistory);
        userRepository.save(user);
    }

    private NicknameHistoryCreate getCreate(User user) {
        return NicknameHistoryCreate.builder()
                .userId(UserId.of(user.getUserIdValue()))
                .nickname(Nickname.of(user.getNicknameValue()))
                .build();
    }
}
