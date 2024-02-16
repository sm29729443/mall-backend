package com.tong.mallbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: UserLoginRequest
 * Package: com.tong.mallbackend.dto
 */
@Setter
@Getter
public class UserLoginRequest {
    @NotBlank(message = "請輸入信箱")
    @JsonProperty(value = "email", required = true)
    private String email;
    @NotBlank(message = "請輸入密碼")
    @JsonProperty(value = "password", required = true)
    private String password;
}
