package com.example.sns.user.domain.entity;

import com.example.sns.user.domain.validator.NicknameBlankValidator;
import com.example.sns.user.domain.validator.NicknameLengthValidator;
import com.example.sns.user.exception.DuplicateNickname;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [생성,변경] 닉네임은 최소 2글자 ~ 20글자 이하로 생성된다. [생성,변경] 다른 닉네임들과 같을 수 없다. [변경]닉네임을 변경할 수 있다.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class Nickname {
    public static final int LENGTH_MIN = 2;
    public static final int LENGTH_MAX = 20;
    private String nickname;
    private NicknameLengthValidator nicknameLengthValidator = new NicknameLengthValidator();
    private NicknameBlankValidator blankValidator = new NicknameBlankValidator();


    @Builder
    public Nickname(String nickname) {
        blankValidator.execute(nickname);
        nicknameLengthValidator.execute(nickname, LENGTH_MIN, LENGTH_MAX);
        this.nickname = nickname;
    }

    public void edit(Nickname to){
        if(this.nickname.equals(to.getNicknameToString())){
            throw new DuplicateNickname();
        }
        else {
            this.nickname = to.getNicknameToString();
        }

    }


    public String getNicknameToString() {
        return this.nickname;
    }
}
