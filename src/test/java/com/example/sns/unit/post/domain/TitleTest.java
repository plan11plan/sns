package com.example.sns.unit.post.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.exception.InvalidLengthTitle;
import com.example.sns.core.post.exception.NoValueTitle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TitleTest {


    @DisplayName("[생성] 제목을 만든다.")
    @Test
    void createTitle() {
        Title title = new Title("title");
        Assertions.assertThat(title.getValue()).isEqualTo("title");
    }


    @DisplayName("[생성] 제목을 만들었을 떄 예외가 발생하지 않는다.")
    @Test
    void createTitle_No_Exception() {
        // Expected
        assertThatCode(() -> new Title("title"))
                .doesNotThrowAnyException();
    }

    @DisplayName("[생성 예외] 빈값이면 예외를 던진다.")
    @Test
    void createTitle_Exception1() {
        assertThatExceptionOfType(NoValueTitle.class)
                .isThrownBy(() -> new Title(""))
                .withMessage("제목을 입력해주세요");
    }

    @DisplayName("[생성 예외] 범위 밖이면 예외를 던진다.")
    @Test
    void createTitle_Exception() {
        assertThatExceptionOfType(InvalidLengthTitle.class)
                .isThrownBy(() -> new Title("1"))
                .withMessage("2글자 이상, 20글자 이하로 입력해주세요.");
    }
}