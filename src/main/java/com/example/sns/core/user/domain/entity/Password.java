package com.example.sns.core.user.domain.entity;


import com.example.sns.common.util.Pair;
import com.example.sns.core.user.domain.validator.PasswordValidatorFactory;
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
    private String value;

    @Builder
    public Password(String value) {
        PasswordValidatorFactory.blankValidator().validate(value);
//        PasswordValidatorFactory.lengthValidator().validate(value);
        this.value =value;
    }

    public static Password of(String value){
        return Password.builder()
                .value(value)
                .build();
    }
    public void editTo(Password to){
        PasswordValidatorFactory.duplicateCurrentPasswordValidator().validate(new Pair<>(this,to));
        this.value =to.getValue();

    }


    public String getValue() {
        return this.value;
    }
}
