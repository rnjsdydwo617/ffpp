package com.example.test.mvc.user;

import lombok.Data;

// edit
@Data
public class UserVO {
    private int result;
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String email;
    private String msg;
}

