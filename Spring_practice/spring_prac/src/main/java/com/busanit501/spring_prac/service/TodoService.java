package com.busanit501.spring_prac.service;

import com.busanit501.spring_prac.dto.PageRequestDTO;
import com.busanit501.spring_prac.dto.PageResponseDTO;
import com.busanit501.spring_prac.dto.TodoDTO;

import java.util.List;


public interface TodoService {
    void register(TodoDTO todoDTO);

    List<TodoDTO> getAll();

    //페이징 처리된 목록
//    PageResponseDTO<TodoDTO> getListWithPage(PageRequestDTO pageRequestDTO);

    PageResponseDTO<TodoDTO> selectList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void delete(Long tno);

    void update(TodoDTO todoDTO);


}
