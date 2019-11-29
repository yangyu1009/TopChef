package com.topchef.demo.repository;

import com.topchef.demo.domain.TopChefUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopChefUserRepository extends JpaRepository<TopChefUser, String> {
    @Query(value = "select * from user", nativeQuery = true)
    List<TopChefUser> getAllUserInfo();
}
