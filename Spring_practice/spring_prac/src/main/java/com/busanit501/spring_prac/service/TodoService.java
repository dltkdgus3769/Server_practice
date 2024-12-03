package com.busanit501.spring_prac.service;

import com.busanit501.spring_prac.dto.TodoDTO;
import org.springframework.stereotype.Service;


public interface TodoService {
    void register(TodoDTO todoDTO);
}
