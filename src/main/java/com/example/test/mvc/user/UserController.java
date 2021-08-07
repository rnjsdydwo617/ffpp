package com.example.test.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//GET,POST,REQUEST
//1. GET , POST  말 그대로 메소드 방식의 이름
//2. REQUEST는 후에 메소드 방식 지정 할수 있음 지정 없은면 기본은 GET

@Controller
public class UserController {

    @Autowired
    UserService uSvc;

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "body/user/login";
    }

    //sign up 페이지 이동
    /*@RequestMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView();
        //스프링이 html파일을 찾아가는 방식 = templates를 짜르고 하위를 찾는다.
        mav.setViewName("body/user/signup");
        return mav;
    }*/
    @GetMapping(value = "signup")
    public String signup() { return "body/user/signup"; }



    //Model And View를 사용 하는 경우
    //단순 페이지 이동을 해야하는 경우
    //1. Mav 사용 하는 경우 -- 게시판 목록을 DB에서 가져와서 자바에서 List에 담고 뷰에 뿌려줘야 할때
    //2. 단순 페이지 이동만 하는 경우 String 경로만 찍어주면 이동은 된다.


    @RequestMapping(value = "/logoutAction")
    public String logoutAction(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "hello";
    }

    @PostMapping(value = "/uRegister")
    public String UserRegister(UserVO vo) {
        return uSvc.UserRegister(vo);
    }
}