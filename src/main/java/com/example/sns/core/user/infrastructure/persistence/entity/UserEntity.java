package com.example.sns.core.user.infrastructure.persistence.entity;

import com.example.sns.core.user.domain.entity.*;
import com.example.sns.core.user.domain.entity.root.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String sex;
    private LocalDate birthday;
    @Column(name = "status")
    private String status;
    private String role;
    @Column(name = "certification_code")
    private String certificationCode;
    private LocalDateTime createdAt;
    @Column(name = "online_status")
    private String onlineStatus;
    @Column(name = "last_activity_at")
    private LocalDateTime lastActivityAt;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getUserIdValue();
        userEntity.email = user.getEmailValue();
        userEntity.password = user.getPasswordValue();
        userEntity.nickname = user.getNicknameValue();
        userEntity.sex = user.getSexValue();
        userEntity.role = user.getRoleValue();
        userEntity.birthday = user.getBirthdayValue();
        userEntity.status = user.getStatusValue();
        userEntity.certificationCode = user.getCertificationCode();
        userEntity.createdAt = user.getCreatedAt();
        userEntity.onlineStatus = user.getOnlineStatusValue();
        userEntity.lastActivityAt = user.getLastActivityAt();
        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .id(new UserId(id))
                .email(new Email(email))
                .password(new Password(password))
                .nickname(new Nickname(nickname))
                .sex(Sex.valueOf(sex))
                .birthday(new Birthday(birthday))
                .status(UserStatus.valueOf(status))
                .role(Role.valueOf(role))
                .certificationCode(certificationCode)
                .createdAt(createdAt)
                .online(onlineStatus != null ? OnlineStatus.valueOf(onlineStatus) : OnlineStatus.OFF)
                .lastActivityAt(lastActivityAt)
                .build();
    }
}
