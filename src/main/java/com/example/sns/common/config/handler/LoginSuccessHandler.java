package com.example.sns.common.config.handler;

import com.example.sns.common.config.UserPrincipal;
import com.example.sns.core.user.domain.service.UserOnlineService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UserOnlineService userOnlineService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_OK);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        // 세션에 userId 저장
        request.getSession().setAttribute("userId", userPrincipal.getUserId());
        userOnlineService.toOnline(userPrincipal.getUserId());
//        response.sendRedirect("/");



    }
}
