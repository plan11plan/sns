package com.example.sns.core.user.domain.entity.root;

import com.example.sns.common.exception.CertificationCodeNotMatchedException;
import com.example.sns.common.service.port.UuidHolder;
import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserId;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.vo.UserProfile;
import com.example.sns.core.user.domain.request.UserCreate;
import com.example.sns.core.user.domain.request.UserUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final UserId id;

    private final Email email;
    private final Password password;
    private final Nickname nickname;
    private final Sex sex;
    private final Birthday birthday;
    private final UserStatus status;

    private final LocalDateTime lastLoginAt;
    private final String certificationCode;
    private final LocalDateTime createdAt;

    @Builder
    public User(UserId id, Email email, Password password, Nickname nickname, Sex sex, Birthday birthday,
                UserStatus status, LocalDateTime lastLoginAt, String certificationCode, LocalDateTime createdAt) {
        this.id = id;
        ///
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.sex = sex;
        this.birthday = birthday;
        this.createdAt = createdAt;
        ///
        this.lastLoginAt = lastLoginAt;
        this.certificationCode = certificationCode;
    }

    public static User from(UserCreate userCreate, UuidHolder uuidHolder) {
        return User.builder()
                .certificationCode(uuidHolder.random())
                ///
                .email(userCreate.getEmail())
                .password(userCreate.getPassword())
                .nickname(userCreate.getNickname())
                .status(userCreate.getStatus())
                .sex(userCreate.getSex())
                .birthday(userCreate.getBirthday())
                .createdAt(userCreate.getCreatedAt())
                .build();
    }
    public void changeNickname(Nickname nickname){
        this.nickname.editTo(nickname);
    }


    public User update(UserUpdate userUpdate) {
        return User.builder()
                .email(userUpdate.getEmail())
                .password(userUpdate.getPassword())
                .nickname(userUpdate.getNickname())
                ///
                .id(id)
                .status(status)
                .sex(sex)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .certificationCode(certificationCode)
                .build();
    }

    public User login(LocalDateTime localDateTime) {
        return User.builder()
                .lastLoginAt(localDateTime)
                ///
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .status(status)
                .sex(sex)
                .birthday(birthday)
                .createdAt(createdAt)
                .certificationCode(certificationCode)
                .build();
    }

    public User certificate(String certificationCode) {
        if (!this.certificationCode.equals(certificationCode)) {
            throw new CertificationCodeNotMatchedException();
        }
        return User.builder()
                .certificationCode(certificationCode)
                .status(UserStatus.ACTIVE)
                ///
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .sex(sex)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .build();
    }

    public String getNicknameValue() {
        return nickname.getValue();
    }
    public UserProfile toUserProfile(){
        return UserProfile.builder()
                .userId(this.getUserIdValue())
                .nickname(this.getNicknameValue())
                .sex(this.sex)
                .build();
    }
    public Long getUserIdValue(){
        return this.id != null ? this.id.getValue() : null;

    }
    public String getPasswordValue(){
        return password.getValue();
    }
    public String getEmailValue(){
        return email.getValue();
    }
    public LocalDate getBirthdayValue(){
        return birthday.getValue();
    }
    public String getSexValue(){
        return sex.name();
    }
    public String getStatusValue(){
        return status.name();
    }
}
