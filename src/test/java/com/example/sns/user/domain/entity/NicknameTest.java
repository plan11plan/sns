package com.example.sns.user.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameTest {

    @DisplayName("[생성] 닉네임을 만든다.")
    @Test
    void createNickname(){
        String inputNickname = "nickname";
        //when
        Nickname nickname = Nickname.builder().nickname(inputNickname).build();
        //then
        Assertions.assertThat(nickname.getNicknameToString()).isEqualTo("nickname");
    }

    @DisplayName("[생성] 빈값을 받을 수 없다.")
    @Test
    void createNickname4(){
        String INPUT_NICKNAME = "";
        //when
        Nickname nickname = Nickname.builder().nickname(INPUT_NICKNAME).build();
        //then
        Assertions.assertThat(nickname.getNicknameToString()).isEqualTo("nickname");
    }


    @DisplayName("[생성] 최소길이 미만으로 생성 불가")
    @Test
    void createNickname2(){
        String INPUT_NICKNAME = "n";
        //when
        Nickname nickname = Nickname.builder().nickname(INPUT_NICKNAME).build();
        //then
        Assertions.assertThat(nickname.getNicknameToString()).isEqualTo("n");
    }


    @DisplayName("[생성] 최대길이 넘게 생성 불가")
    @Test
    void createNickname3(){
        String INPUT_NICKNAME = "aaaaaaaaaaaaaaaaaaaaa";
        //when
        Nickname nickname = Nickname.builder().nickname(INPUT_NICKNAME).build();
        //then
        Assertions.assertThat(nickname.getNicknameToString()).isEqualTo("n");
    }
}