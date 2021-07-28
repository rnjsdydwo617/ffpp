package com.example.test.user;

import lombok.Data;

@Data
public class UserVO {
    private int result;
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String email;
}

