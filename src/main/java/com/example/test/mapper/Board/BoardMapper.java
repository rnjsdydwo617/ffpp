package com.example.test.mapper.Board;

import com.example.test.mvc.board.BoardVO;
import com.example.test.mvc.board.CategorieVO;
import com.example.test.paging.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    public int BoardRegister(BoardVO vo);      //글쓰기
    public int BoardUpdate(BoardVO vo);        //개시글 수정
    public List<BoardVO> BoardGet();           //개시판가지고오기
    public List<BoardVO> BoardBest();          //베스트글 가지고오기
    public List<BoardVO> BoardNewPost();       //최신글 가지고오기
    public List<PageInfo> CategorieBoard(String code); //카테고리별 개시글
    public List<CategorieVO> CategorieGet();   //카테고리 가지고오기
    public List<BoardVO> boardDetail(String board_code);   //개시글 상세보기
    public int Viewcnt(String board_code);
    public List<BoardVO> selectBoardList(BoardVO params);
    public int selectBoardTotalCount(BoardVO params);



}



