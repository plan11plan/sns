package com.example.sns.user.domain.repository;

import com.example.sns.user.domain.entity.root.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
