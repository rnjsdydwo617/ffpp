package com.example.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService uSvc;

    @GetMapping(value = "/login")
    public String loginPage() {
        return "body/user/login";
    }

    //sign up 페이지 이동
    @RequestMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView();
        //스프링이 html파일을 찾아가는 방식 = templates를 짜르고 하위를 찾는다.
        mav.setViewName("body/user/signup");
        return mav;
    }

    @GetMapping(value = "/fashion_news")
    public String contentpage(){
        return "body/fashion_news";
    }


    @PostMapping(value = "/uRegister")
    public String UserRegister(UserVO vo){
        return uSvc.UserRegister(vo);
    }
}
