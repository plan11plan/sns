package com.example.sns.core.post.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WriterId {
    long writerId;

    public long getValue(){
        return this.writerId;
    }

    @Builder
    public WriterId(long writerId) {
        this.writerId = writerId;
    }
}
