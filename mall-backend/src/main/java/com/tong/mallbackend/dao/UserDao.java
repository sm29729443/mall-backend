package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

    public UserEntity getByEmail(String email);


}
