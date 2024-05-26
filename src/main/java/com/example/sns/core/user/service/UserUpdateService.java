package com.example.sns.core.user.service;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserUpdate;
import com.example.sns.core.user.exception.UserNotFoundException;
import com.example.sns.core.user.service.output.UserOutput;
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
    public UserOutput update(Long id, UserUpdate userUpdate) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user = user.update(userUpdate);
        userRepository.save(user);
        return UserOutput.from(user);
    }
}
