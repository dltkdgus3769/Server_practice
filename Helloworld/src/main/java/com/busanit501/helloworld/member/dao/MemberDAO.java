package com.busanit501.helloworld.member.dao;

import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {


    public void insert(MemberVO memberVO) throws SQLException {

        String sql = "insert into tbl_member2 (name, birthdate, tel) values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getName());
        preparedStatement.setDate(2, Date.valueOf(memberVO.getBirthdate()));
        preparedStatement.setString(3, memberVO.getTel());
        preparedStatement.executeUpdate();
    } //insert

    public List<MemberVO> selectAll() throws SQLException {
        String sql = "select * from tbl_member2";
        List<MemberVO> list = new ArrayList<>();
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            MemberVO memberVO = MemberVO.builder()
                    .mno(rs.getLong("mno"))
                    .name(rs.getString("name"))
                    .birthdate(rs.getDate("birthdate").toLocalDate())
                    .tel(rs.getString("tel"))
                    .build();

            list.add(memberVO);
        }
        return list;
    }

    public MemberVO selectOne(Long mno) throws SQLException {
        String sql = "select * from tbl_member2 where mno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, mno);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();
        MemberVO memberVO = MemberVO.builder()
                .mno(rs.getLong("mno"))
                .name(rs.getString("name"))
                .birthdate(rs.getDate("birthdate").toLocalDate())
                .tel(rs.getString("tel"))
                .build();

        return memberVO;

    }

    //수정
    public void updateOne(MemberVO memberVO) throws SQLException {
        String sql = "update tbl_member set name=?, birthdate=?, tel=? where mno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getName());
        preparedStatement.setDate(2, Date.valueOf(memberVO.getBirthdate()));
        preparedStatement.setString(3,memberVO.getTel());
        preparedStatement.setLong(4,memberVO.getMno());
        preparedStatement.executeUpdate();
    }

    //삭제
    public void deleteMember(Long mno) throws SQLException {
        String sql = "delete from tbl_member where mno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, mno);
        preparedStatement.executeUpdate();
    }

    public MemberVO getMemberWithMpw(Long mno) throws SQLException {
        String query = "select * from tbl_member2 where mno=?";
        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, mno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        memberVO = MemberVO.builder()
                .mno(resultSet.getLong("mno"))
                .name(resultSet.getString("name"))
                .birthdate(resultSet.getDate("birthdate").toLocalDate())
                .tel((resultSet.getString("tel")))
                .build();

        return memberVO;
    }




} //class

