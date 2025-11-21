package com.example.smart_commute.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password; // 存储经过 bcrypt 加密的密码
    private String roles; // 逗号分隔的角色字符串，例如 "USER,ADMIN"
}