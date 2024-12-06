package com.busanit501.spring_prac.mapper;


import com.busanit501.spring_prac.domain.TodoVO;
import com.busanit501.spring_prac.dto.PageRequestDTO;
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
public class TodoMapperTest {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime(){
        log.info("getTime:"+todoMapper.getTime());
    }

    @Test
    public void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("샘플 테스트")
                .dueDate(LocalDate.now())
                .writer("이상현").build();
        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll(){
        List<TodoVO> lists=todoMapper.selectAll();
        for(TodoVO todoVO:lists){
            log.info("todoVO : "+todoVO);
        }
    }

    @Test
    public void testSelectOne(){
        TodoVO todoVO = todoMapper.selectOne(1L);
        log.info("todoVO:"+todoVO);
    }

    @Test
    public void testDelete(){
        todoMapper.delete(1L);
    }

    @Test
    public void testUpdate(){
        TodoVO todoVO = TodoVO.builder()
                .tno(5L)
                .title("업데이트 테스트")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
        todoMapper.update(todoVO);
    }

    @Test
    public void testSelectAllWithPage(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .keyword("샘플")
                .types(new String[]{"t","w"})
//                .types(null)
                .finished(true)
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .build();

        List<TodoVO> list= todoMapper.selectList(pageRequestDTO);
        list.forEach(vo->log.info("vo : "+vo));
    }

    @Test
    public void testGetCount(){
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

        int total = todoMapper.getCount(pageRequestDTO);
        log.info("total : "+total);
    }

}
