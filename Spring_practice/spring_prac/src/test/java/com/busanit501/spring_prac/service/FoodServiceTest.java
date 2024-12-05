package com.busanit501.spring_prac.service;


import com.busanit501.spring_prac.dto.FoodDTO;
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
public class FoodServiceTest {


    @Autowired
    private FoodService foodService;


    @Test
    public void testRegister(){
        FoodDTO foodDTO = FoodDTO.builder()
                .title("샘플데이터 서비스에서 입력")
                .dueDate(LocalDate.now())
                .writer("이상현").build();

        foodService.register(foodDTO);
    }

    @Test
    public void testGetAll(){
        List<FoodDTO> list= foodService.getAll();
        for(FoodDTO foodDTO:list){
            log.info("foodDTO:"+foodDTO);
        }
    }
}
