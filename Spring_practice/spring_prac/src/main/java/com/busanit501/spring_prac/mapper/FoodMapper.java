package com.busanit501.spring_prac.mapper;

import com.busanit501.spring_prac.domain.FoodVO;
import com.busanit501.spring_prac.dto.PageRequestDTO;

import java.util.List;

public interface FoodMapper {
    String getTime();

    void insert(FoodVO foodVO);

    List<FoodVO> selectAll();

    FoodVO selectOne(Long fno);

    void delete(Long fno);

    void update(FoodVO foodVO);

    List<FoodVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
