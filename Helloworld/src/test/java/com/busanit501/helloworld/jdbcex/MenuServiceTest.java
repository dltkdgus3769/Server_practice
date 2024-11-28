package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dto.MenuDTO;
import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.MenuService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class MenuServiceTest {
    private MenuService menuService;
    @BeforeEach
    public void ready(){
        menuService = MenuService.INSTANCE;
    }

    @Test
    public void testInsert() throws SQLException {
        MenuDTO menuDTO = MenuDTO.builder()
                .menu("메뉴 샘플 3")
                .price(10000)
                .description("메뉴 샘플 설명3")
                .build();
        menuService.register(menuDTO);
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<MenuDTO> dtoList = menuService.listAll();
        for(MenuDTO menuDTO:dtoList){
            log.info(menuDTO);
        }
    }

    //하나 조회
    @Test
    public void testSelectone() throws SQLException {
        MenuDTO menuDTO = menuService.get(1L);
        log.info(menuDTO);
    }

    //수정 테스트
    @Test
    public void testUpdate() throws SQLException {
        MenuDTO menuDTO = MenuDTO.builder()
                .tno(11L)
                .menu("수정 샘플 작업 1127")
                .price(12345)
                .description("abcde")
                .build();
        menuService.update(menuDTO);
    }

    //삭제 테스트
    @Test
    public void testDelete() throws SQLException {
        menuService.delete(12L);
    }

}
