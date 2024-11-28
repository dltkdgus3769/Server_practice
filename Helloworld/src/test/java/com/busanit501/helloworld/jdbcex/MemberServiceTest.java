package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2
public class MemberServiceTest {
    private MemberService memberService;
    @BeforeEach
    public void ready(){
        memberService = MemberService.INSTANCE;
    }

    @Test
    public void loginTest() throws SQLException {
        MemberDTO memberDTO = memberService.login("lsy","1234");
        log.info("MemberService Test Login : " + memberDTO.toString());
    }

    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        memberService.updateUuid("lsy2",uuid);
    }

    @Test
    public void getMemberWithUuidSearcdh() throws SQLException {
        MemberDTO memberDTO = memberService.getMemberWithUuidService("fe255805-ab76-479e-aa5e-384200eb94fc");
        log.info("MemberService Test GetMember : " + memberDTO.toString());
    }
}
