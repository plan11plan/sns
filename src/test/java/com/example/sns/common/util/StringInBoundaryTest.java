package com.example.sns.common.util;

import static com.example.sns.core.common.util.StringInBoundary.isInBoundary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringInBoundaryTest {

    @DisplayName("문자의 경계[Follower]  밖이면 false를 반환한다.")
    @Test
    void outOfBoundaryLessThenMin(){
        String text ="333";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @DisplayName("문자의 경계[Follower]  밖이면 false를 반환한다.")
    @Test
    void outOfBoundaryOverThenMax(){
        String text ="7777777";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @DisplayName("문자의 경계[Follower]  안이면 true를 반환한다.")
    @Test
    void inBoundaryByMin(){
        String text ="4444";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(true);
    }
    @DisplayName("문자의 경계[Follower]  안이면 true를 반환한다.")
    @Test
    void inBoundaryOverByMax(){
        String text ="666666";
        Long min = 4L;
        Long max = 6L;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @DisplayName("문자의 경계[int]  밖이면 false를 반환한다.")
    @Test
    void outOfBoundaryLessThenMinByInt(){
        String text ="333";
        int min = 4;
        int  max = 6;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @DisplayName("문자의 경계[int] 밖이면 false를 반환한다.")
    @Test
    void outOfBoundaryOverThenMaxByInt(){
        String text ="7777777";
        int min = 4;
        int max = 6;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @DisplayName("문자의 경계[int] 안이면 true를 반환한다.")
    @Test
    void inBoundaryByMinByInt(){
        String text ="4444";
        int min = 4;
        int max = 6;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(true);
    }
    @DisplayName("문자의 경계[int] 안이면 true를 반환한다.")
    @Test
    void inBoundaryOverByMaxByInt(){
        String text ="666666";
        int min = 4;
        int max = 6;
        boolean result = isInBoundary(text, min, max);
        Assertions.assertThat(result).isEqualTo(true);
    }
}