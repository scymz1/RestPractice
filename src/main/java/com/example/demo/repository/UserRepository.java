package com.example.demo.repository;

import com.example.demo.pojo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo getUserInfoById(String id);
}
