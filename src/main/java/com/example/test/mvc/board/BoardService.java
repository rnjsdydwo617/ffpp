package com.example.test.mvc.board;

import com.example.test.mapper.Board.BoardMapper;
import com.example.test.mvc.user.UserVO;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageInfo;
import com.example.test.paging.PaginationInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    //베스트글
    public List<BoardVO> BoardBest() {
        return bMap.BoardBest();
    }
    //최신글
    public List<BoardVO> BoardNewPost() {
        return bMap.BoardNewPost();
    }
    public List<CategorieVO> CategorieGet(){
        return bMap.CategorieGet();
    }

    public List<PageInfo> CategorieBoard(String code, int pageNo){
        PageHelper.startPage(pageNo, 10);
        return bMap.CategorieBoard(code);
    }


    //개시글수정
    public int BoardUpdate(BoardVO vo){
        return bMap.BoardUpdate(vo);
    }

    //개시글 불러오기
    public List<BoardVO> boardDetail(String board_code) {
        Viewcnt(board_code);
        return bMap.boardDetail(board_code);
    }

    //조회수 올리기
    public void Viewcnt(String board_code) {
        bMap.Viewcnt(board_code);
    }



//   public List<BoardVO> getBoardList(BoardVO params) {
//        List<BoardVO> boardList = Collections.emptyList();
//        int boardTotalCount = bMap.selectBoardTotalCount(params);
//        PaginationInfo paginationInfo = new PaginationInfo(params);
//        paginationInfo.setTotalRecordCount(boardTotalCount);
//
//        params.setPaginationInfo(paginationInfo);
//
//        if (boardTotalCount > 0) {
//            boardList = bMap.selectBoardList(params);
//        }
//        return boardList;
//    }


}
