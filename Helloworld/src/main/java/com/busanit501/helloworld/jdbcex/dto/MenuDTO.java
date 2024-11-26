package com.busanit501.helloworld.jdbcex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 멤버의 파라미터로 다 이용한 생성자.
public class MenuDTO {
    private Long tno;
    private String menu;
    private int price;
    private String description;
}
