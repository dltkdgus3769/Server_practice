package com.busanit501.spring_prac.service;


import com.busanit501.spring_prac.dto.TodoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/root-context.xml")
//@RequiredArgsConstructor
public class TodoServiceTest {

    //방법1
    @Autowired
    private TodoService todoService;

    //방법2
    //@RequiredArgsConstructor 사용
//    private final TodoService todoService;

    @Test
    public void testRegister(){
        TodoDTO todoDTO = TodoDTO.builder()
                .title("샘플데이터 서비스에서 입력")
                .dueDate(LocalDate.now())
                .writer("이상현").build();

        todoService.register(todoDTO);
    }


}
