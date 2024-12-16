package com.busanit501.boot_prac.service;


import com.busanit501.boot_prac.dto.PageRequestDTO;
import com.busanit501.boot_prac.dto.PageResponseDTO;
import com.busanit501.boot_prac.dto.FoodDTO;

public interface FoodService {
    Long register(FoodDTO foodDTO);
    FoodDTO readOne(Long fno);
    void update(FoodDTO foodDTO);
    void delete(Long fno);
    PageResponseDTO<FoodDTO> list(PageRequestDTO pageRequestDTO);
}
