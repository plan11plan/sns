package com.example.sns.core.user.service;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.NicknameHistory;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserUpdate;
import com.example.sns.core.user.service.dto.UserDto;
import com.example.sns.core.user.service.port.NicknameHistoryRepository;
import com.example.sns.core.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Builder
@RequiredArgsConstructor
public class UserUpdateService {
    private final UserRepository userRepository;
    private final NicknameHistoryRepository nicknameHistoryRepository;
    private final TimeHolder timeHolder;

    @Transactional
    public UserDto update(Long id, UserUpdate userUpdate) {
        User user = userRepository.getById(id);
        user = user.update(userUpdate);
        userRepository.save(user);
        return UserDto.from(user);
    }
    @Transactional
    public void changeNickname(Long userId, Nickname nickname){
        User user = userRepository.getById(userId);
        user.changeNickname(nickname);

        NicknameHistory nicknameHistory = NicknameHistory.builder()
                .userId(userId)
                .nickname(nickname)
                .createdAt(timeHolder.nowDateTime())
                .build();
        nicknameHistoryRepository.save(nicknameHistory);
    }
}
