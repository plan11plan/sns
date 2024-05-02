package com.example.sns.user.domain.entity;


import com.example.sns.user.domain.validator.password.BlankPasswordValidator;
import com.example.sns.user.domain.validator.password.PasswordLengthValidator;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [생성] - 비밀번호를 생성한다. 최소 4글자~20글자
 * [수정] - 이전 비밀번호와 같은 비밀번호로 변경할 수 없다.
 */
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Password {
    public static final int LENGTH_MIN = 4;
    public static final int LENGTH_MAX = 20;
    private String nowPassword;
    private String oldPassword;

    @Builder
    public Password(String input) {
        BlankPasswordValidator.execute(input);
        PasswordLengthValidator.execute(input,LENGTH_MIN,LENGTH_MAX);
        this.nowPassword = input;
        this.oldPassword = input;
    }
    private void verifyChangePassword(Password password) {
        if(isSameWithBefore(password.getOldPassword())){
            throw new IllegalArgumentException();
        }
        if (isSameWithCurrent(password.getNowPassword())) {
            throw new IllegalArgumentException();
        }
    }


    boolean isSameWithCurrent(String input) {
        return nowPassword.equals(input);
    }

    boolean isSameWithBefore(String input) {
        return oldPassword.equals(input);
    }

    public String getNowValue() {
        return nowPassword;
    }
}
