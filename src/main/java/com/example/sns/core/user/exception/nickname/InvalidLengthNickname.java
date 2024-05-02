package com.example.sns.core.user.exception.nickname;

import com.example.sns.common.exception.RootException;

/**
 * status : 400
 */
public class InvalidLengthNickname extends RootException {
    // 동적 메시지 생성을 위해 기본 메시지 패턴 수정
    public InvalidLengthNickname(int min, int max) {
        super(min + "글자 이상, " + max + "글자 이하로 입력해주세요.");
    }

    @Override
    public String getStatusCode() {
        return "400"; // Bad Request 상태 코드
    }
}
