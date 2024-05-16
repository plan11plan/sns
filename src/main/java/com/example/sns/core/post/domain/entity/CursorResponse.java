package com.example.sns.core.post.domain.entity;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CursorResponse<T> {
    private final CursorRequest nextCursorRequest;
    private final List<T> body;


    @Builder
    public CursorResponse(CursorRequest nextCursorRequest, List<T> body) {
        this.nextCursorRequest = nextCursorRequest;
        this.body = body;
    }
}
