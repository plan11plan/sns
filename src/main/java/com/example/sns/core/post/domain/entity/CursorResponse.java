package com.example.sns.core.post.domain.entity;

import java.util.List;
import lombok.Getter;

@Getter
public class CursorResponse<T> {
    private final CursorRequest nextCursorRequest;
    private final List<T> data;

    public CursorResponse(CursorRequest nextCursorRequest, List<T> data) {
        this.nextCursorRequest = nextCursorRequest;
        this.data = data;
    }
}