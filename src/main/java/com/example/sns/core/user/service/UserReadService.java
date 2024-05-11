package com.example.sns.core.user.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.service.dto.UserDto;
import com.example.sns.core.user.service.port.UserRepository;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class UserReadService {
    private final UserRepository userRepository;

    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Users", email));
        return UserDto.from(user);
    }

    public UserDto getById(Long id) {
        User user = userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Users", id));
        return UserDto.from(user);
    }

    public UserDto getByIdAndStatus(long id) {
        User user = userRepository.findByIdAndStatus(id, UserStatus.ACTIVE).get();
        return UserDto.from(user);
    }
    //TODO : 예외 처리 해야함
    public List<UserDto> getUsers(List<Long> ids){
        List<User> users = userRepository.findAllByIdIn(ids)
                .orElseThrow(() -> new ResourceNotFoundException("Users", 1L));
        return UserDto.from(users);
    }
}
