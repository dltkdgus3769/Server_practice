package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dao.MenuDAO;
import com.busanit501.helloworld.jdbcex.dao.TodoDAO;
import com.busanit501.helloworld.jdbcex.dto.MenuVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class MenuDAOTest {
    private MenuDAO menuDAO;

    @BeforeEach
    public void ready() {
        menuDAO = new MenuDAO();
    }

    @Test
    public void insertTest() throws SQLException {
        MenuVO menuVO = MenuVO.builder()
                .menu("국밥")
                .price(1000)
                .description("맛있는 국밥입니다.")
                .build();
        menuDAO.insert(menuVO);
    }

}
