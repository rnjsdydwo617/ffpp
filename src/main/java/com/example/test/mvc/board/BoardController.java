package com.example.test.mvc.board;

import com.example.test.mvc.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;


@Controller
public class BoardController {
    @Autowired
    private BoardService bVc;

    @PostMapping(value = "/BoardRegister")
    public void board_write(BoardVO vo , HttpServletRequest req , HttpServletResponse res) throws IOException {
        int result = bVc.BoardRegister(vo);

        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        if(result == 0){
            out.println("<script>alert('게시글 등록에 실패하였습니다.'); location.href='/body/fashion/news'; </script>");
        } else {
            out.println("<script>alert('게시글 등록에 성공 하였습니다.'); location.href='/'; </script>");
            out.flush();
        }
    }

    @RequestMapping(value = "/fashion_news")
    public ModelAndView fashion_news() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mnList", bVc.CategorieGet());
        mav.setViewName("body/fashion/news");
        return mav;
    }
}
