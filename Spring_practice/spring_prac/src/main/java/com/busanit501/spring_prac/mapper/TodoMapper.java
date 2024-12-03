package com.busanit501.spring_prac.mapper;

import com.busanit501.spring_prac.domain.TodoVO;

public interface TodoMapper {
    String getTime();

    void insert(TodoVO todoVO);
}
