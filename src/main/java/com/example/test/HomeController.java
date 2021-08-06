package com.example.test;

import com.example.test.mvc.board.BoardService;
import com.example.test.mvc.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private BoardService bSvc;
    @RequestMapping(value = "/")
    public ModelAndView BoardGet1(){
        ModelAndView mav = new ModelAndView();
        List<BoardVO> BoardGet = bSvc.BoardGet();
        mav.addObject("boardGet" , BoardGet);
        mav.setViewName("hello");
        return mav;
    }

    // type -> 대분류
    // query -> 소분류
    // switch, if
    // ex) type fashion [a]
    //

}
