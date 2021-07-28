package com.example.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserService uSvc;

    @GetMapping(value = "/test")
    public UserVO test(){
        UserVO vo = new UserVO();
        vo.setUser_id("w8230");
        vo.setUser_name("김재일");
        vo.setUser_pwd("1234");
        vo.setEmail("w8230@naver.com");
        return vo;
    }

    @PostMapping(value = "/loginAction")
    public int LoginAction(UserVO vo) {
        int result = uSvc.LoginAction(vo);
        return result;
    }
    @PostMapping(value = "/IdCheck")
    public int IdCheck(UserVO vo) {
        int result = uSvc.IdCheck(vo);
        return result;
    }

}
