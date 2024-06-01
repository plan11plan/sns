package com.example.sns.core.user.domain.service.port;

public interface MailSender{

    void send(String email,String title,String content);
}
