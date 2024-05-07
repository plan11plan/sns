package com.example.sns.unit.user.service;

import com.example.sns.core.user.service.CertificationService;
import com.example.sns.mock.FakeMailSender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CertificationServiceTest {

    @DisplayName("이메일과 컨텐츠가 제대로 만들어져서 보내지는지 테스트합니다.")
    @Test
    void test1() {
        // given
        FakeMailSender mailSender = new FakeMailSender();
        CertificationService certificationService = new CertificationService(mailSender);

        // when
        certificationService.send("email@gmail.com",1L,"123");
        // then
        Assertions.assertThat(mailSender.email).isEqualTo("email@gmail.com");
        Assertions.assertThat(mailSender.title).isEqualTo("Please certify your email address");
        Assertions.assertThat(mailSender.content).isEqualTo("Please cl   ick the following link to certify your email address: http://localhost:8080/api/users/1/verify?certificationCode=123");

    }
}
