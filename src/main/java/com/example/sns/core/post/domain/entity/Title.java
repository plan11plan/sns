package com.example.sns.core.post.domain.entity;


import com.example.sns.core.post.domain.validator.TitleValidatorFactory;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Title {
    public static final int LENGTH_MIN = 2;
    public static final int LENGTH_MAX = 20;
    private String title;

    public Title(String title) {
        TitleValidatorFactory.blankValidator().validate(title);
        TitleValidatorFactory.lengthValidator().validate(title);
        this.title = title;
    }

    public String getValue() {
        return this.title;
    }
}
