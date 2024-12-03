package com.busanit501.spring_prac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    private long fno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
}
