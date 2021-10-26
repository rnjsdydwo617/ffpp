package com.example.test.mvc.user;

import com.example.test.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper uMapper;

    //로그인
    public UserVO LoginAction(UserVO vo) {
        return  uMapper.LoginAction(vo);
    }
    public int IdCheck(UserVO vo){
        return uMapper.IdCheck(vo);
    }

    public String UserRegister(UserVO vo) {

        if (uMapper.UserRegister(vo) == 0) {
            return "body/user/signup";
        } else {
            return "body/user/login";
        }
    }


    public int vaildTokenUser(String token) {
        return uMapper.vaildTokenUser(token);
    }

    public String naverRegister(UserVO vo) {
        if (uMapper.naverRegister(vo) == 0) {
            return "body/user/signup";
        } else {
            return "body/user/login";
        }
    }
}
