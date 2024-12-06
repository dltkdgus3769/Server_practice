package com.busanit501.spring_prac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value=1)
    @Positive //양수만
    private int page=1;

    @Builder.Default
    @Min(value=10)
    @Max(value=100)
    @Positive
    private int size=10;

    //목록-> 상세보기 화면 이동시 페이지 정보 쿼리 스트링으로 전달
    private String link;

    //검색 또는 필터 관련 조건
    //1검색어
    private String keyword;
    //2 검색 유형
    private String[] types;
    //3 todo 완료 여부
    private boolean finished;
    //4. 기한
    private LocalDate from;
    private LocalDate to;



    public int getSkip(){
        return (page - 1) * size;
    }

    public String getLink() {
        if(link==null){
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);
            link = builder.toString();
        }
        return link;
    }


}
