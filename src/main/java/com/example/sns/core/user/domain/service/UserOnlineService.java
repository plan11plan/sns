package com.example.sns.core.user.domain.service;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.user.domain.entity.OnlineStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.service.port.UserRepository;
import com.example.sns.core.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOnlineService {
    private final UserRepository userRepository;
    private final TimeHolder timeHolder;

    @Transactional
    public void toOnline(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user = user.online(timeHolder.nowDateTime());
        userRepository.save(user);
    }

    @Transactional
    public void toOffline(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user = user.offline();
        userRepository.save(user);
    }

    @Transactional
    public void checkAndSetOfflineUsers() {
        List<User> onlineUsers = userRepository.findAllByOnlineStatus(OnlineStatus.ON.name())
                .orElseThrow(UserNotFoundException::new);
        for (User user : onlineUsers) {
            if (user.getLastActivityAt() == null || user.getLastActivityAt().isBefore(timeHolder.nowDateTime().minusMinutes(5))) {
                user = user.offline();
                userRepository.save(user);
            }
        }
    }
}
