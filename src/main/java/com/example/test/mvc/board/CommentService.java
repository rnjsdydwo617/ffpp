package com.example.test.mvc.board;
import com.example.test.mapper.Board.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper mCommentMapper;

    //댓글보기
    public List<CommentVo> commentListService(String BOARD_CODE) {

        return mCommentMapper.commentList(BOARD_CODE);
    }
    //댓글달기
    public int commentInsertService(CommentVo comment){

        return mCommentMapper.commentInsert(comment);
    }
    //수정
    public int commentUpdateService(CommentVo comment) throws Exception{

        return mCommentMapper.commentUpdate(comment);
    }
    //삭제
    public String commentDeleteService(String cno) throws Exception{

        return mCommentMapper.commentDelete(cno);
    }
}


