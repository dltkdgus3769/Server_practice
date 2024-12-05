package com.busanit501.spring_prac.mapper;

import com.busanit501.spring_prac.domain.TodoVO;
import com.busanit501.spring_prac.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
