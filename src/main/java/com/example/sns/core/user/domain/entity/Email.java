package com.example.sns.core.user.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter

@Embeddable
public class Email {
    String value;

    @Builder
    public Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Email of(String value){
        return Email.builder()
                .value(value)
                .build();
    }
}
