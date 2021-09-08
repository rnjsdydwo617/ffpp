package com.example.test.mvc.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService mCommentService;


    @RequestMapping(value = "/list/{board_code}") //댓글 리스트
    @ResponseBody
    private List<CommentVo> mCommentServiceList(@PathVariable(name = "board_code") String cno){
        return mCommentService.commentListService(cno);
    }



    @ResponseBody
    @PostMapping("/insert") //댓글 작성
    private boolean mCommentServiceInsert(  @RequestParam(name = "board_code") String board_code,
                                            @RequestParam(name = "user_id") String user_id,
                                            @RequestParam(name = "content") String content) {
        CommentVo comment = new CommentVo();
        comment.setUSER_ID(user_id);
        comment.setBOARD_CODE(board_code);
        comment.setCOM_CONTENT(content);
       int a = mCommentService.commentInsertService(comment);
        if(a > 0) {
            return true;
        } else {
            return false;
        }

    }

    @RequestMapping(value = "/update") //댓글 수정
    @ResponseBody
    private int mCommentServiceUpdateProc(@RequestParam String cno, @RequestParam String content) throws Exception{

        CommentVo comment = new CommentVo();
        comment.setCNO(cno);
        comment.setCOM_CONTENT(content);
        return mCommentService.commentUpdateService(comment);
    }

    @RequestMapping(value = "/delete/{cno}") //댓글 삭제
    @ResponseBody
    private String mCommentServiceDelete(@PathVariable String cno) throws Exception{

        return mCommentService.commentDeleteService(cno);
    }
}
