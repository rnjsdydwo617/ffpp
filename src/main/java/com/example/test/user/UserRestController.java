package com.example.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//가장 큰 사용 목적은 ajax에서 넘겨준 json정보를 받기 위함이다.
//일반적인 컨트롤러에선 json을 받아올 수 없음
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
    public UserVO LoginAction(UserVO vo , HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        UserVO result = uSvc.LoginAction(vo);

        if(result.getUser_id().equals("")){
            result.setResult(0);
        } else {
            result.setResult(1);
            session.setAttribute("userData" , result);
            session.setMaxInactiveInterval(60*60*24);
        }

        return result;
    }
    @PostMapping(value = "/IdCheck")
    public int IdCheck(UserVO vo) {
        int result = uSvc.IdCheck(vo);
        return result;
    }

}
