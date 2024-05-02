package com.example.sns.user.domain.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.common.util.Pair;
import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.domain.validator.nickname.DuplicateNicknameValidator;
import com.example.sns.user.exception.nickname.DuplicateNickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DuplicateNicknameValidatorTest {

    @DisplayName("중복 아닐 경우 통과")
    @Test
    void success() {
        // given
        DuplicateNicknameValidator duplicateNicknameValidator = new DuplicateNicknameValidator();
        Nickname nickname = new Nickname("nickname");
        Nickname to = new Nickname("To");
        Pair<Nickname, Nickname> nicknameNicknamePair = new Pair<>(nickname, to);
        // expected
        duplicateNicknameValidator.validate(nicknameNicknamePair);

    }
    @DisplayName("중복인 경우 예외")
    @Test
    void fail() {
        //given
        DuplicateNicknameValidator duplicateNicknameValidator = new DuplicateNicknameValidator();
        Nickname nickname = new Nickname("same");
        Nickname to = new Nickname("same");
        Pair<Nickname, Nickname> nicknameNicknamePair = new Pair<>(nickname, to);

        // expected
        assertThatExceptionOfType(DuplicateNickname.class)
                .isThrownBy(() -> duplicateNicknameValidator.validate(nicknameNicknamePair))
                .withMessageContaining("다른 닉네임을 사용해주세요."); // Use the exact message you expect
    }
}
