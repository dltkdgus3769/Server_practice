package com.busanit501.spring_prac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value=1)
    @Positive
    private int page=1;

    @Builder.Default
    @Min(value=10)
    @Max(value=100)
    @Positive
    private int size=10;

    public int getSkip(){
        return (page - 1) * size;
    }
}
