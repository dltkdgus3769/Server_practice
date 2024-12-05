package com.busanit501.spring_prac.mapper;

import com.busanit501.spring_prac.domain.FoodVO;

import java.util.List;

public interface FoodMapper {
    String getTime();

    void insert(FoodVO foodVO);

    List<FoodVO> selectAll();

    FoodVO selectOne(Long fno);

    void delete(Long fno);

    void update(FoodVO foodVO);
}
