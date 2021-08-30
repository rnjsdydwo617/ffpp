package com.example.test.mapper.Board;

import com.example.test.mvc.board.BoardVO;
import com.example.test.mvc.board.CategorieVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int BoardRegister(BoardVO vo);      //글쓰기
    int BoardUpdate(BoardVO vo);        //개시글 수정
    List<BoardVO> BoardGet();           //개시판가지고오기
    List<BoardVO> BoardBest();          //베스트글 가지고오기
    List<BoardVO> BoardNewPost();       //최신글 가지고오기
    List<BoardVO> CategorieBoard(String code);
    List<CategorieVO> CategorieGet();   //카테고리 가지고오기
    List<BoardVO> boardDetail(String board_code);   //개시글 상세보기
    int Viewcnt(String board_code);
}
