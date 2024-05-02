package com.example.sns.user.domain.entity;


import static com.example.sns.user.domain.validator.PasswordValidatorFactory.blankValidator;
import static com.example.sns.user.domain.validator.PasswordValidatorFactory.duplicateCurrentPasswordValidator;
import static com.example.sns.user.domain.validator.PasswordValidatorFactory.lengthValidator;

import com.example.sns.common.util.Pair;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [생성] - 비밀번호를 생성한다. 최소 4글자~20글자 [수정] - 이전 비밀번호와 같은 비밀번호로 변경할 수 없다.
 */
@Getter
@Embeddable
@NoArgsConstructor
public class Password {
    public static final int LENGTH_MIN = 4;
    public static final int LENGTH_MAX = 20;
    private String password;

    @Builder
    public Password(String input) {
        blankValidator().validate(input);
        lengthValidator().validate(input);
        this.password =input;
    }


    public void editTo(Password to){
        duplicateCurrentPasswordValidator().validate(new Pair<>(this,to));
        this.password=to.getValue();

    }


    public String getValue() {
        return this.password;
    }
}
