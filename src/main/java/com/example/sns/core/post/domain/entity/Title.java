package com.example.sns.core.post.domain.entity;


import com.example.sns.core.post.domain.validator.TitleValidatorFactory;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Title {
    public static final int LENGTH_MIN = 2;
    public static final int LENGTH_MAX = 20;
    private String value;

    @Builder
    public Title(String value) {
        TitleValidatorFactory.blankValidator().validate(value);
        TitleValidatorFactory.lengthValidator().validate(value);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Title of(String value){
        return Title.builder()
                .value(value)
                .build();
    }
}
