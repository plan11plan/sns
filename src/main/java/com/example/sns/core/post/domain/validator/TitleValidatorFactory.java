package com.example.sns.core.post.domain.validator;

import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.validator.title.TitleBlankValidator;
import com.example.sns.core.post.domain.validator.title.TitleLengthValidator;
import com.example.sns.core.user.domain.validator.Validator;

public class TitleValidatorFactory {
    private TitleValidatorFactory() {
    }

    public static Validator<String> lengthValidator() {
        return new TitleLengthValidator(Title.LENGTH_MIN, Title.LENGTH_MAX);
    }

    public static Validator<String> blankValidator() {
        return new TitleBlankValidator();
    }

}