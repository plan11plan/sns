package com.example.sns.user.domain.validator.nickname;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.user.exception.nickname.InvalidLengthNickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameBlankValidatorTest {

    @DisplayName("닉네임이 비어있지 않을 때 예외를 발생시키지 않는다.")
    @Test
    void validateNicknameIsNotEmpty() {
        //given
        var nicknameBlankValidator = new NicknameBlankValidator();

        //expected
        assertThatCode(() -> nicknameBlankValidator.validate("123"))
                .doesNotThrowAnyException();
    }

    @DisplayName("닉네임이 빈 문자열일 때 예외를 발생시킨다.")
    @Test
    void validateNicknameIsEmpty() {
        //given
        var nicknameBlankValidator = new NicknameBlankValidator();

        // expected
        assertThatExceptionOfType(InvalidLengthNickname.class)
                .isThrownBy(() -> nicknameBlankValidator.validate(""))
                .withMessage("2글자 이상, 20글자 이하로 입력해주세요.");
    }

}