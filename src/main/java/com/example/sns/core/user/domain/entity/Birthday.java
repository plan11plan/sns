package com.example.sns.core.user.domain.entity;


import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Birthday {
    LocalDate birthday;

    @Builder
    public Birthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getValue(){
        return this.birthday;
    }
}
