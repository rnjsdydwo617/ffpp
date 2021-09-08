package com.example.test.mvc.board;


import lombok.Data;

@Data
public class CommentVo {
    private String CNO;
    private String BOARD_CODE;
    private String USER_ID;
    private String COM_CONTENT;
    private String CREATR_DAY;

}
