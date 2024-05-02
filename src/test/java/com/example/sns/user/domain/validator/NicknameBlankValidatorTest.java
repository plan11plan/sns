package com.example.sns.user.domain.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.user.domain.validator.nickname.NicknameBlankValidator;
import com.example.sns.user.exception.nickname.InvalidLengthNickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameBlankValidatorTest {

    @DisplayName("닉네임이 빈값이 아니다.")
    @Test
    void validateNicknameLength() {
        //given
        var nicknameBlankValidator = new NicknameBlankValidator();
        nicknameBlankValidator.execute("123");
        //expected

    }


    @DisplayName("닉네임의 길이가 \"\" 면 예외를 반환한다.")
    @Test
    void validateNicknameLengthFail() {
        //given
        var nicknameBlankValidator = new NicknameBlankValidator();

        // expected
        assertThatExceptionOfType(InvalidLengthNickname.class)
                .isThrownBy(() -> nicknameBlankValidator.execute(""))
                .withMessageContaining("2글자 이상,20 글자 이하로 입력해주세요.");
    }

    @Test
    void d(){

    }

}