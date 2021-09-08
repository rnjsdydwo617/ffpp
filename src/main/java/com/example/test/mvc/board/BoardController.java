package com.example.test.mvc.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;
import java.util.logging.Logger;


@Controller
public class BoardController {
    @Autowired
    private BoardService bVc;

    //개시글 등록
    @PostMapping(value = "/BoardRegister")
    public void board_write(BoardVO vo , HttpServletRequest req , HttpServletResponse res) throws IOException {
        int result = bVc.BoardRegister(vo);

        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        if(result == 0){
            out.println("<script>alert('게시글 등록에 실패하였습니다.'); location.href='/body/fashion/write'; </script>");
        } else {
            out.println("<script>alert('게시글 등록에 성공 하였습니다.'); location.href='/'; </script>");
            out.flush();
        }
    }


}


