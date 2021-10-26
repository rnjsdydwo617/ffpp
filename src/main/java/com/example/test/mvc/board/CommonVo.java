package com.example.test.mvc.board;

import com.example.test.paging.Criteria;
import com.example.test.paging.PaginationInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommonVo extends Criteria {

    /** 페이징 정보  */
    public PaginationInfo paginationInfo;

    /** 삭제 여부 */
    public String deleteYn;

    /** 등록일 */
    public LocalDateTime insertTime;

    /** 수정일 */
    public LocalDateTime updateTime;

    /** 삭제일 */
    public LocalDateTime deleteTime;

}