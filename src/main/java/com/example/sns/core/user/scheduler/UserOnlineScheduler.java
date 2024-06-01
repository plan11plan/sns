package com.example.sns.core.user.scheduler;


import com.example.sns.core.user.domain.service.UserOnlineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserOnlineScheduler {
    private final UserOnlineService userOnlineService;

    @Scheduled(fixedRate = 10000) // 1분마다 실행
    public void updateUsersOfflineStatus() {
        userOnlineService.checkAndSetOfflineUsers();
    }
}
