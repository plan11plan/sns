package com.example.sns.core.user.controller.port;

import com.example.sns.core.user.domain.entity.root.User;

public interface UserService {

    public User create(User user);
    public User get(Long id);
}
