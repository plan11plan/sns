package com.example.sns.core.user.service;

import com.example.sns.common.service.port.UuidHolder;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserCreate;
import com.example.sns.core.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Builder
@RequiredArgsConstructor
public class UserCreateService {
    private final UserRepository userRepository;
    private final CertificationService certificationService;
    private final UuidHolder uuidHolder;

    @Transactional
    public User create(UserCreate userCreate) {
        User user = User.from(userCreate, uuidHolder);
        user = userRepository.save(user);
        certificationService.send(userCreate.getEmail().getValue(),user.getId(), user.getCertificationCode());
        return user;
    }

}
