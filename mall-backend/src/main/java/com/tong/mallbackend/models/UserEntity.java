package com.tong.mallbackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * ClassName: UserEntity
 * Package: com.tong.mallbackend.model
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "mall_tong")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Basic
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;
    @Basic
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Basic
    @Column(name = "registration_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime registrationDate;
    @Basic
    @Column(name = "point", nullable = false)
    private int point;
}
