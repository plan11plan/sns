package com.example.sns.user.domain.entity;

import static com.example.sns.user.domain.validator.NicknameValidatorFactory.duplicateValidator;

import com.example.sns.common.util.Pair;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameEditor {
    private final Nickname nickname;
    private final Nickname editTo;

    @Builder
    public NicknameEditor(Nickname nickname, Nickname editTo) {
        duplicateValidator().validate(new Pair<>(nickname,editTo));
        this.nickname = nickname;
        this.editTo = editTo;
    }
    public String getEditNicknameToString(){
        return editTo.getNicknameToString();
    }
}
