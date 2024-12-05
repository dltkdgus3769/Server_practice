package com.busanit501.spring_prac.service;

import com.busanit501.spring_prac.dto.FoodDTO;

import java.util.List;


public interface FoodService {
    void register(FoodDTO foodDTO);

    List<FoodDTO> getAll();

    FoodDTO getOne(Long fno);

    void delete(Long fno);
    void update(FoodDTO foodDTO);
}
