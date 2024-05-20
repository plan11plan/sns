package com.example.sns.core.user.service.output;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class UserProfilesOutput {
    private final Map<Long, UserProfileOutput> userProfiles;

    public UserProfilesOutput(List<UserProfileOutput> userProfileList) {
        this.userProfiles = userProfileList.stream()
                .collect(Collectors.toMap(UserProfileOutput::getUserId, userProfile -> userProfile));
    }

    public UserProfileOutput getUserProfile(Long userId) {
        return userProfiles.get(userId);
    }

    public boolean containsUser(Long userId) {
        return userProfiles.containsKey(userId);
    }
}