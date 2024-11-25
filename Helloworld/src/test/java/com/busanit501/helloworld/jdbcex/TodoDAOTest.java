package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dao.TodoDAO;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class TodoDAOTest {
    private TodoDAO todoDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void getTime() {
        System.out.println("sql 전달 후, 시간 조회 확인용: " + todoDAO.getTime());
    }

    @Test
    public void getTime2() throws SQLException {
        System.out.println("sql 전달 후, " +
                "시간 조회 확인용: 자동 반납 @Cleanup 확인 " + todoDAO.getTime2());
    }

    @Test
    public void insetTest() throws Exception {
        TodoVO todoVO1 = TodoVO.builder()
                .title("샘플 디비 작성 테스트4")
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();

        todoDAO.insert(todoVO1);
    }

    //2, 전체 목록 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void getOneTEst() throws SQLException {
        Long tno = 1L;
        TodoVO todoVO = todoDAO.selectOne(tno);
        System.out.println(todoVO);
    }

    @Test
    public void deleteTest() throws SQLException {
        Long tno = 2L;
        todoDAO.deleteTodo(tno);
    }

    @Test
    public void updateTest() throws SQLException {
        TodoVO todoVO = TodoVO.builder()
                .tno(3L)
                .title("샘플 디비 수정 테스트55")
                .dueDate(LocalDate.of(2024, 10, 31))
                .finished(true)
                .build();
        todoDAO.updateOne(todoVO);
    }
}
