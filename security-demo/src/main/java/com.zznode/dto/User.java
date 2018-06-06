package com.zznode.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by sc_ma on 2018/1/24.
 */
public class User {
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
