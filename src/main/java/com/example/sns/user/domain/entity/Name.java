package com.example.sns.user.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Name {
    String name;

    public String getNameToString() {
        return name;
    }

}
