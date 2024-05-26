package com.example.sns.core.user.service;

import com.example.sns.core.user.domain.entity.NicknameHistory;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.exception.NicknameHistoryNotFoundException;
import com.example.sns.core.user.exception.UserNotFoundException;
import com.example.sns.core.user.service.output.NicknameHistoryOutput;
import com.example.sns.core.user.service.output.UserOutput;
import com.example.sns.core.user.service.output.UserProfileOutput;
import com.example.sns.core.user.service.output.UserProfilesOutput;
import com.example.sns.core.user.service.port.NicknameHistoryRepository;
import com.example.sns.core.user.service.port.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class UserReadService {
    private final UserRepository userRepository;
    private final NicknameHistoryRepository nicknameHistoryRepository;
    private final String USER_STATUS_ACTIVE = "ACTIVE";

    public UserProfilesOutput getUserProfiles(List<Long> userIds) {
        List<User> users = userRepository.findAllByIdIn(userIds).get();
        List<UserProfileOutput> userProfileOutputs = users.stream()
                .map(user -> UserProfileOutput.builder()
                        .userId(user.getUserIdValue())
                        .nickname(user.getNickname().getValue())
                        .sex(user.getSex().name())
                        .build())
                .collect(Collectors.toList());
        return new UserProfilesOutput(userProfileOutputs);
    }

    public UserProfileOutput getUserProfile(Long userId) {
        User user = userRepository.findByIdAndStatus(userId, USER_STATUS_ACTIVE)
                .orElseThrow(UserNotFoundException::new);
        return UserProfileOutput.builder()
                .userId(user.getUserIdValue())
                .nickname(user.getNickname().getValue())
                .sex(user.getSex().name())
                .build();
    }

    public UserOutput getByEmail(String email) {
        User user = userRepository.findByEmailAndStatus(email, USER_STATUS_ACTIVE)
                .orElseThrow(UserNotFoundException::new);
        return UserOutput.from(user);
    }

    public UserOutput getById(Long id) {
        User user = userRepository.findByIdAndStatus(id, USER_STATUS_ACTIVE)
                .orElseThrow(UserNotFoundException::new);
        return UserOutput.from(user);
    }

    public UserOutput getByIdAndStatus(long id) {
        User user = userRepository.findByIdAndStatus(id, USER_STATUS_ACTIVE).get();
        return UserOutput.from(user);
    }

    public List<UserOutput> getUsers(List<Long> ids) {
        List<User> users = userRepository.findAllByIdIn(ids)
                .orElseThrow(UserNotFoundException::new);
        return UserOutput.from(users);
    }

    public List<NicknameHistoryOutput> getNicknameHistories(Long userId) {
        List<NicknameHistory> nicknameHistory = nicknameHistoryRepository.findAllByUserId(userId)
                .orElseThrow(NicknameHistoryNotFoundException::new);
        return nicknameHistory.stream()
                .map(NicknameHistoryOutput::from)
                .collect(Collectors.toList());
    }

    public void ensureWriterExists(Long writerId) {
        userRepository.findByIdAndStatus(writerId, USER_STATUS_ACTIVE)
                .orElseThrow(UserNotFoundException::new);
    }
}
