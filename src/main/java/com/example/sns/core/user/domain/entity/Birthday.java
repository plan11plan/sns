package com.example.sns.core.user.domain.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Birthday {
    private final LocalDate value;

    // Use @JsonCreator to indicate that this constructor should be used when deserializing.
    @Builder
    @JsonCreator
    public Birthday(LocalDate value) {
        this.value = value;
    }
}