package com.example.test;

import com.example.test.mvc.board.BoardService;
import com.example.test.mvc.board.BoardVO;
import com.example.test.mvc.board.CategorieVO;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        for(int i=0; i<CategorieGet.size(); i++) {
            System.out.println(CategorieGet.get(i).getCategorie_name());
        }
        mav.addObject("CategorieGet", CategorieGet);
        mav.setViewName("body/fashion/write");
        return mav;
    }
    //개시글 보기
    @RequestMapping(value = "/boardview/{board_code}")
    public ModelAndView boarddetail(@PathVariable("board_code")String board_code ) {
        ModelAndView mav = new ModelAndView();
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        List<BoardVO> boardDetail = bSvc.boardDetail(board_code);
        mav.addObject("boardview",boardDetail);
        mav.addObject("CategorieGet",CategorieGet);
        mav.setViewName("body/fashion/boardview");
        return mav;
    }


    @RequestMapping(value = "/categorie/{CategorieCode}")
    public ModelAndView fashion_news(@PathVariable("CategorieCode")String code,
                                     @RequestParam(required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(required = false, defaultValue = "1") int pageSize)
    {
        ModelAndView mav = new ModelAndView();
        PageHelper.startPage(pageNum,pageSize);
        List<CategorieVO> CategorieGet = bSvc.CategorieGet();
        for(int i=0; i<CategorieGet.size(); i++) {
            System.out.println(CategorieGet.get(i).getCategorie_name());
        }
        List<PageInfo> CategorieBoard = bSvc.CategorieBoard(code,pageNum);
        PageInfo<PageInfo> page = new PageInfo<>(CategorieBoard);
        mav.addObject("page",page);
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
