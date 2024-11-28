package com.busanit501.helloworld.jdbcex;


import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class MemberDAOTest2 {
    private MemberDAO memberDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
            memberDAO= new MemberDAO();
    }

    @Test
    public void insetTest() throws Exception {
        MemberVO memberVO = MemberVO.builder()
                .name("샘플 디비 작성 테스트4")
                .birthdate(LocalDate.of(2024, 12, 31))
                .build();

        memberDAO.insert(memberVO);
    }

    //2, 전체 목록 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<MemberVO> list = memberDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void getOneTEst() throws SQLException {
        Long mno = 1L;
        MemberVO memberVO = memberDAO.selectOne(mno);
        System.out.println(memberVO);
    }

    @Test
    public void deleteTest() throws SQLException {
        Long mno = 2L;
        memberDAO.deleteMember(mno);
    }

    @Test
    public void updateTest() throws SQLException {
        MemberVO memberVO = MemberVO.builder()
                .mno(3L)
                .name("샘플 디비 수정 테스트55")
                .birthdate(LocalDate.of(2024, 10, 31))
                .tel("010-444-4444")
                .build();
        memberDAO.updateOne(memberVO);
    }

    @Test
    public void getMemberWithMpwTest() throws SQLException {
        Long mno = 1L;
        MemberVO memberVO = memberDAO.getMemberWithMpw(mno);
        log.info("memberVO 조회 확인: " +memberVO );
    }
}
