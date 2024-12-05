package com.busanit501.spring_prac.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodVO {
    private long fno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
}
