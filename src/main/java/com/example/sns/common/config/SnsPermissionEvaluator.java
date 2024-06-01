package com.example.sns.common.config;

import com.example.sns.core.post.exception.PostNotFoundException;
import com.example.sns.core.post.domain.service.port.PostReadRepository;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

@Slf4j
@RequiredArgsConstructor
public class SnsPermissionEvaluator implements PermissionEvaluator {

    private final PostReadRepository postReadRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        var userPrincipal = (UserPrincipal) authentication.getPrincipal();

        var post = postReadRepository.findById((Long) targetId)
                .orElseThrow(PostNotFoundException::new);

        if (!post.getPostIdValue().equals(userPrincipal.getUserId())) {
            log.error("[인가실패] 해당 사용자가 작성한 글이 아닙니다. targetId={}", targetId);
            return false;
        }

        return true;
    }
}
