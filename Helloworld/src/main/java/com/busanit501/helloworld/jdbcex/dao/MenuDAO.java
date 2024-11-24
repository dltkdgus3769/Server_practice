package com.busanit501.helloworld.jdbcex.dao;

import com.busanit501.helloworld.jdbcex.dto.MenuVO;
import com.busanit501.helloworld.jdbcex.dto.TodoVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
