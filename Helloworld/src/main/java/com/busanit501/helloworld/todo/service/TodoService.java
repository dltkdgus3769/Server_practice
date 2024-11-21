package com.busanit501.helloworld.todo.service;

import com.busanit501.helloworld.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum TodoService {
    INSTANCE;

    public void register(TodoDTO todoDto){
        System.out.println("글쓰기 작업하는 기능");
    }
    public List<TodoDTO> getList(){
        List<TodoDTO> todoList = IntStream.range(0,10).mapToObj(
                i-> {
                    TodoDTO todoDTO = new TodoDTO();
                    todoDTO.setTitle("테스트 "+i);
                    todoDTO.setTno((long)i);
                    todoDTO.setDueDate(LocalDate.now());
                    return todoDTO;
                }).collect(Collectors.toList());
        return todoList;
    }
    public TodoDTO getone(Long tno){
        TodoDTO todoDTO =new TodoDTO();
        todoDTO.setTno(5L);
        todoDTO.setTitle("하나 조회 더미 데이터");
        todoDTO.setDueDate(LocalDate.now());

        return todoDTO;
    }
}
