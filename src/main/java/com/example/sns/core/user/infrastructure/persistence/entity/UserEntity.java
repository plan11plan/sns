package com.example.sns.core.user.infrastructure.persistence.entity;

import com.example.sns.core.user.domain.entity.Birthday;
import com.example.sns.core.user.domain.entity.Email;
import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.Password;
import com.example.sns.core.user.domain.entity.Sex;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Sex sex;
    private LocalDate birthday;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "certification_code")
    private String certificationCode;
    private LocalDateTime createdAt;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail().getValue();
        userEntity.password = user.getPassword().getValue();
        userEntity.nickname = user.getNickname().getValue();
        userEntity.sex = user.getSex();
        userEntity.birthday = user.getBirthday().getBirthday();
        userEntity.status = user.getStatus();
        userEntity.certificationCode = user.getCertificationCode();
        userEntity.createdAt = user.getCreatedAt();
        return userEntity;
    }


    public User toModel() {
        return User.builder()
                .id(id)
                .email(new Email(email))
                .password(new Password(password))
                .nickname(new Nickname(nickname))
                .sex(sex)
                .birthday(new Birthday(birthday))
                .status(status)
                .certificationCode(certificationCode)
                .createdAt(createdAt)
                .build();
    }

}
