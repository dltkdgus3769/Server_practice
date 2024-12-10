package com.busanit501.spring_prac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

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
    private boolean finished2;
    //4. 기한
    private LocalDate from;
    private LocalDate to;



    public int getSkip(){
        return (page - 1) * size;
    }

    public String getLink()  {

            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);
            if(finished2){
                builder.append("&finished2=on");
            }
            if(types != null&&types.length>0){
                for(int i =0;i<types.length;i++){
                    builder.append("&types="+types[i]);
                }
            }
            if(keyword!=null && !keyword.isEmpty()){
                try {
                    builder.append("&keyword="+ URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
//          throw new RuntimeException(e);
                    e.printStackTrace();
                }
            }
            if(from!=null){
                builder.append("&from="+from.toString());
            }
            if(to!=null){
                builder.append("&to="+to.toString());
            }

        return builder.toString();
    }

    //검색 조건 확인하는 기능.
    public boolean checkType(String type){
        if(types==null || types.length==0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

}
