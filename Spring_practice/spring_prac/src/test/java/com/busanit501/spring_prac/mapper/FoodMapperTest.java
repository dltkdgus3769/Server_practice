package com.busanit501.spring_prac.mapper;


import com.busanit501.spring_prac.domain.FoodVO;
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
public class FoodMapperTest {

    @Autowired(required = false)
    private FoodMapper foodMapper;

    @Test
    public void testGetTime(){
        log.info("getTime:"+foodMapper.getTime());
    }

    @Test
    public void testInsert(){
        FoodVO foodVO = FoodVO.builder()
                .title("샘플 테스트")
                .dueDate(LocalDate.now())
                .writer("이상현").build();
        foodMapper.insert(foodVO);
    }

    @Test
    public void testSelectAll(){
        List<FoodVO> lists=foodMapper.selectAll();
        for(FoodVO foodVO:lists){
            log.info("foodVO : "+foodVO);
        }
    }

    @Test
    public void testSelectOne(){
        FoodVO foodVO = foodMapper.selectOne(1L);
        log.info("foodVO:"+foodVO);
    }

    @Test
    public void testDelete(){
        foodMapper.delete(1L);
    }

    @Test
    public void testUpdate(){
        FoodVO foodVO = FoodVO.builder()
                .fno(5L)
                .title("업데이트 테스트")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
        foodMapper.update(foodVO);
    }

    @Test
    public void testSelectAllWithPage(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .keyword("테스트")
                .types(new String[]{"t","w"})
//                .types(null)
                .finished(true)
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .build();

        List<FoodVO> list= foodMapper.selectList(pageRequestDTO);
        list.forEach(vo->log.info("vo : "+vo));
    }

    @Test
    public void testGetCount(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("테스트")
                .types(new String[]{"t","w"})
//                .types(null)
                .finished(false)
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .build();

        int total = foodMapper.getCount(pageRequestDTO);
        log.info("total : "+total);
    }
}
