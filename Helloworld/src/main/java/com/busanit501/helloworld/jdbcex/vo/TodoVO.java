package com.busanit501.helloworld.jdbcex.vo;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
