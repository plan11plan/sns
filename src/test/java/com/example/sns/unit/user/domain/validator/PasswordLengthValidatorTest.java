package com.example.sns.unit.user.domain.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.example.sns.core.user.domain.validator.password.PasswordLengthValidator;
import com.example.sns.core.user.exception.password.InvalidLengthPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PasswordLengthValidatorTest {

    private PasswordLengthValidator validator;

    @BeforeEach
    void setUp() {
        int min = 4;  // 최소 길이
        int max = 20; // 최대 길이
        validator = PasswordLengthValidator.builder().
                min(min)
                .max(max).build();}

    @Test
    @DisplayName("유효한 비밀번호 길이는 예외를 발생시키지 않아야 한다")
    void validate_ValidLengthPassword_DoesNotThrowException() {
        String validPassword = "ValidPass123";
        assertThatCode(() -> validator.validate(validPassword)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("길이가 너무 짧은 비밀번호는 InvalidLengthPassword 예외를 발생시켜야 한다")
    void validate_TooShortPassword_ThrowsException() {
        String shortPassword = "sho";
        assertThatThrownBy(() -> validator.validate(shortPassword))
                .isInstanceOf(InvalidLengthPassword.class)
                .hasMessageContaining("4글자 이상, 20글자 이하로 입력해주세요");
    }

    @Test
    @DisplayName("길이가 너무 긴 비밀번호는 InvalidLengthPassword 예외를 발생시켜야 한다")
    void validate_TooLongPassword_ThrowsException() {
        String longPassword = "ThisIsAVeryLongPasswordThatExceedsLimits";
        assertThatThrownBy(() -> validator.validate(longPassword))
                .isInstanceOf(InvalidLengthPassword.class)
                .hasMessageContaining("4글자 이상, 20글자 이하로 입력해주세요");
    }
}