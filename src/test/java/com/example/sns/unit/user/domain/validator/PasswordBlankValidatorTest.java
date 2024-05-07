package com.example.sns.unit.user.domain.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.core.user.domain.validator.password.PasswordBlankValidator;
import com.example.sns.core.user.exception.password.InvalidLengthPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PasswordBlankValidatorTest {
    @DisplayName("닉네임이 비어있지 않을 때 예외를 발생시키지 않는다.")
    @Test
    void validatePasswordIsNotEmpty() {
        //given
        var passwordBlankValidator = new PasswordBlankValidator();

        //expected
        assertThatCode(() -> passwordBlankValidator.validate("123"))
                .doesNotThrowAnyException();
    }

    @DisplayName("닉네임이 빈 문자열일 때 예외를 발생시킨다.")
    @Test
    void validatePasswordIsEmpty() {
        //given
        var passwordBlankValidator = new PasswordBlankValidator();

        // expected
        assertThatExceptionOfType(InvalidLengthPassword.class)
                .isThrownBy(() -> passwordBlankValidator.validate(""))
                .withMessage("4글자 이상, 20글자 이하로 입력해주세요.");
    }

}