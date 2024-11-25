package com.busanit501.helloworld.jdbcex.dao;

import com.busanit501.helloworld.jdbcex.vo.MenuVO;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    public void insert(MenuVO menuVO) throws SQLException {

        String sql = "insert into tbl_menu (menu, price, description) " +
                "values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, menuVO.getMenu());
        preparedStatement.setInt(2, menuVO.getPrice());
        preparedStatement.setString(3, menuVO.getDescription());
        preparedStatement.executeUpdate();
    } //insert

    public List<MenuVO> selectAll() throws SQLException {
        String sql = "select * from tbl_menu";
        List<MenuVO> list = new ArrayList<>();
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            MenuVO menuVO = MenuVO.builder()
                    .tno(rs.getLong("tno"))
                    .menu(rs.getString("menu"))
                    .price(rs.getInt("price"))
                    .description(rs.getString("description"))
                    .build();

            list.add(menuVO);
        }
        return list;
    }

    public MenuVO selectOne(Long tno) throws SQLException {
        String sql = "select * from tbl_menu where tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();
        MenuVO menuVO = MenuVO.builder()
                .tno(rs.getLong("tno"))
                .menu(rs.getString("menu"))
                .price(rs.getInt("price"))
                .description(rs.getString("description"))
                .build();

        return menuVO;

    }

    //수정
    public void updateOne(MenuVO menuVO) throws SQLException {
        String sql = "update tbl_menu set menu=?, price=?, description=? where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, menuVO.getMenu());
        preparedStatement.setInt(2,menuVO.getPrice());
        preparedStatement.setString(3, menuVO.getDescription());
        preparedStatement.setLong(4,menuVO.getTno());
        preparedStatement.executeUpdate();
    }

    //삭제
    public void deleteMenu(Long tno) throws SQLException {
        String sql = "delete from tbl_menu where tno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        preparedStatement.executeUpdate();
    }
}
