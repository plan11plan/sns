package com.example.sns.core.user.domain.entity.root;

import com.example.sns.core.user.domain.entity.*;
import com.example.sns.core.user.domain.entity.vo.UserProfile;
import com.example.sns.core.user.domain.request.UserCreate;
import com.example.sns.core.user.domain.request.UserUpdate;
import com.example.sns.common.exception.CertificationCodeNotMatchedException;
import com.example.sns.common.service.port.UuidHolder;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class User {
    private final UserId id;
    private final Email email;
    private final Password password;
    private final Nickname nickname;
    private final Sex sex;
    private final Birthday birthday;
    private final UserStatus status;
    private final OnlineStatus online;
    private final Role role;
    private final LocalDateTime lastLoginAt;
    private final String certificationCode;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastActivityAt;

    @Builder
    public User(UserId id, Email email, Password password, Nickname nickname, Sex sex, Birthday birthday,
                UserStatus status, LocalDateTime lastLoginAt, Role role, String certificationCode, OnlineStatus online,
                LocalDateTime createdAt, LocalDateTime lastActivityAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.online = online != null ? online : OnlineStatus.OFF;
        this.role = role != null ? role : Role.ROLE_USER;
        this.sex = sex;
        this.birthday = birthday;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
        this.lastActivityAt = lastActivityAt != null ? lastActivityAt : LocalDateTime.now();
        this.certificationCode = certificationCode;
    }

    public static User from(UserCreate userCreate, UuidHolder uuidHolder) {
        return User.builder()
                .certificationCode(uuidHolder.random())
                .email(userCreate.getEmail())
                .password(userCreate.getPassword())
                .nickname(userCreate.getNickname())
                .status(UserStatus.PENDING)
                .role(Role.ROLE_USER)
                .sex(userCreate.getSex())
                .online(OnlineStatus.OFF)
                .birthday(userCreate.getBirthday())
                .createdAt(userCreate.getCreatedAt())
                .lastActivityAt(LocalDateTime.now())
                .build();
    }

    public User changeRoleToAdmin() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .status(status)
                .sex(sex)
                .role(Role.ROLE_ADMIN)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .lastActivityAt(lastActivityAt)
                .online(online)
                .certificationCode(certificationCode)
                .build();
    }

    public User changeRoleToUser() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .status(status)
                .sex(sex)
                .role(Role.ROLE_USER)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .lastActivityAt(lastActivityAt)
                .online(online)
                .certificationCode(certificationCode)
                .build();
    }

    public User update(UserUpdate userUpdate) {
        return User.builder()
                .id(id)
                .email(userUpdate.getEmail())
                .password(userUpdate.getPassword())
                .nickname(userUpdate.getNickname())
                .status(status)
                .sex(sex)
                .role(role)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .lastActivityAt(lastActivityAt)
                .online(online)
                .certificationCode(certificationCode)
                .build();
    }

    public User login(LocalDateTime localDateTime) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .status(status)
                .sex(sex)
                .role(role)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(localDateTime)
                .lastActivityAt(LocalDateTime.now())
                .online(online)
                .certificationCode(certificationCode)
                .build();
    }

    public User online(LocalDateTime activityTime) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .status(status)
                .sex(sex)
                .role(role)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .lastActivityAt(activityTime)
                .online(OnlineStatus.ON)
                .certificationCode(certificationCode)
                .build();
    }

    public User offline() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .status(status)
                .sex(sex)
                .role(role)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .lastActivityAt(lastActivityAt)
                .online(OnlineStatus.OFF)
                .certificationCode(certificationCode)
                .build();
    }

    public void changeNickname(Nickname nickname) {
        this.nickname.editTo(nickname);
    }

    public User certificate(String certificationCode) {
        if (!this.certificationCode.equals(certificationCode)) {
            throw new CertificationCodeNotMatchedException();
        }
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .status(UserStatus.ACTIVE)
                .sex(sex)
                .role(role)
                .birthday(birthday)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .lastActivityAt(lastActivityAt)
                .online(online)
                .certificationCode(certificationCode)
                .build();
    }

    public UserProfile toUserProfile() {
        return UserProfile.builder()
                .userId(this.getUserIdValue())
                .nickname(this.getNicknameValue())
                .sex(this.sex)
                .build();
    }

    public Long getUserIdValue() {
        return this.id != null ? this.id.getValue() : null;
    }

    public String getOnlineStatusValue() {
        return online != null ? online.name() : OnlineStatus.OFF.name();
    }

    public String getNicknameValue() {
        return nickname.getValue();
    }

    public String getPasswordValue() {
        return password.getValue();
    }

    public String getEmailValue() {
        return email.getValue();
    }

    public LocalDate getBirthdayValue() {
        return birthday.getValue();
    }

    public String getSexValue() {
        return sex.name();
    }

    public String getRoleValue() {
        return role.name();
    }

    public String getStatusValue() {
        return status.name();
    }

    public LocalDateTime getLastActivityAt() {
        return lastActivityAt;
    }
}
