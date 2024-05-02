package com.example.sns.user.domain.entity.root;

import com.example.sns.user.domain.entity.Age;
import com.example.sns.user.domain.entity.Email;
import com.example.sns.user.domain.entity.Name;
import com.example.sns.user.domain.entity.Nickname;
import com.example.sns.user.domain.entity.Password;
import com.example.sns.user.domain.entity.Sex;
import com.example.sns.user.domain.entity.UserStatus;
import com.example.sns.user.domain.request.UserCreate;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Name name;
    private Age age;
    private Nickname nickname;
    private Email email;
    private Password password;

    @Enumerated
    private Sex sex;
    @Enumerated
    private UserStatus userStatus;

    private LocalDate birthday;
    private LocalDateTime createdAt;

    @Builder
    public User(Name name, Age age, Nickname nickname, Sex sex, Email email, Password password, UserStatus userStatus,
                LocalDate birthday, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }

    public static User from(UserCreate userCreate) {
        return User.builder()
                .name(new Name(userCreate.name()))
                .age(new Age(userCreate.age()))
                .nickname(new Nickname(userCreate.nickname()))
                .email(new Email(userCreate.email()))
                .password(new Password(userCreate.password()))
                .sex(userCreate.sex())
                .userStatus(userCreate.userStatus())
                .birthday(userCreate.birthday())
                .build();
    }
    
}
