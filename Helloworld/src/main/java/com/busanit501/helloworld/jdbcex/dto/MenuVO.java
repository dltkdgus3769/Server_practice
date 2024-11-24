package com.busanit501.helloworld.jdbcex.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuVO {
    private Long tno;
    private String menu;
    private int price;
    private String description;
}
