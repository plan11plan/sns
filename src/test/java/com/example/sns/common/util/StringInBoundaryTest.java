package com.example.sns.common.util;

import static com.example.sns.common.util.StringInBoundary.isInBoundary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringInBoundaryTest {

    @DisplayName("문자의 경계 밖이면 false를 반환한다.")
    @Test
    void outOfBoundaryLessThenMin(){
        String text ="333";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @DisplayName("문자의 경계 밖이면 false를 반환한다.")
    @Test
    void outOfBoundaryOverThenMax(){
        String text ="7777777";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @DisplayName("문자의 경계 안이면 true를 반환한다.")
    @Test
    void inBoundaryByMin(){
        String text ="4444";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(true);
    }
    @DisplayName("문자의 경계 안이면 true를 반환한다.")
    @Test
    void inBoundaryOverByMax(){
        String text ="666666";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(true);
    }
}