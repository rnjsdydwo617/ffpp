package com.example.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String home(){
        return "hello";
    }

    @GetMapping(value = "/url")
    public String baseRedirection(String type, String query) {
        String url = "body/"+type+"/"+query;
        return url;
    }


    // type -> 대분류
    // query -> 소분류
    // switch, if
    // ex) type fashion [a]
    //

}
