package com.busanit501.spring_prac.mapper;


import com.busanit501.spring_prac.domain.FoodVO;
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
}
