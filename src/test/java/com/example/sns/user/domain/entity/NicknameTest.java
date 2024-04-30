package com.example.sns.user.domain.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.user.exception.InvalidNicknameLength;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameTest {

    @DisplayName("[생성] 유효한 닉네임으로 객체를 성공적으로 생성한다.")
    @Test
    void createNicknameWithValidInput() {
        String inputNickname = "nickname";
        //when
        Nickname nickname = Nickname.builder().nickname(inputNickname).build();
        //then
        Assertions.assertThat(nickname.getNicknameToString()).isEqualTo("nickname");
    }

    @DisplayName("[생성 실패] 빈 문자열로 닉네임 생성 시 예외 발생")
    @Test
    void createNicknameWithEmptyString() {
        String inputNickname = "";
        // expected
        assertThatExceptionOfType(InvalidNicknameLength.class)
                .isThrownBy(() ->  Nickname.builder().nickname(inputNickname).build())
                .withMessageContaining("2글자 이상,20 글자 이하로 입력해주세요.");
    }

    @DisplayName("[생성 실패] 최소 길이 미만의 닉네임으로 생성 시 예외 발생")
    @Test
    void createNicknameWithLessThanMinLength() {
        String inputNickname = "n";
        // expected
        assertThatExceptionOfType(InvalidNicknameLength.class)
                .isThrownBy(() ->  Nickname.builder().nickname(inputNickname).build())
                .withMessageContaining("2글자 이상,20 글자 이하로 입력해주세요.");
    }

    @DisplayName("[생성 실패] 최대 길이 초과의 닉네임으로 생성 시 예외 발생")
    @Test
    void createNicknameWithMoreThanMaxLength() {
        String inputNickname = "aaaaaaaaaaaaaaaaaaaaa"; // 21 characters
        // expected
        assertThatExceptionOfType(InvalidNicknameLength.class)
                .isThrownBy(() ->  Nickname.builder().nickname(inputNickname).build())
                .withMessageContaining("2글자 이상,20 글자 이하로 입력해주세요.");
    }
    @DisplayName("[수정] 닉네임 수정")
    @Test
    void test(){
        Nickname dd = new Nickname("dd");
        dd.edit(new Nickname("change"));
        System.out.println(dd.getNicknameToString());
    }

}
