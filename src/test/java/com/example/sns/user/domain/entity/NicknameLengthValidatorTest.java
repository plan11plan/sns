package com.example.sns.user.domain.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.user.domain.validator.NicknameLengthValidator;
import com.example.sns.user.exception.InvalidNicknameLength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameLengthValidatorTest {

    @DisplayName("닉네임의 길이를 만족한다.")
    @Test
    void validateNicknameLength() {
        //given
        NicknameLengthValidator nicknameLengthValidator = new NicknameLengthValidator();
        nicknameLengthValidator.execute("4444", 1, 4);
        //expected

    }

    @DisplayName("닉네임의 길이 만족을 못해 예외를 반환한다.")
    @Test
    void validateNicknameLengthFail() {
        //given
        NicknameLengthValidator nicknameLengthValidator = new NicknameLengthValidator();

        // expected
        assertThatExceptionOfType(InvalidNicknameLength.class)
                .isThrownBy(() -> nicknameLengthValidator.execute("4444", 5, 10))
                .withMessageContaining("2글자 이상,20 글자 이하로 입력해주세요."); // Use the exact message you expect
    }


}