package com.example.test.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class Criteria {

    /** 현재 페이지 번호 */
    public int currentPageNo;

    /** 페이지당 출력할 데이터 개수 */
    public int recordsPerPage;

    /** 화면 하단에 출력할 페이지 사이즈 */
    public int pageSize;

    /** 검색 키워드 */
    public String searchKeyword;

    /** 검색 유형 */
    public String searchType;

    public String categorie_code;

    public Criteria() {
        this.currentPageNo = 1;     /** 현재 페이지번호*/
        this.recordsPerPage = 2;   /** 페이지당 출력할 데이터 개수*/
        this.pageSize = 10;         /** 화면 하단에 출력할 페이지의 사이즈*/

    }
    public int getStartPage() {
        return (currentPageNo - 1) * recordsPerPage;
    }

    public String makeQueryString(int pageNo) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("currentPageNo", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("pageSize", pageSize)
                .queryParam("searchType", searchType)
                .queryParam("searchKeyword", searchKeyword)
                .build()
                .encode();

        return uriComponents.toUriString();
    }

}
