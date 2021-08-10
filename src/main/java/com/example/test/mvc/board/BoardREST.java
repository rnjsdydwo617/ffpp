package com.example.test.mvc.board;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BoardREST {
    @Autowired
    private BoardService bSvc;

    /*@RequestMapping(value = "/body/fashion/news")
    public String BoardGet(Model model) {
        List<BoardVO> BoardGet = bSvc.BoardGet();
        model.addAttribute("BoardGet",BoardGet);
        System.out.println(bSvc.BoardGet());
        return "body/fashion/news";
    }*/
    @RequestMapping(value = "/body/fashion/write")
    public ModelAndView BoardGet1(){
        ModelAndView mav = new ModelAndView();
        List<BoardVO> BoardGet = bSvc.BoardGet();
        mav.addObject("boardGet" , BoardGet);
        mav.setViewName("hello");
        return mav;
    }
}
