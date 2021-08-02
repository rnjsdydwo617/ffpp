package com.example.test.user;

import com.example.test.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserMapper uMapper;

    //로그인
    public int LoginAction(UserVO vo) {
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

}
