package com.example.test.mvc.board;


import lombok.Data;

import java.util.List;

@Data
public class BoardVO {
    private int result;
    private String user_code;
    private String user_name;
    private String board_code;
    private String user_id;
    private String categorie_code;
    private String categorie_name;
    private String board_title;
    private String board_sub_title;
    private String board_contents;
    private String img_size;
    private String ing_url;
    private String img_name;
    private String create_day;
    private String update_day;
    private int view;
}


