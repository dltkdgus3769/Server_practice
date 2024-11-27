package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class MemberServiceTest {
    private MemberService memberService;
    @BeforeEach
    public void ready(){
        memberService = MemberService.INSTANCE;
    }

    @Test
    public void testInsert() throws SQLException {
        MemberDTO memberDTO = MemberDTO.builder()
                .name("abc")
                .birthdate(LocalDate.now())
                .tel("010-1234-4567")
                .build();
        memberService.register(memberDTO);
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<MemberDTO> dtoList = memberService.listAll();
        for(MemberDTO memberDTO:dtoList){
            log.info(memberDTO);
        }
    }

    //하나 조회
    @Test
    public void testSelectone() throws SQLException {
        MemberDTO memberDTO = memberService.get(1L);
        log.info(memberDTO);
    }

    //수정 테스트
    @Test
    public void testUpdate() throws SQLException {
        MemberDTO memberDTO = MemberDTO.builder()
                .mno(1L)
                .name("LSH")
                .birthdate(LocalDate.now())
                .tel("010-1234-4567")
                .build();
        memberService.update(memberDTO);
    }

    //삭제 테스트
    @Test
    public void testDelete() throws SQLException {
        memberService.delete(1L);
    }

}
