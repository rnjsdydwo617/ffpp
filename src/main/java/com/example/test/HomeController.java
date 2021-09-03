package com.example.test;

import com.example.test.mvc.board.BoardService;
import com.example.test.mvc.board.BoardVO;
import com.example.test.mvc.board.CategorieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private BoardService bSvc;

    @RequestMapping(value = "/")
    public ModelAndView BoardBest(){
        ModelAndView mav = new ModelAndView();
        List<BoardVO> BoardBest = bSvc.BoardBest();
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        List<BoardVO> BoardNewPost = bSvc.BoardNewPost();
        mav.addObject("BoardNewPost",BoardNewPost);
        mav.addObject("BoardBest" , BoardBest);
        mav.addObject("CategorieGet",CategorieGet);
        mav.setViewName("hello");

        return mav;
    }
    //글쓰기
    @RequestMapping(value = "/write")
    public ModelAndView write() {
        ModelAndView mav = new ModelAndView();
        List<CategorieVO> CategorieGet =bSvc.CategorieGet();
        mav.addObject("mnList", CategorieGet);
        mav.setViewName("body/fashion/write");
        return mav;
    }
    //개시글 보기
    @RequestMapping(value = "/boardview/{board_code}")
    public ModelAndView boarddetail(@PathVariable("board_code")String board_code) {
        ModelAndView mav = new ModelAndView();
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        List<BoardVO> boardDetail = bSvc.boardDetail(board_code);
        mav.addObject("boardview",boardDetail);
        mav.addObject("CategorieGet",CategorieGet);
        mav.setViewName("body/fashion/boardview");
        return mav;
    }

    @RequestMapping(value = "/categorie/{CategorieCode}")
    public ModelAndView fashion_news(@PathVariable("CategorieCode")String code) {
        List<BoardVO> CategorieBoard = bSvc.CategorieBoard(code);
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        ModelAndView mav = new ModelAndView();
        mav.addObject("CategorieBoard",CategorieBoard);
        mav.addObject("CategorieGet",CategorieGet);
        mav.setViewName("body/fashion/news");

        return mav;
    }


    /*@RequestMapping(value = "/lookbook")
    public ModelAndView lookbook(@PathVariable("CategorieCode")String code) {
        List<BoardVO> CategorieBoard = bSvc.CategorieBoard(code);
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        ModelAndView mav = new ModelAndView();
        mav.addObject("CategorieBoard",CategorieBoard);
        mav.addObject("CategorieGet",CategorieGet);
        mav.setViewName("body/fashion/lookbook");
        return mav;
    }
    @RequestMapping(value = "/sneakers")
    public ModelAndView sneakers(@PathVariable("CategorieCode")String code) {
        List<BoardVO> CategorieBoard = bSvc.CategorieBoard(code);
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        ModelAndView mav = new ModelAndView();
        mav.addObject("CategorieBoard",CategorieBoard);
        mav.addObject("CategorieGet",CategorieGet);
        mav.setViewName("body/fashion/sneakers");
        return mav;
    }
    @RequestMapping(value = "/clothes")
    public ModelAndView clothes(@PathVariable("CategorieCode")String code) {
        List<BoardVO> CategorieBoard = bSvc.CategorieBoard(code);
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        ModelAndView mav = new ModelAndView();
        mav.addObject("CategorieBoard",CategorieBoard);
        mav.addObject("CategorieGet",CategorieGet);
        mav.setViewName("body/fashion/clothes");
        return mav;
    }*/


    // type -> 대분류
    // query -> 소분류
    // switch, if
    // ex) type fashion [a]
    //

}
