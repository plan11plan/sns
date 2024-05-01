package com.example.sns.user.domain.entity;

import com.example.sns.user.domain.validator.NicknameValidatorFactory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NicknameEditor {
    private final Nickname nickname;
    private final Nickname editTo;

    @Builder
    public NicknameEditor(Nickname nickname, Nickname editTo) {
        NicknameValidatorFactory.duplicateValidator().execute(editTo,nickname);
        this.nickname = nickname;
        this.editTo = editTo;
    }
    public String getEditNicknameToString(){
        return editTo.getNicknameToString();
    }
}
