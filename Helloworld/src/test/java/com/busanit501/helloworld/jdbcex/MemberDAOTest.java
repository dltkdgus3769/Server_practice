package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dao.MemberDAO;
import com.busanit501.helloworld.jdbcex.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2
public class MemberDAOTest {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    @Test
    public void getMemberWithMpwTest() throws SQLException {
        String mid = "lsy";
        String mpw = "1234";
        MemberVO memberVO = memberDAO.getMemberWithMpw(mid,mpw);
        log.info("memberVO 조회 확인: " +memberVO );
    }

    @Test
    public void updateUuidTest() throws SQLException {
        String mid = "lsy";
        String uuid = UUID.randomUUID().toString();
        memberDAO.updateUuid(mid,uuid);
    }

    @Test
    public void getMemberWithUuidTest() throws SQLException {
        MemberVO memberVO = memberDAO.getMemberWithUuid("fe255805-ab76-479e-aa5e-384200eb94fc");
        log.info("memberVO:"+memberVO);

    }
}
