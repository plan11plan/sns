package com.example.sns.core.post.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TimelineId {
    private final Long value;

    public Long getValue(){
        return this.value;
    }

    public static TimelineId of(Long value){
        return TimelineId.builder()
                .value(value)
                .build();
    }
    @Builder
    public TimelineId(Long value) {
        this.value = value;
    }
}
