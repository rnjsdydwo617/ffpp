package com.example.test.mapper.Board;

import com.example.test.mvc.board.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
        // 댓글 개수
        int commentCount() throws Exception;

        // 댓글 목록
        List<CommentVo> commentList(String BOARD_CODE);

        // 댓글 작성
        int commentInsert(CommentVo comment);

        // 댓글 수정
        int commentUpdate(CommentVo comment) throws Exception;

        // 댓글 삭제
        String commentDelete(String cno) throws Exception;

    }
