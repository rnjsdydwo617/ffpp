package com.example.test.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;
//GET,POST,REQUEST
//1. GET , POST  말 그대로 메소드 방식의 이름
//2. REQUEST는 후에 메소드 방식 지정 할수 있음 지정 없은면 기본은 GET

@Controller
public class UserController {


    @Autowired
    UserService uSvc;


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
    public String logoutAction(HttpServletRequest req,HttpServletResponse res) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "body/user/login";
    }

    @PostMapping(value = "/uRegister")
    public String UserRegister(UserVO vo) {
        System.out.println(vo);
        return uSvc.UserRegister(vo);
    }
    @PostMapping(value = "/naverRegister")
    public String naverRegister(UserVO vo){
        System.out.println(vo);
        return uSvc.naverRegister(vo);
    }

    //네이버로그인
    private String CLIENT_ID = "WzMiSfyH0RCXxuTJmEg6";
    private String CLI_SECRET = "zmSVnvIMyw";


    @RequestMapping(value = "/login")
    public String NaverLogin(HttpSession session, Model model) throws UnsupportedEncodingException{

        String redirectURI = URLEncoder.encode("http://localhost:9000/naver/callback","UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
                CLIENT_ID, redirectURI, state);
        session.setAttribute("state", state);
        model.addAttribute("apiURL", apiURL);
        return "body/user/login";
    }
    //콜백 페이지
    @RequestMapping("/naver/callback")
    public String naverCallback1(HttpSession session, HttpServletRequest request, Model model) throws IOException, ParseException {
        // 이 부분에서 db처리 ->
        // 1. parsedJson.get("access_token") -> db에다가 조회를 해 select count(*) from user where token = #{token} -> 0 or 1
        // 0 -> register
        // 1 -> User user = select * from user token = #{token} -> 1 row
        // user -> addObject("session",user)

        int cnt = 0;
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback", "UTF-8");
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL, apiURL);
        if(res != null && !res.equals("")) {
            model.addAttribute("res", res);
            Map<String, Object> parsedJson = new JSONParser(res).parseObject();
            cnt = uSvc.vaildTokenUser(parsedJson.get("access_token").toString());
            model.addAttribute("token",parsedJson.get("access_token").toString());
            session.setAttribute("currentUser", res);
            session.setAttribute("currentAT", parsedJson.get("access_token"));
            session.setAttribute("currentRT", parsedJson.get("refresh_token"));
        } else {
            model.addAttribute("res", "Login failed!");
        }
        if(cnt != 0) {
            return "body/user/naver_callback";
        }
        else {
            model.addAttribute("type","naver");
            return "/body/user/signup";
        }
    }

    @RequestMapping("/naver/refreshToken")
    public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken) throws IOException, ParseException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&refresh_token=" + refreshToken;
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL, apiURL);
        model.addAttribute("res", res);
        session.invalidate();
        return "body/user/naver_callback";
    }
    @RequestMapping("/naver/deleteToken")
    public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken) throws IOException {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLI_SECRET;
        apiURL += "&access_token=" + accessToken;
        apiURL += "&service_provider=NAVER";
        System.out.println("apiURL=" + apiURL);
        String res = requestToServer(apiURL, apiURL);
        model.addAttribute("res", res);
        session.invalidate();
        return "body/user/naver_callback";
    }
    @ResponseBody
    @RequestMapping("/naver/getProfile")
    public String getProfileFromNaver(String accessToken) throws IOException {
        // 네이버 로그인 접근 토큰;
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
        String res = requestToServer(apiURL, headerStr);
        return res;
    }
    //  네이버 로그아웃
    @RequestMapping("/naver/invalidate")
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "redirect:/naver";
    }



    private String requestToServer(String apiURL) throws IOException {
        return requestToServer(apiURL, "");
    }


    private String requestToServer(String apiURL, String headerStr) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        System.out.println("header Str: " + headerStr);
        if(headerStr != null && !headerStr.equals("") ) {
            con.setRequestProperty("Authorization", headerStr);
        }
        int responseCode = con.getResponseCode();
        BufferedReader br;
        System.out.println("responseCode="+responseCode);
        if(responseCode == 200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer res = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        if(responseCode==200) {
            return res.toString();
        } else {
            return null;
        }
    }

}
