package com.example.test;

import com.example.test.mvc.board.BoardService;
import com.example.test.mvc.board.BoardVO;
import com.example.test.mvc.board.CategorieVO;
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
    public ModelAndView BoardGet(){
        ModelAndView mav = new ModelAndView();
        List<BoardVO> BoardGet = bSvc.BoardGet();
        mav.addObject("boardGet" , BoardGet);
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(value = "/write")
    public ModelAndView write() {
        ModelAndView mav = new ModelAndView();
        List<CategorieVO> CategorieGet =bSvc.CategorieGet();
        mav.addObject("mnList", CategorieGet);
        mav.setViewName("body/fashion/write");
        return mav;
    }
    @RequestMapping(value = "/fashion_news")
    public ModelAndView fashion_news() {
        List<BoardVO> BoardGet = bSvc.BoardGet();
        ModelAndView mav = new ModelAndView();
        mav.addObject("boardGet",BoardGet);
        mav.setViewName("body/fashion/news");
        return mav;
    }
    @RequestMapping(value = "/lookbook")
    public ModelAndView lookbook() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("body/fashion/lookbook");
        return mav;
    }
    @RequestMapping(value = "/sneakers")
    public ModelAndView sneakers() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("body/fashion/sneakers");
        return mav;
    }
    @RequestMapping(value = "/clothes")
    public ModelAndView clothes() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("body/fashion/clothes");
        return mav;
    }

    // type -> 대분류
    // query -> 소분류
    // switch, if
    // ex) type fashion [a]
    //

}
