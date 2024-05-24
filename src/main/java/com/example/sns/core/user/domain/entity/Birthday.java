package com.example.sns.core.user.domain.entity;


import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Birthday {
    private final LocalDate value;

    @Builder
    public Birthday(LocalDate value) {
        this.value = value;
    }
    public static Birthday of(LocalDate value){
        return Birthday.builder()
                .value(value)
                .build();
    }
}