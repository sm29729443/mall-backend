package com.tong.mallbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

/**
 * ClassName: UserRegisterRequest
 * Package: com.tong.mallbackend.dto
 */
@Getter
@Setter
public class UserRegisterRequest {

    @JsonProperty(value = "name")
    @NotBlank(message = "姓名不得為空")
    @Size(min = 0, max = 32, message = "長度不得超過 32 個字")
    public String name;

    @JsonProperty(value = "password")
    @Pattern(regexp = "^\\S+$", message = "密碼不得有任何空白符號")
    @Size(min = 8, max = 20, message = "密碼長度不得小於 8 或超過 20")
    public String password;

    @JsonProperty(value = "repeatPassword")
    @Pattern(regexp = "^\\S+$", message = "密碼不得有任何空白符號")
    public String repeatPassword;

    @JsonProperty(value = "email", namespace = "必須為信箱格式")
    @NotBlank(message = "信箱不得為空")
    public String email;
}
