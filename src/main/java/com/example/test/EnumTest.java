package com.example.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum EnumTest {
    MENU("메뉴테스트"),
    MENU1("메뉴테스트1"),
    MENU2("메뉴테스트2"),
    MENU3("메뉴테스트3"),
    MENU4("메뉴테스트4");

    private final String menu;
}
