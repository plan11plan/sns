package com.example.sns.user.domain.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.user.exception.password.InvalidLengthPassword;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {

    @DisplayName("[생성] 비밀번호를 생성한다.")
    @Test
    void create_success() {
        // given
        Password password = new Password("password");
        Password passwordByBuilder = Password.builder()
                .input("password")
                .build();

        // expected
        Assertions.assertThat(password.getNowValue()).isEqualTo("password");
        Assertions.assertThat(passwordByBuilder.getNowValue()).isEqualTo("password");
    }

    @DisplayName("[생성 에러] 4~20글자가 아니면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"aaa", "aaaaaaaaaaaaaaaaaaaaa"}) // 3글자와 21글자 입력
    void create_length_fail(String input) {

        // expected
        assertThatExceptionOfType(InvalidLengthPassword.class)
                .isThrownBy(() -> new Password(input))
                .withMessageContaining("4글자 이상,20 글자 이하로 입력해주세요.");
    }


    @DisplayName("[생성 에러] 빈 값이면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {""}) // 3글자와 21글자 입력
    void create_blank_fail(String input) {
        // expected
        assertThatExceptionOfType(InvalidLengthPassword.class)
                .isThrownBy(() -> new Password(input))
                .withMessageContaining("4글자 이상,20 글자 이하로 입력해주세요.");
    }
}