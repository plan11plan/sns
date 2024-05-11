package com.example.sns.core.user.domain.validator.password;

import com.example.sns.core.common.util.Pair;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.validator.Validator;
import com.example.sns.core.user.exception.nickname.DuplicateNickname;
import com.example.sns.core.user.exception.password.DuplicateCurrentPassword;

public class DuplicateCurrentPasswordValidator implements Validator<Pair<Password, Password>> {
    @Override
    public void validate(Pair<Password, Password> passwords) throws DuplicateNickname {
        Password to = passwords.getFirst();
        Password password = passwords.getSecond();
        if (password.getValue().equals(to.getValue())) {
            throw new DuplicateCurrentPassword();
        }

    }
}