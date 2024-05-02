package com.example.sns.user.domain.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.domain.validator.nickname.NicknameLengthValidator;
import com.example.sns.user.exception.nickname.InvalidLengthNickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameLengthValidatorTest {

    @DisplayName("닉네임의 길이를 만족한다.")
    @Test
    void validateNicknameLength() {
        //given
        NicknameLengthValidator nicknameLengthValidator = new NicknameLengthValidator(Nickname.LENGTH_MIN,Nickname.LENGTH_MAX);
        nicknameLengthValidator.validate("4444");
        //expected

    }

    @DisplayName("닉네임의 길이 만족을 못해 예외를 반환한다.")
    @Test
    void validateNicknameLengthFail() {
        //given
        NicknameLengthValidator nicknameLengthValidator = new NicknameLengthValidator(Nickname.LENGTH_MIN,Nickname.LENGTH_MAX);

        // expected
        assertThatExceptionOfType(InvalidLengthNickname.class)
                .isThrownBy(() -> nicknameLengthValidator.validate("1"))
                .withMessageContaining("2글자 이상, 20글자 이하로 입력해주세요."); // Use the exact message you expect
    }


}