package com.example.test.mvc.board;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class BoardVO extends CommonVo{
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
    private LocalDate create_day;
    private String update_day;
    private int board_views;
}


