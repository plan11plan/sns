package com.example.sns.core.user.interceptor;

import com.example.sns.core.user.domain.service.UserOnlineService;
import com.example.sns.core.user.domain.service.UserWebSocketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class UserActivityInterceptor implements HandlerInterceptor {
    private final UserOnlineService userOnlineService;
    private final UserWebSocketService userWebSocketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("userId");
        System.out.println("아이디를" + userId);
        if (userId != null) {
            userOnlineService.toOnline(userId);
            userWebSocketService.notifyUserStatusChange(userId, "online");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId != null) {
            userOnlineService.toOffline(userId);
            userWebSocketService.notifyUserStatusChange(userId, "offline");
        }
    }
}
