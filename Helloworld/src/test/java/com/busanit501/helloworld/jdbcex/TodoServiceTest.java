package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class TodoServiceTest {
    private TodoService todoService;
    @BeforeEach
    public void ready(){
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testInsert() throws SQLException {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("샘플 작업 1126")
                .dueDate(LocalDate.now())
                .build();
        todoService.register(todoDTO);
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<TodoDTO> dtoList = todoService.listAll();
        for(TodoDTO todoDTO:dtoList){
            log.info(todoDTO);
        }
    }

    //하나 조회
    @Test
    public void testSelectone() throws SQLException {
        TodoDTO todoDTO = todoService.get(1L);
        log.info(todoDTO);
    }

    //수정 테스트
    @Test
    public void testUpdate() throws SQLException {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(1L)
                .title("수정 샘플 작업 1127")
                .dueDate(LocalDate.now())
                .build();
        todoService.update(todoDTO);
    }

    //삭제 테스트
    @Test
    public void testDelete() throws SQLException {
        todoService.delete(1L);
    }

}
