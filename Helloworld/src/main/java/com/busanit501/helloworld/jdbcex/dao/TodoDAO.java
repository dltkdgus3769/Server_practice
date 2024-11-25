package com.busanit501.helloworld.jdbcex.dao;

import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import com.sun.tools.javac.comp.Todo;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    // Todo 등록기능, 추가하기.
    //DAO(Data Access Object), DB의 데이터에 접근하기 위한 객체
    // VO(Value Object, 실제 디비 컬럼과 일치함)
    // 서비스 계층에서, VO 넘겨 받은 데이터 중에서, 보여줄 데이터만 따로 분리해서,
    // 전달하는 용도로 사용하는 DTO 입니다.

    public void insert(TodoVO todoVO) throws SQLException {

        String sql = "insert into tbl_todo (title, dueDate, finished) " +
                "values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.executeUpdate();
    } //insert

    public List<TodoVO> selectAll() throws SQLException {
        String sql = "select * from tbl_todo";
        List<TodoVO> list = new ArrayList<>();
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            TodoVO todoVO = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("duedate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();

            list.add(todoVO);
        }
        return list;
    }

    public TodoVO selectOne(Long tno) throws SQLException {
        String sql = "select * from tbl_todo where tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();
        TodoVO todoVO = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("duedate").toLocalDate())
                .finished(rs.getBoolean("finished"))
                .build();

        return todoVO;

    }

    //수정
    public void updateOne(TodoVO todoVO) throws SQLException {
        String sql = "update tbl_todo set title=?, dueDate=?, finished=? where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3,todoVO.isFinished());
        preparedStatement.setLong(4,todoVO.getTno());
        preparedStatement.executeUpdate();
    }

    //삭제
    public void deleteTodo(Long tno) throws SQLException {
        String sql = "delete from tbl_todo where tno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        preparedStatement.executeUpdate();
    }


    public String getTime() {
        String now = null;
        // hikariCP 이용해서,
        // 디비 연결하고,
        // sql 전달하고,
        // 결과값 받고,
        // 자원 반납
        // 자원 반납 하는 방법 2가지.
        //1)
        // try catch -> try with resource , 자동으로 자원 반납을 함.
        // autocloseable 인터페이스를 구현한 기능들만, 자동 반납.
        // 2) 애너테이션 이용해서, @cleanup , 이용하면, 간단히 자동 반납.
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("select now()");
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.next();
            now = resultSet.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        } //catch
        return now;
    } //getTime

    public String getTime2() throws SQLException {
        String now = null;
        // 자동으로 디비의 connection 반납하는 방법2
        // @Cleanup
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select now()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        now = resultSet.getString(1);
        return now;
    }


} //class

