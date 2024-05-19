package com.example.sns.core.post.service.dto;

import com.example.sns.core.post.domain.entity.CursorRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostGeyByCursor {
    private final Long writerId;
    private final String status;
    private final CursorRequest cursorRequest;


    @Builder
    public PostGeyByCursor(Long writerId, String status, CursorRequest cursorRequest) {
        this.writerId = writerId;
        this.status = status;
        this.cursorRequest = cursorRequest;
    }
}
