package com.example.sns.core.user.domain.service;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.common.service.port.UuidHolder;
import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.request.UserCreate;
import com.example.sns.core.user.domain.service.input.UserCreateInput;
import com.example.sns.core.user.domain.service.output.UserOutput;
import com.example.sns.core.user.domain.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Builder
@RequiredArgsConstructor
public class UserSignupService {
    private final UserRepository userRepository;
    private final CertificationService certificationService;
    private final PasswordEncoder passwordEncoder;
    private final TimeHolder timeHolder;
    private final UuidHolder uuidHolder;

    @Transactional
    public UserOutput signup(UserCreateInput userCreateInput) {
        UserCreate userCreate = UserCreate.builder()
                .email(Email.of(userCreateInput.getEmail()))
                .nickname(Nickname.of(userCreateInput.getNickname()))
                .password(Password.of(passwordEncoder.encode(userCreateInput.getPassword())))
                .birthday(Birthday.of(userCreateInput.getBirthDay()))
                .sex(Sex.valueOf(userCreateInput.getSex()))
                .createdAt(timeHolder.nowDateTime())
                .build();
        User user = User.from(userCreate, uuidHolder);

        user = userRepository.save(user);

        certificationService.send(user.getEmailValue(),user.getUserIdValue(), user.getCertificationCode());
        return UserOutput.from(user);
    }

}
