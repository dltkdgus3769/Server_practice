package com.busanit501.spring_prac.service;


import com.busanit501.spring_prac.dto.PageRequestDTO;
import com.busanit501.spring_prac.dto.PageResponseDTO;
import com.busanit501.spring_prac.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    public void testGetAll(){
        List<TodoDTO> list= todoService.getAll();
        for(TodoDTO todoDTO:list){
            log.info("todoDTO:"+todoDTO);
        }
    }

    @Test
    public void testGetOne(){
        TodoDTO todoDTO = todoService.getOne(1L);
        log.info("todoDTO:"+todoDTO);
    }

    @Test
    public void testDelete(){
        todoService.delete(2L);
    }

    @Test
    public void testUpdate(){
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(6L)
                .title("업데이트 테스트2")
                .dueDate(LocalDate.now())
                .finished(true).
                build();
        todoService.update(todoDTO);
    }

    @Test
    public void testPageList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("수정")
                .types(new String[]{"t","w"})
//                .types(null)
                .finished(false)
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .build();
        PageResponseDTO<TodoDTO> list = todoService.selectList(pageRequestDTO);
        log.info("list:"+list);
        list.getDtoList().stream().forEach(dto-> log.info("dto:"+dto));


    }


}
