package com.example.test.mvc.board;

import com.example.test.mapper.Board.BoardMapper;
import com.example.test.mvc.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper bMap;

    public int BoardRegister(BoardVO vo) {
        return bMap.BoardRegister(vo);
    }
    public List<BoardVO> BoardGet() {
        return bMap.BoardGet();
    }
    public List<CategorieVO> CategorieGet(){
        return bMap.CategorieGet();
    }
    public List<BoardVO> CategorieBoardGet(){
        return bMap.BoardGet();
    }
}
