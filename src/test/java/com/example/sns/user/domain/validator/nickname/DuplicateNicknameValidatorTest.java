package com.example.sns.user.domain.validator.nickname;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.common.util.Pair;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.validator.nickname.DuplicateNicknameValidator;
import com.example.sns.core.user.exception.nickname.DuplicateNickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DuplicateNicknameValidatorTest {

    @DisplayName("중복이 아닐 경우 통과")
    @Test
    void success() {
        // given
        DuplicateNicknameValidator duplicateNicknameValidator = new DuplicateNicknameValidator();
        Nickname nickname = new Nickname("nickname");
        Nickname to = new Nickname("To");
        Pair<Nickname, Nickname> nicknamePair = new Pair<>(nickname, to);

        // expected
        assertThatCode(() -> duplicateNicknameValidator.validate(nicknamePair))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복인 경우 예외 발생")
    @Test
    void fail() {
        // given
        DuplicateNicknameValidator duplicateNicknameValidator = new DuplicateNicknameValidator();
        Nickname nickname = new Nickname("same");
        Nickname to = new Nickname("same");
        Pair<Nickname, Nickname> nicknamePair = new Pair<>(nickname, to);

        // expected
        assertThatExceptionOfType(DuplicateNickname.class)
                .isThrownBy(() -> duplicateNicknameValidator.validate(nicknamePair))
                .withMessageContaining("다른 닉네임을 사용해주세요.");

    }
}
