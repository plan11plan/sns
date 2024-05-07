package com.example.sns.core.user.service;

import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserUpdate;
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

    @Transactional
    public User update(long id, UserUpdate userUpdate) {
        User user = userRepository.getById(id);
        user = user.update(userUpdate);
        userRepository.save(user);
        return user;
    }
}
