package com.example.sns.application.dto.post;

import com.example.sns.core.post.domain.entity.CursorRequest;
import lombok.Builder;
import lombok.Getter;

@Getter

public class GetTimelineByCursorCommand {

    private final Long userId;
    private final CursorRequest cursorRequest;

    @Builder
    public GetTimelineByCursorCommand(Long userId, CursorRequest cursorRequest) {
        this.userId = userId;
        this.cursorRequest = cursorRequest;
    }
    public static GetTimelineByCursorCommand of(Long userId, CursorRequest request){
        return GetTimelineByCursorCommand.builder()
                .userId(userId)
                .cursorRequest(request)
                .build();
    }

}
