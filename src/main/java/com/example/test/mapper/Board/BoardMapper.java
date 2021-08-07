package com.example.test.mapper.Board;

import com.example.test.mvc.board.BoardVO;
import com.example.test.mvc.board.CategorieVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int BoardRegister(BoardVO vo); //글쓰기
    List<BoardVO> BoardGet();
    List<CategorieVO> CategorieGet();




}
