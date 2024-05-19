package com.example.sns.presentation.post.controller.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetTimelineByCursorRequest {


    @Min(1)
    private int size = 10;

    private Long key;


    @Builder

    public GetTimelineByCursorRequest(int size, Long key) {
        this.size = size;
        this.key = key;

    }
}
