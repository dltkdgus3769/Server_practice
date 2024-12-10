package com.busanit501.spring_prac.service;

import com.busanit501.spring_prac.dto.FoodDTO;
import com.busanit501.spring_prac.dto.PageRequestDTO;
import com.busanit501.spring_prac.dto.PageResponseDTO;

import java.util.List;


public interface FoodService {
    void register(FoodDTO foodDTO);

    List<FoodDTO> getAll();

    FoodDTO getOne(Long fno);

    void delete(Long fno);

    void update(FoodDTO foodDTO);

    //페이징 처리된 목록
//    PageResponseDTO<FoodDTO> getListWithPage(PageRequestDTO pageRequestDTO);

    PageResponseDTO<FoodDTO> selectList(PageRequestDTO pageRequestDTO);
}
