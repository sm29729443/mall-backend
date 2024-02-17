package com.tong.mallbackend.dao;

import com.tong.mallbackend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserDao
 * Package: com.tong.mallbackend.dao
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

    public UserEntity getByEmail(String email);

    @Modifying
    @Query(value = "Update UserEntity SET point = point + :point WHERE userId = :userId")
    void updatePoint(@Param(value = "userId") Integer userId, @Param(value = "point") Integer point);
}
