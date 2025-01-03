package com.busanit501.helloworld.jdbcex.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {
    private Long tno;
    private String menu;
    private int price;
    private String description;
}
