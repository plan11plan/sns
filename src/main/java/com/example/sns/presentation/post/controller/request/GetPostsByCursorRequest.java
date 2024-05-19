package com.example.sns.presentation.post.controller.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GetPostsByCursorRequest {


    @Min(1)
    private int size = 10;

    private Long key;


    @Builder
    public GetPostsByCursorRequest(int size, Long key) {
        this.size = size;
        this.key = key;
    }
}