package com.example.test.mvc.board;


import lombok.Data;

import java.util.List;

@Data
public class BoardVO {
    private int result;
    private String board_code;
    private String categorie_code;
    private String board_title;
    private String board_title_sub;
    private String board_contents;
    private String img_size;
    private String ing_url;
    private String img_name;
    private String create_day;
    private String update_day;
    private int view;
}

