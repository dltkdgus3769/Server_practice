package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dao.MenuDAO;
import com.busanit501.helloworld.jdbcex.vo.MenuVO;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MenuDAOTest {
    private MenuDAO menuDAO;

    @BeforeEach
    public void ready() {
        menuDAO = new MenuDAO();
    }

    @Test
    public void insertTest() throws SQLException {
        MenuVO menuVO = MenuVO.builder()
                .menu("치킨")
                .price(18000)
                .description("바삭한 후라이드 치킨입니다.")
                .build();
        menuDAO.insert(menuVO);
    }

    @Test
    public void testList() throws SQLException {
        List<MenuVO> list = menuDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void getOneTEst() throws SQLException {
        Long tno = 1L;
        MenuVO menuVO = menuDAO.selectOne(tno);
        System.out.println(menuVO);
    }

    @Test
    public void deleteTest() throws SQLException {
        Long tno = 4L;
        menuDAO.deleteMenu(tno);
    }

    @Test
    public void updateTest() throws SQLException {
        MenuVO menuVO = MenuVO.builder()
                .tno(1L)
                .menu("비빔국수")
                .price(5500)
                .description("매콤한 비빔국수!!!")
                .build();
        menuDAO.updateOne(menuVO);
    }

}
